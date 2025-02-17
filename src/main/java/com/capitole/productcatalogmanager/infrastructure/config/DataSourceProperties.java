package com.capitole.productcatalogmanager.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for the database connection.
 * <p>
 * This class is automatically populated with values from the application's
 * configuration file (e.g., application.yml or application.properties)
 * using the prefix "database".
 * </p>
 */
@Component
@Data
@ConfigurationProperties(prefix = "database")
public class DataSourceProperties {

    /**
     * The JDBC URL of the database.
     */
    private String url;

    /**
     * The username used for database authentication.
     */
    private String username;

    /**
     * The password used for database authentication.
     */
    private String password;

    /**
     * The fully qualified class name of the database driver.
     */
    private String driverClassName;

    /**
     * The maximum number of connections allowed in the connection pool.
     */
    private int maximumPoolSize;

    /**
     * The minimum number of idle connections maintained in the connection pool.
     */
    private int minimumIdle;

    /**
     * The maximum time in milliseconds to wait for a connection from the pool.
     */
    private int connectionTimeout;

    /**
     * The maximum time in milliseconds that an idle connection can remain in the pool.
     */
    private int idleTimeout;

    /**
     * The maximum lifetime in milliseconds of a connection in the pool.
     */
    private int maxLifetime;

    /**
     * The SQL query used to test the validity of connections.
     */
    private String connectionTestQuery;

    /**
     * The default page size for paginated queries.
     */
    private int pageSize;

}
