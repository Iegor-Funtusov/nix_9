package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.repository.user.AdminRepository;

//@EnableAspectJAutoProxy
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class })
public class UnitFinalProjectApplication {

    private final BCryptPasswordEncoder encoder;
    private final AdminRepository adminRepository;

    public UnitFinalProjectApplication(BCryptPasswordEncoder encoder, AdminRepository adminRepository) {
        this.encoder = encoder;
        this.adminRepository = adminRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(UnitFinalProjectApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void listen() {
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
