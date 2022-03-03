package com.dbmigrator;

import java.sql.*;

public class TenantPresence {

    public static String JDBC_URL = System.getenv("JDBC_URL");
    public static String JDBC_USER = System.getenv("JDBC_USER");
    public static String JDBC_PASS = System.getenv("JDBC_PASS");

    public static String TABLE_STORAGE_PRESENCE_CS = "";

    Connection mysqlConnection;

    public TenantPresence() throws SQLException {
        mysqlConnection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    public void getAllTenant() throws SQLException {
        String sql = "SELECT * FROM anyconnect_db.endpoint_table where TENANT_ID = 1 and TYPE = 1 and STATUS = 0";

        PreparedStatement ps = mysqlConnection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String endpoint = rs.getString("ENDPOINT_ID");
            System.out.println(endpoint);
        }

        mysqlConnection.close();

    }



    public static void main(String... args) throws SQLException {
        TenantPresence tenantPresence = new TenantPresence();
        tenantPresence.getAllTenant();

    }
}
