package com.capitole.productcatalogmanager.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "database")
public class DataSourceProperties {

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private int maximumPoolSize;

    private int minimumIdle;

    private int connectionTimeout;

    private int idleTimeout;

    private int maxLifetime;

    private String connectionTestQuery;

}
