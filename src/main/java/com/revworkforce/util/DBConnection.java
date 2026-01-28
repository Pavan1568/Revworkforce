package com.revworkforce.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {

    private static final Logger logger = LogManager.getLogger(DBConnection.class);


    private static final String URL = "jdbc:mysql://localhost:3306/revworkforce_db";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            logger.info("✅ Database connected successfully!");
            return con;
        } catch (Exception e) {
            logger.error("❌ Database connection failed!",e);

        }
        return null;
    }
}
