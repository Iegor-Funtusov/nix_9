package org.dinix.configurator.impl;

import org.apache.commons.lang3.StringUtils;
import org.dinix.configurator.JpaConfigurator;
import org.dinix.orm.DiNixOrmProperties;
import org.dinix.storage.JpaStorage;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySqlJpaConfigurator implements JpaConfigurator {

    private final Statement statement;
    private final Connection connection;
    private final DiNixOrmProperties diNixOrmProperties;
    private final JpaStorage.JpaContext jpaContext = JpaStorage.getInstance().getJpaContext();

    public MySqlJpaConfigurator(
            Statement statement,
            Connection connection,
            DiNixOrmProperties diNixOrmProperties
    ) {
        this.diNixOrmProperties = diNixOrmProperties;
        this.statement = statement;
        this.connection = connection;
    }

    @Override
    public void defaultConfigure() {
        System.out.println("MySqlJpaConfigurator.defaultConfigure");
        System.out.println("diNixOrmProperties = " + diNixOrmProperties);
        for (Class<?> entity : JpaStorage.getInstance().getEntities()) {
            JpaStorage.EntityContext entityContext = jpaContext.addEntityToContext(entity);
            List<String> columnNames = getAllColumnNamesByTable(entityContext);
            for (Field field : entity.getDeclaredFields()) {
                String columnName = getColumnNameByEntityField(field);
                if (columnNames.stream().anyMatch(name -> name.equals(columnName))) {
                    JpaStorage.getInstance().getJpaContext().addColumnToEntityContext(field, entityContext);
                }
            }
        }
    }

    @Override
    public void updateConfigure() {

    }

    @Override
    public void createDropConfigure() {

    }

    @Override
    public void initConfigure() {
        System.out.println("diNixOrmProperties = " + diNixOrmProperties);
        String drop = "DROP DATABASE IF EXISTS " + diNixOrmProperties.getSchema() + ";";
        String create = "CREATE DATABASE IF NOT EXISTS " + diNixOrmProperties.getSchema() + ";";
        String encoding = "ALTER DATABASE " + diNixOrmProperties.getSchema() + " CHARSET=UTF8 COLLATE=UTF8_BIN;";
        try {
            statement.executeUpdate(drop);
            statement.executeUpdate(create);
            statement.executeUpdate(encoding);
            for (Class<?> entity : JpaStorage.getInstance().getEntities()) {
                initTable(jpaContext.addEntityToContext(entity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initTable(JpaStorage.EntityContext entityContext) throws SQLException {
        List<String> uniqueIndexes = new ArrayList<>();

        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder.append("CREATE TABLE ");
        tableBuilder.append("`");
        tableBuilder.append(diNixOrmProperties.getSchema());
        tableBuilder.append("`.`");
        tableBuilder.append(entityContext.getTableName());
        tableBuilder.append("` ");
        tableBuilder.append("(");

        columnInit(tableBuilder, uniqueIndexes, entityContext);

        String columnIdName = entityContext.getColumnContexts().stream()
                .filter(JpaStorage.ColumnContext::isPrimaryKey)
                .map(JpaStorage.ColumnContext::getColumnName)
                .findFirst().orElseThrow(() -> new RuntimeException("unique index not found"));

        tableBuilder
                .append("PRIMARY KEY (`")
                .append(columnIdName)
                .append("`)");

        if (uniqueIndexes.isEmpty()) {
            tableBuilder
                    .append(")");
        } else {
            tableBuilder
                    .append(",");

            for (int i = 0; i < uniqueIndexes.size(); i++) {
                tableBuilder
                        .append(" UNIQUE INDEX `")
                        .append(uniqueIndexes.get(i))
                        .append("_UNIQUE` (`")
                        .append(uniqueIndexes.get(i))
                        .append("` ASC) VISIBLE");
                if (uniqueIndexes.size() - 1 == i) {
                    tableBuilder
                            .append(") ");
                } else {
                    tableBuilder
                            .append(",");
                }
            }
        }

        tableBuilder
                .append(" ENGINE = InnoDB ")
                .append("DEFAULT CHARACTER SET = utf8;");

        System.out.println("tableBuilder = " + tableBuilder);
        statement.executeUpdate(tableBuilder.toString());
    }

    private void columnInit(StringBuilder tableBuilder, List<String> uniqueIndexes, JpaStorage.EntityContext entityContext) {
        columnInit(tableBuilder, uniqueIndexes, entityContext, entityContext.getEntity());
    }

    private void columnInit(StringBuilder tableBuilder, List<String> uniqueIndexes, JpaStorage.EntityContext entityContext, Class<?> entityClass) {
//        List<Field> entityFields = EntityFieldUtil.reverseFieldList(entityClass);
        Field[] entityFields = entityClass.getDeclaredFields();
        for (Field entityField : entityFields) {
            JpaStorage.ColumnContext columnContext = JpaStorage.getInstance()
                    .getJpaContext().addColumnToEntityContext(entityField, entityContext);
            if (columnContext.isPrimaryKey()) {
                tableBuilder.append("`");
                tableBuilder.append(columnContext.getColumnName());
                tableBuilder.append("` ");
                tableBuilder.append("INT NOT NULL AUTO_INCREMENT, ");
            } else {
                if (entityField.getType().isAssignableFrom(String.class)) {
                    tableBuilder.append("`");
                    tableBuilder.append(columnContext.getColumnName());
                    tableBuilder.append("` ");
                    tableBuilder.append("VARCHAR(");
                    tableBuilder.append(columnContext.getLength());
                    tableBuilder.append(") ");
                    setCollate(tableBuilder);
                    if (columnContext.isNullable()) {
                        tableBuilder.append("NULL, ");
                    } else {
                        tableBuilder.append("DEFAULT '' NOT NULL, ");
                    }
                }
                if (entityField.getType().isAssignableFrom(Integer.class)) {
                    tableBuilder.append("`");
                    tableBuilder.append(columnContext.getColumnName());
                    tableBuilder.append("` ");
                    tableBuilder.append("INT ");
                    if (columnContext.isNullable()) {
                        tableBuilder.append("NULL, ");
                    } else {
                        tableBuilder.append("DEFAULT 0 NOT NULL, ");
                    }
                }
                if (entityField.getType().isAssignableFrom(Date.class)) {
                    tableBuilder.append("`");
                    tableBuilder.append(columnContext.getColumnName());
                    tableBuilder.append("` ");
                    tableBuilder.append("TIMESTAMP ");
                    if (columnContext.isNullable()) {
                        tableBuilder.append("NULL, ");
                    } else {
                        tableBuilder.append("DEFAULT CURRENT_TIMESTAMP NOT NULL, ");
                    }
                }
                if (columnContext.isUnique()) {
                    uniqueIndexes.add(columnContext.getColumnName());
                }
            }
        }
    }

    private void setCollate(StringBuilder tableBuilder) {
        tableBuilder.append("CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' ");
    }

    private List<String> getAllColumnNamesByTable(JpaStorage.EntityContext entityContext) {
        List<String> columnNames = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + entityContext.getTableName());
            ResultSetMetaData metadata = resultSet.getMetaData();
            int columnCount = metadata.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metadata.getColumnName(i));
            }
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }
        return columnNames;
    }

    private static String getColumnNameByEntityField(Field field) {
        String columnName = field.getName();
        if (field.isAnnotationPresent(Column.class)) {
            String name = field.getAnnotation(Column.class).name();
            if (StringUtils.isNotBlank(name)) {
                columnName = name;
            }
        }
        return columnName;
    }
}
