package book.book.config;

import book.book.PersistencePackageMarker;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Test configuration for testing persistence layer
 */
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = PersistencePackageMarker.class)
@EntityScan(basePackageClasses = PersistencePackageMarker.class)
@ComponentScan(basePackageClasses = PersistencePackageMarker.class)
public class PersistenceTestConfig {}
