/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author remig
 */
public class DBUtils {
    Connection sql;

    public DBUtils(String url, String user, String pass) throws SQLException {
        sql = DriverManager.getConnection(url, user, pass);
        createSchema();
    }
    
    public DBUtils() throws SQLException {
        this("jdbc:derby://localhost:1527/lab", "app", "app");
    }
    
     private void createSchema() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found");
        }
        // make a connection to DB
        try ( Connection con = sql) {
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE TABLE operation_history "
                    + "(id INTEGER, "
                    + "num1 VARCHAR(128), "
                    + "num2 VARCHAR(128), "
                    + "operation CHAR(1), "
                    + "result VARCHAR(128)"
                    + ")");
            System.out.println("Table created");
        } catch (SQLException sqle) {
            String msg = sqle.getMessage();
            System.err.println(msg);
        }
    }
}
