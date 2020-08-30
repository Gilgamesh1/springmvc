package guru.springframework.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

@Configuration
@EnableAutoConfiguration
@ComponentScan("guru.springframework")
@ActiveProfiles({"jpadao"})
public class JpaIntegrationConfig {
}
