/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;
/**
 *
 * @author Acer
 */
import java.sql.*;

public class LoginConnection {
    
    public static Connection dbConnect(){
        try
        {
            Class.forName("com.mysql.jdbc.driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LED","root","9808640305@Sr");
            
            System.out.println("Connected");
            return conn;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
        
}
}

