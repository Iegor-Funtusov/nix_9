package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.AuthorDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorDaoImpl implements AuthorDao {

    private final JpaConfig jpaConfig;

    public AuthorDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Author entity) {

    }

    @Override
    public void update(Author entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Author findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Author> findAll(DataTableRequest request) {
        List<Author> authors = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select id, created, updated, visible, first_name, last_name, count(*) as bookCount from authors join author_book ab on authors.id = ab.author_id group by author_id order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();

        System.out.println("sql = " + sql);

        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                AuthorResultSet authorResultSet = convertResultSetToAuthor(resultSet);
                authors.add(authorResultSet.getAuthor());
                otherParamMap.put(authorResultSet.getAuthor().getId(), authorResultSet.getBookCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DataTableResponse<Author> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(authors);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    @Override
    public long count() {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from authors")) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private AuthorResultSet convertResultSetToAuthor(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int bookCount = resultSet.getInt("bookCount");

        Author author = new Author();
        author.setId(id);
        author.setCreated(created);
        author.setUpdated(updated);
        author.setVisible(visible);
        author.setFirstName(firstName);
        author.setLastName(lastName);

        return new AuthorResultSet(author, bookCount);
    }

    private static class AuthorResultSet {

        private final Author author;
        private final int bookCount;

        private AuthorResultSet(Author author, int bookCount) {
            this.author = author;
            this.bookCount = bookCount;
        }

        public Author getAuthor() {
            return author;
        }

        public int getBookCount() {
            return bookCount;
        }
    }
}
