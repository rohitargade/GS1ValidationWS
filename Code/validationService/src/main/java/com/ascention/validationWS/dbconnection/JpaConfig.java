package com.ascention.validationWS.dbconnection;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
public class JpaConfig {}
//@Configuration
//public class JpaConfig {
//      
//    @Bean(name = "MSSQLDataSource")
//    public DataSource h2DataSource() 
//    {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        dataSourceBuilder.url("jdbc:h2:file:C:/temp/test");
//        dataSourceBuilder.username("sa");
//        dataSourceBuilder.password("");
//        return dataSourceBuilder.build();
//    }
// 
//    @Bean(name = "mySqlDataSource")
//    @Primary
//    public DataSource mySqlDataSource() 
//    {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.url("jdbc:mysql://localhost/testdb");
//        dataSourceBuilder.username("dbuser");
//        dataSourceBuilder.password("dbpass");
//        return dataSourceBuilder.build();
//    }
//}