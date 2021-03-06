import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Login extends JFrame implements ActionListener {
  
  JTextField username = null;
  JTextField password = null;
  
  public Login(){
    
    
    setSize(300,300);
    setVisible(true);
    this.setLayout(new GridLayout(3,1));
    
    username = new JTextField(20);
    this.add(username);
    
    password = new JTextField(20);
    this.add(password);
    
    JButton login = new JButton("Login!");
    login.addActionListener(this);
    // we are adding on a label to the button here
    // so later we will know which button has been clicked.
    login.setActionCommand("login");
    this.add(login);
    
    JButton test = new JButton("Test!");
    test.addActionListener(this);
    test.setActionCommand("test");
    this.add(test);
    
    
    validate();
    repaint();
    
    
  
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    new Login();
  }
  
  public void loginWithDatabase(){
    
    
    try {
      
      Class.forName("com.mysql.jdbc.Driver").newInstance();
    
    }catch(Exception e ){}
    
    
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;
      try {
          conn =
             DriverManager.getConnection("jdbc:mysql://127.0.0.1/ticketing?user=root&password=");

          // Do something with the Connection
          stmt = conn.createStatement();

          // or alternatively, if you don't know ahead of time that
          // the query will be a SELECT...

          String un = username.getText();
          String pw = password.getText();
          
          if (stmt.execute("SELECT * FROM users WHERE name = '"+un+"' AND password = '"+pw+"'")) {
              rs = stmt.getResultSet();
          }

          // loop over results
          
          while(rs.next()){
            JOptionPane.showMessageDialog(this, "Logged in!");

            System.out.print("----------------------");
            String id = rs.getString("id");
              System.out.println("ID: " + id);
            
              String sid = rs.getString("username");
              System.out.println("UN: " + sid);
          } 
         
      } catch (SQLException ex) {
          // handle any errors
          System.out.println("SQLException: " + ex.getMessage());
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
      }    
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    
    // if you want to set a label for each of the buttons
    // and then redirect the user to a different part of the program
    // you can use the getActionCommand to check which button
    // has sent the request
    if(e.getActionCommand().equals("login")){
      
      loginWithDatabase();
    }
    else if(e.getActionCommand().equals("login")){
      
    }
    
    
    
  
    
  }

}
