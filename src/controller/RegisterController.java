/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;
import model.*;
import view.*;

public class RegisterController {
    RegistrationModel model;
    RegistrationView view;
    ResultSet rs;
    PreparedStatement pst=null;
        public RegisterController(RegistrationView view)
        {
            this.view=view;
            
             view.addRegistrationListener(new RegisetrListener());
        }
        
        
    class RegisetrListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            try
            {
                model=view.getUser();
                if(checkUser(model))
                {
                    view.setMessage("Registered Successfully");
                }
                else
                {
                    view.setMessage("error");
                    
                }
            }
            catch(Exception e1)
            {
                
            }

        }
       
        public boolean checkUser(RegistrationModel user) throws Exception
        {
           
try
          {
         Class.forName("com.mysql.cj.jdbc.Driver");
               Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","ajina kaya8860");
String sql="insert into register(username,password,fname,lname,gender,email,role) values(?,?,?,?,?,?,?)";
pst = conn.prepareStatement(sql);

// String sql="select * from patient where pname='"+user.getUsername()+"' AND paddress='"+user.getPassword()+"'";
pst.setString(1,user.getUsername());
pst.setString(2,user.getPassword());
pst.setString(3,user.getFname());
pst.setString(4,user.getLname());
pst.setString(5,user.getGender());
pst.setString(6, user.getEmail());
pst.setString(7,user.getRole());

pst.executeUpdate();
              System.out.println("Data inserted");
JOptionPane.showMessageDialog(null,"Data Registered Successfully");
          
          }
          catch(Exception e2)
          {
              System.out.println(e2.getMessage());
          }         
            
            return false;
        }
    
}
    
}
