/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import model.FractionCalculatorModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import model.DBUtils;

/**
 *
 * @author remig
 */
@WebListener
public class FractionContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent serverContextEvent) {
        FractionCalculatorModel calcModel = new FractionCalculatorModel();
        serverContextEvent.getServletContext().setAttribute("sharedModel", calcModel);
        DBUtils sqlCon = null;
        try {
            sqlCon = new DBUtils();
            System.out.println("Estabilished connection");
        } catch (SQLException e) {
            System.err.println("FUCK"); // FIXME: do something sensible
            String msg = e.getMessage();
            System.err.println(msg);
        }

        serverContextEvent.getServletContext().setAttribute("sharedDB", sqlCon);

    }
}
