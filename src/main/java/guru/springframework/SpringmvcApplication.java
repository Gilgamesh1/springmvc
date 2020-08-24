package guru.springframework;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Log4j2
public class SpringmvcApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(SpringmvcApplication.class, args);
        log.info("*********BEANS********");
        log.info(ctx.getBeanDefinitionCount());
        for (String bean : ctx.getBeanDefinitionNames()) {
            log.info(bean);
        }
    }

}
