package org.dinix.storage;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.*;

public class JpaStorage {

    private static JpaStorage jpaStorage;
    private final Set<Class<?>> entities = new HashSet<>();
    private final JpaContext jpaContext;

    private JpaStorage() {
        jpaContext = new JpaContext();
    }

    public static JpaStorage getInstance() {
        if (jpaStorage == null) {
            jpaStorage = new JpaStorage();
        }
        return jpaStorage;
    }

    public Set<Class<?>> getEntities() {
        return entities;
    }

    public JpaContext getJpaContext() {
        return jpaContext;
    }

    public static class JpaContext {

        private final Set<EntityContext> entitiesContext;

        JpaContext() {
            this.entitiesContext = new HashSet<>();
        }

        public Set<EntityContext> getEntitiesContext() {
            return entitiesContext;
        }

        public EntityContext addEntityToContext(Class<?> entity) {
            EntityContext entityContext = new EntityContext(entity);
            entitiesContext.add(entityContext);
            return entityContext;
        }

        public ColumnContext addColumnToEntityContext(Field entityField, EntityContext entityContext) {
            ColumnContext columnContext = new ColumnContext(entityField);
            entityContext.getColumnContexts().add(columnContext);
            return columnContext;
        }

        public EntityContext findEntityContextByEntity(Class<?> entity) {
            return entitiesContext.stream()
                    .filter(entityContext -> entityContext.getEntity().equals(entity))
                    .findFirst().orElseThrow(() -> new RuntimeException("entity " + entity.getName() + " not found"));
        }

        public EntityContext findEntityContextByEntity(String entity) {
            return entitiesContext.stream()
                    .filter(entityContext -> entityContext.getEntityName().equals(entity))
                    .findFirst().orElseThrow(() -> new RuntimeException("entity " + entity + " not found"));
        }
    }

    public static class EntityContext {

        private final Class<?> entity;
        private final String tableName;
        private final String entityName;
        private final List<ColumnContext> columnContexts;

        EntityContext(Class<?> entity) {
            this.entity = entity;
            this.tableName = initTableName(entity);
            this.entityName = entity.getSimpleName();
            this.columnContexts = new ArrayList<>();

        }

        private String initTableName(Class<?> entity) {
            Table table = entity.getAnnotation(Table.class);
            String tableName;
            if (table != null) {
                tableName = table.name();
            } else {
                tableName = entity.getSimpleName().toLowerCase().concat("s");
            }
            return tableName;
        }

        public String getTableName() {
            return tableName;
        }

        public String getEntityName() {
            return entityName;
        }

        public Class<?> getEntity() {
            return entity;
        }

        public List<ColumnContext> getColumnContexts() {
            return columnContexts;
        }

        @Override
        public String toString() {
            return "EntityContext{" +
                    "entity=" + entity +
                    ", tableName='" + tableName + '\'' +
                    ", entityName='" + entityName + '\'' +
                    ", columnContexts=" + columnContexts +
                    '}';
        }
    }

    public static class ColumnContext {

        private final Class<?> columnType;
        private final String columnName;
        private final String fieldName;
        private final boolean primaryKey;
        private final boolean unique;
        private final boolean nullable;
        private final boolean insertable;
        private final boolean updatable;
        private final String columnDefinition;
        private final int length;

        ColumnContext(Field entityField) {
            this.columnType = entityField.getType();
            this.fieldName = entityField.getName();
            String defaultColumnName = entityField.getName();
            Column entityColumn = entityField.getAnnotation(Column.class);
            if (entityColumn == null) {
                this.columnName = defaultColumnName;
                this.unique = false;
                this.nullable = true;
                this.insertable = false;
                this.updatable = true;
                this.columnDefinition = "";
                this.length = 255;
            } else {
                String columnName = entityColumn.name();
                if (StringUtils.isBlank(columnName)) {
                    this.columnName = defaultColumnName;
                } else {
                    this.columnName = columnName;
                }
                this.unique = entityColumn.unique();
                this.nullable = entityColumn.nullable();
                this.insertable = entityColumn.insertable();
                this.updatable = entityColumn.updatable();
                this.columnDefinition = entityColumn.columnDefinition();
                this.length = entityColumn.length();
            }

            this.primaryKey = entityField.getAnnotation(Id.class) != null;
        }

        public Class<?> getColumnType() {
            return columnType;
        }

        public String getColumnName() {
            return columnName;
        }

        public String getFieldName() {
            return fieldName;
        }

        public boolean isPrimaryKey() {
            return primaryKey;
        }

        public boolean isUnique() {
            return unique;
        }

        public boolean isNullable() {
            return nullable;
        }

        public boolean isInsertable() {
            return insertable;
        }

        public boolean isUpdatable() {
            return updatable;
        }

        public String getColumnDefinition() {
            return columnDefinition;
        }

        public int getLength() {
            return length;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ColumnContext that = (ColumnContext) o;
            return primaryKey == that.primaryKey && unique == that.unique && nullable == that.nullable && insertable == that.insertable && updatable == that.updatable && length == that.length && columnType.equals(that.columnType) && columnName.equals(that.columnName) && columnDefinition.equals(that.columnDefinition);
        }

        @Override
        public int hashCode() {
            return Objects.hash(columnType, columnName, primaryKey, unique, nullable, insertable, updatable, columnDefinition, length);
        }

        @Override
        public String toString() {
            return "ColumnContext{" +
                    "columnType=" + columnType +
                    ", columnName='" + columnName + '\'' +
                    ", primaryKey=" + primaryKey +
                    ", unique=" + unique +
                    ", nullable=" + nullable +
                    ", insertable=" + insertable +
                    ", updatable=" + updatable +
                    ", columnDefinition='" + columnDefinition + '\'' +
                    ", length=" + length +
                    '}';
        }
    }
}
