/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.*;
import view.*;

/**
 *
 * @author Acer
 */
public class LoginController {
    LoginModel model;
    LoginView myview;
    Statement stmt;
    ResultSet rs;
   
    public LoginController(LoginView myview)
    {
        this.myview=myview;
        myview.addLoginListener(new LoginListener());
    }
    class LoginListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e){
            try
            {
               model=myview.getUser();
               if(checkUser(model))
               {
                   myview.setMessage("Login Successfully");
               }
               else
               {
                  myview.setMessage("Invalid usersname or password"); 
               }
               
            }
            catch (Exception e1)
            {
               System.out.println(e1.getMessage()); 
            }
        }
        public boolean checkUser(LoginModel user) throws Exception
        {
            Class.forName("com.mysql.jdbc.driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LED","root","ajina kaya8860");
            String sql = "select * from register'username="+user.getUsername()+"' AND password='"+user.getPassword()+"' AND role='"+user.getRole()+"'";
            try
            {
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                if(rs.next())
                {
                    return true;
                }
                conn.close();
            }
            catch(SQLException e2)
            {
                
                System.out.println(e2.getMessage());
            }
            return false;
        }
    }
    
    
}

