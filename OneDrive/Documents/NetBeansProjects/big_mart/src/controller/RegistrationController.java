/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import model.*;
import view.*;
import database.*;


/**
 *
 * @author Acer
 */
public class RegistrationController {
    RegistrationModel model;
    RegistrationView myview;
     PreparedStatement pst ;
    ResultSet rs;
    Connection conn = null;
    
     public RegistrationController(RegistrationView myview)
    {
        this.myview=myview;
        myview.addRegistrationListener(new RegistrationController.RegistrationListener());
    }
    class RegistrationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e){
            try
            {
               model=myview.getUser();
               if(checkUser(model))
               {
                   myview.setMessage("Register Successfully");
               }
               else
               {
                  myview.setMessage("usersname or password cannot be empty"); 
               }
               
            }
            catch (Exception e1)
            {
               System.out.println(e1.getMessage()); 
            }
        }
        public boolean checkUser(RegistrationModel user) throws Exception
        {
            conn = LoginConnection.dbConnect();
            Class.forName("com.mysql.jdbc.driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/LED","root","ajina kayastha");
            String sql="insert into register(fname,lname,username,email,gender) values (?,?,?,?,?)";
//            String sql = "insert into register'username="+user.getUsername()+"' AND password='"+user.getPassword()+"' AND fname='"+user.getFname()+"' AND lname='"+user.getLname()+"' And email='"+user.getEmail()+"' AND gender='"+user.getGender()+"' AND role='"+user.getRole()+"'";
             
              try
            {
                 
//                stmt = conn.createStatement();
//                stmt.executeUpdate(sql);
//                System.out.println("Data Inserted");
                JOptionPane.showMessageDialog(null,"Data Registered Successfully");
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
