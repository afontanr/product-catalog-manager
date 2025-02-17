package com.capitole.productcatalogmanager.infrastructure.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Configuration class for setting up the application's data source.
 * Uses HikariCP for connection pooling and integrates Flyway for database migrations.
 */
@Configuration
public class DataSourceConfig {

    private final DataSourceProperties dataSourceProperties;

    public DataSourceConfig(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    /**
     * Creates and configures a HikariCP {@link DataSource} using application properties.
     *
     * @return A configured {@link DataSource} instance.
     */
    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(dataSourceProperties.getUrl());
        hikariConfig.setUsername(dataSourceProperties.getUsername());
        hikariConfig.setPassword(dataSourceProperties.getPassword());
        hikariConfig.setDriverClassName(dataSourceProperties.getDriverClassName());

        hikariConfig.setMaximumPoolSize(dataSourceProperties.getMaximumPoolSize());
        hikariConfig.setMinimumIdle(dataSourceProperties.getMinimumIdle());
        hikariConfig.setConnectionTimeout(dataSourceProperties.getConnectionTimeout());
        hikariConfig.setIdleTimeout(dataSourceProperties.getIdleTimeout());
        hikariConfig.setMaxLifetime(dataSourceProperties.getMaxLifetime());
        hikariConfig.setConnectionTestQuery(dataSourceProperties.getConnectionTestQuery());

        return new HikariDataSource(hikariConfig);
    }

    /**
     * Configures and initializes Flyway for database migrations.
     *
     * @param dataSource The data source used for database migrations.
     * @return A configured {@link Flyway} instance.
     */
    @Bean
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:db/migration")
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();
        return flyway;
    }

}
