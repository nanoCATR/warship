package com.warship.warship;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


@Configurable
public class Config {

    @Value("${db.path}")
    private String path;
    @Value("${db.encoding}")
    private String encoding;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;
    private static Connection con=null;

    public Connection getConnection() throws SQLException {
        System.out.println(path);
        if (con==null || con.isClosed()) {
            Properties props = new Properties();

//            props.setProperty("user", "SYSDBA");
//            props.setProperty("password", "masterkey");
//            props.setProperty("encoding", "UTF8");
//            props.setProperty("path", "jdbc:firebirdsql://localhost:3050/C:/db/WARSHIP.FDB");
            props.setProperty("user", "postgres");
            props.setProperty("password", "root");
            props.setProperty("encoding", "UTF8");
            props.setProperty("path", "jdbc:postgresql://localhost:5432/warships_java");
            con = DriverManager.getConnection(props.getProperty("path"), props);
        }
        return con;
    }
}
