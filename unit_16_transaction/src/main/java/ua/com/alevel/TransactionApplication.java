package ua.com.alevel;

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
public class TransactionApplication {

    private final DBTest dbTest;

    public TransactionApplication(DBTest dbTest) {
        this.dbTest = dbTest;
    }

    public static void main(String[] args) {
        SpringApplication.run(TransactionApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void test() {
        dbTest.searchByCustomQuery();
    }
}
