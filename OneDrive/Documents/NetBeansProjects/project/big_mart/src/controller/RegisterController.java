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
       
            
             view.addRegistrationListener(new RegisterListener());
        }
        
        
    class RegisterListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
    try {
        model = view.getUser();
        if (model.getUsername().isEmpty() || model.getFname().isEmpty() || model.getLname().isEmpty() || model.getEmail().isEmpty() || model.getGender().isEmpty() || model.getRole().isEmpty() || model.getPassword().isEmpty() || model.getconfirmPassword().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Fill up the form properly.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!model.getPassword().equals(model.getconfirmPassword())) {
            JOptionPane.showMessageDialog(null, "Password Mismatch.", "Error", JOptionPane.ERROR_MESSAGE);
//        } else if (!isUsernameUnique(model.getUsername())) {
//            JOptionPane.showMessageDialog(null, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (checkUser(model)) {
                view.setMessage("Registered Successfully");
            } else {
                view.setMessage("Error");
            }
        }
    } catch (Exception e1) {
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
pst.setString(1,user.getUsername());
pst.setString(2,user.getPassword());
pst.setString(3,user.getFname());
pst.setString(4,user.getLname());
pst.setString(5,user.getGender());
pst.setString(6, user.getEmail());
pst.setString(7,user.getRole());

    int rs=pst.executeUpdate();

       System.out.println("Data inserted");
       JOptionPane.showMessageDialog(null,"Data Registered Successful");
          
       
          return true;
    } catch (SQLException | ClassNotFoundException e) {
        throw e;
    }
        }
     }
//    public boolean isUsernameUnique(String username) throws SQLException {
//    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "ajina kaya8860");
//         PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM register WHERE username = ?")) {
//        statement.setString(1, username);
//        try (ResultSet resultSet = statement.executeQuery()) {
//            if (resultSet.next()) {
//                int count = resultSet.getInt(1);
//                 
//                return count == 0;
//            }
//        }
//    } catch (SQLException e) {
//        throw e;
  }
 
