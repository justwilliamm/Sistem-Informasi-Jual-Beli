/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conek;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class nek {
    private static Connection mysqlconfig;
    public static Connection configDB()throws SQLException{
        try{
            String url="jdbc:mysql://localhost:3306/dbminimarket";
            String user="root";
            String pass="";
            
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlconfig=DriverManager.getConnection(url, user, pass);
        }catch(SQLException e){
            System.err.println("Connection configuration failed!" + e.getMessage());
        }
        return mysqlconfig;
    }
}
