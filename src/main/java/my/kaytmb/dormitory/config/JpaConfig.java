package my.kaytmb.dormitory.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author kay 25.03.2025
 */
@Configuration
@EntityScan(basePackages = "my.kaytmb.dormitory.entity")
@ComponentScan(basePackages = "my.kaytmb.dormitory.service")
@EnableTransactionManagement
public class JpaConfig {



}
