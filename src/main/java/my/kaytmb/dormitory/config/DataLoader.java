package my.kaytmb.dormitory.config;


import my.kaytmb.dormitory.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author kay 26.03.2025
 */
@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            //userRepository.save(new User("admin", passwordEncoder.encode("b32h2ffp"), User.Role.ADMIN));
        };
    }

}
