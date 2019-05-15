package czx.me.demo;

import ch.ralscha.extdirectspring.ExtDirectSpring;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <pre>
 *     Halo run!
 * </pre>
 *
 * @author : RYAN0UP
 * @date : 2017/11/14
 */
@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
@EnableScheduling
@ComponentScan(basePackageClasses = { ExtDirectSpring.class,Application.class})
@EnableJpaRepositories(basePackages = "czx.me.demo.repository")
public class Application {
    public static void main(String[] args) {
        System.setProperty("spring.index.ignore", "true");
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        String serverPort = run.getEnvironment().getProperty("server.port");
        System.out.println("Halo started at http://localhost:"+serverPort);
    }
}
