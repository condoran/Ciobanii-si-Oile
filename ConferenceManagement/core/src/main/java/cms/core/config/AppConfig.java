package cms.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"repo", "cms.core.service", "ui"})
public class AppConfig {
}
