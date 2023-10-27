package ru.fridge.fourth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Конфигурация базы данных
 */
@Configuration
@PropertySource("classpath:application.properties")
public class DatabaseConfig {
    /**
     * Драйвер для базы данных
     */
    @Value("${dataSource.driverClassName}")
    private String driverClassName;

    /**
     * URL базы данных.
     */
    @Value("${dataSource.url}")
    private String url;

    /**
     * Имя пользователя для базы данных
     */
    @Value("${dataSource.username}")
    private String username;

    /**
     * Пароль для базы данных
     */
    @Value("${dataSource.password}")
    private String password;

    /**
     * Создает источник данных для базы данных
     * @return Источник данных
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /**
     * Создает шаблон JDBC для работы с базой данных
     * @param dataSource Источник данных
     * @return Шаблон JDBC
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
