package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.com.alevel.elastic.index.BookIndex;
import ua.com.alevel.persistence.repository.user.AdminRepository;
import ua.com.alevel.persistence.repository.user.PersonalRepository;

import javax.annotation.PreDestroy;

//@EnableAspectJAutoProxy
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class })
public class UnitFinalProjectApplication {

    private final BCryptPasswordEncoder encoder;
    private final AdminRepository adminRepository;
    private final PersonalRepository personalRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    public UnitFinalProjectApplication(
            BCryptPasswordEncoder encoder,
            AdminRepository adminRepository,
            PersonalRepository personalRepository,
            ElasticsearchOperations elasticsearchOperations) {
        this.encoder = encoder;
        this.adminRepository = adminRepository;
        this.personalRepository = personalRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public static void main(String[] args) {
        SpringApplication.run(UnitFinalProjectApplication.class, args);
    }

    @PreDestroy
    public void deleteIndex() {
        elasticsearchOperations.indexOps(BookIndex.class).delete();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void listen() {

//        Personal personal = new Personal();
//        personal.setEmail("personal@mail.com");
//        personal.setPassword(encoder.encode("rootroot"));
//        personalRepository.save(personal);
//        Admin admin = new Admin();
//        admin.setEmail("admin@mail.com");
//        admin.setPassword(encoder.encode("rootroot"));
//        adminRepository.save(admin);
//        String pass = "123456789";
//        String encode = encoder.encode(pass);
//        System.out.println("encode = " + encode);
//        String newEncode = encoder.encode(pass);
//        System.out.println("newEncode = " + newEncode);
//        boolean matches = newEncode.matches(pass);
//        System.out.println("matches = " + matches);
    }
}
