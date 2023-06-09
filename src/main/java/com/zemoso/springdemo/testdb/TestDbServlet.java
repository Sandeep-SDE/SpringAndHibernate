package com.zemoso.springdemo.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


import java.sql.*;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String jdbcUrl;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // setup connection variables
        String user = "root";
        String pass = "123456";

        this.setJdbcUrl("jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC");
        String jdbcUrl = this.getJdbcUrl();
        String driver = "com.mysql.cj.jdbc.Driver";

        // get connection to database
        try {
            PrintWriter out = response.getWriter();
            out.println("Connecting to database: " + jdbcUrl);
            Class.forName(driver);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
            out.println("SUCCESS!!!");
            System.out.println("Ended");
            myConn.close();

        }
        catch (Exception exc) {
            exc.printStackTrace();
            throw new ServletException(exc);
        }


    }

}











