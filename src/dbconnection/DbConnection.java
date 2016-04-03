/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shalom
 */
public class DbConnection {
Connection con;
private String connectionUrl = "jdbc:mysql://localhost:3306/HospitalDB";
private String dbUser = "root";
private String dbPwd = "root";
public  Connection connect()
{
    try {
        Class.forName("com.mysql.jdbc.Driver");
         con = DriverManager.getConnection (connectionUrl,dbUser,dbPwd);
 
      
        
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
    }
 return con; 
}

}
