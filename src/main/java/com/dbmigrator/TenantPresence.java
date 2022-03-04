package com.dbmigrator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
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

    public void getAllTenant()
        throws SQLException, JsonProcessingException, InvalidProtocolBufferException {
        //String sql = "SELECT * FROM anyconnect_db.endpoint_table where TENANT_ID = 1 and TYPE = 1 and STATUS = 0";
        String sql = "SELECT ENDPOINT_ID, et.TENANT_ID, DEVICE_KEY, GROUP_ID, GROUP_BLOB FROM \n" +
            "anyconnect_db.endpoint_table et\n" +
            "join anyconnect_db.group_table gp\n" +
            "on et.GROUP_ID = gp.ID where\n" +
            "et.tenant_id='98ed2e00-2390-4786-a49d-6e9d2d72d3e2' and et.endpoint_id='4850'";

        PreparedStatement ps = mysqlConnection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String endpoint = rs.getString("ENDPOINT_ID");


            System.out.println(endpoint);

        }

        mysqlConnection.close();

    }



    public static void main(String... args)
        throws SQLException, JsonProcessingException, InvalidProtocolBufferException {
        TenantPresence tenantPresence = new TenantPresence();
        tenantPresence.getAllTenant();

    }
}
