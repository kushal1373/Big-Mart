/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import model.*;
import view.*;
import java.sql.*;
public class LoginController {
    LoginModel model;
    LoginView view;
    ResultSet rs;
    Statement stmt;
    public LoginController(LoginView view)
    {
        this.view=view;
        
        view.addLoginListener(new LoginListener());
        
        
    }
    class LoginListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                model=view.getUser();
                if(checkUser(model))
                {
                    view.setMessage("Login Successfully");
                }
                else
                {
                    view.setMessage("Invalid username or password");
                    
                }
            }
            catch(Exception e1)
            {
                
            }

        }
        public boolean checkUser(LoginModel user) throws Exception
        {
             Class.forName("com.mysql.cj.jdbc.Driver");
               Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","ajina kaya8860");
          String sql="select * from register where username='"+user.getUsername()+"' AND password='"+user.getPassword()+"'";
          try
          {
            stmt=conn.createStatement();
             rs=stmt.executeQuery(sql);
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

