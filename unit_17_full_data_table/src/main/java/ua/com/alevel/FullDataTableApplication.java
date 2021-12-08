package ua.com.alevel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class })
public class FullDataTableApplication {

    @Value("${init}")
    private Boolean init;

    private final DBCombineSearchTest dbCombineSearchTest;

    public FullDataTableApplication(DBCombineSearchTest dbCombineSearchTest) {
        this.dbCombineSearchTest = dbCombineSearchTest;
    }

    public static void main(String[] args) {
        SpringApplication.run(FullDataTableApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void test() {
        if (init) {
            dbCombineSearchTest.init();
        }
        dbCombineSearchTest.searchEmployeesByParams();
    }
}
