///author


package bookstoreframe;

import java.awt.Button;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FourthFrame extends Frame implements ActionListener, WindowListener{
  
    
  private TextField Author_Id = new TextField("");
  private TextField First_Name = new TextField("");
  private TextField Last_Name = new TextField("");
  private TextField ISBN = new TextField("");

  private Label info1 = new Label("Author_Id:");
  private Label info2 = new Label("First_Name:");
  private Label info3 = new Label("Last_Name:");
  private Label info4 = new Label("ISBN:");
  ResultSet rs;
  
  Statement stmt;
  Button insert = new Button("Insert");
  Button insertbook=new Button("Insert New Book");
  Button delete = new Button("Delete");
  Button update = new Button("Update");
  Button search = new Button("Search");
  Button back = new Button("Back");
  Button exit = new Button("Exit");
  
  
     Connection con;
FourthFrame() {
    super("BookStore Authors");
        addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent e){
        System.exit(0);
        }
        
        });
        try {
            
          String url = "jdbc:sqlserver://localhost;databaseName=BookStore";
          String username = "Hazem";
          String password = "123";
          con = DriverManager.getConnection(url, username, password);
            System.out.println("CONNECTED!");
        } catch (SQLException ex) {
            System.out.println("SQL CONNECTION ERROR!");
        }
        
        Font font4 = new Font("Arial", Font.PLAIN, 24);
        
        info1.setBounds(20, 50, 80, 30);
    add(info1);
    info2.setBounds(20, 100, 80, 30);
    add(info2);
    info3.setBounds(20, 150, 80, 30);
    add(info3);
    info4.setBounds(20, 200, 80, 30);
    add(info4);
    
    Author_Id.setBounds(100, 50, 200, 30);
    Author_Id.setFont(font4);
    add(Author_Id);
    
    First_Name.setBounds(100, 100, 200, 30);
    First_Name.setFont(font4);
    add(First_Name);
    
    Last_Name.setBounds(100, 150, 200, 30);
    Last_Name.setFont(font4);
    add(Last_Name);
    
    ISBN.setBounds(100, 200, 200, 30);
    ISBN.setFont(font4);
    add(ISBN);


    update.setBounds(50, 310, 130, 35);
    update.addActionListener(this);
    add(update);
    
    insertbook.setBounds(301, 200, 130, 30);
    insertbook.addActionListener(this);
    add(insertbook);
    
    delete.setBounds(185, 310, 130, 35);
    delete.addActionListener(this);
    add(delete);
    
    insert.setBounds(50, 270, 130, 35);
    insert.addActionListener(this);
    add(insert);

    search.setBounds(185, 270, 130, 35);
    search.addActionListener(this);
    add(search);


    back.setBounds(320, 270, 130, 35);
    back.addActionListener(this);
    add(back);
    
    exit.setBounds(320, 310, 130, 35);
    exit.addActionListener(this);
    add(exit);
    

    setLayout(null);
    setBounds(740, 320, 450, 355);
    setVisible(true);
    setResizable(false);
        
    
}
    @Override
    public void actionPerformed(ActionEvent e) {
          if (e.getSource() == exit) {
            System.exit(0);
        }
        if(e.getSource() == back){
            setVisible(false);
            FirstFrame First = new FirstFrame();
            First.setVisible(true);
            
            
        }
         if (e.getSource() == insert) {
      try {
        PreparedStatement ps = con.prepareStatement("INSERT INTO author VALUES (?,?,?,?)");
        ps.setInt(1, Integer.parseInt(Author_Id.getText()));
        ps.setString(2, First_Name.getText());
        ps.setString(3, Last_Name.getText());
        ps.setInt(4, Integer.parseInt(ISBN.getText()));
        ps.executeUpdate();
      } catch (Exception ex) {
        System.out.println(ex.getMessage());
      }
    }
         
         if(e.getSource() == insertbook){
    try {
    PreparedStatement psBookPublisher = con.prepareStatement("INSERT INTO book_author VALUES (?,?)");
    psBookPublisher.setInt(1, Integer.parseInt(ISBN.getText()));
    psBookPublisher.setInt(2, Integer.parseInt(Author_Id.getText()));
    psBookPublisher.executeUpdate();
    } catch (Exception ex) {
    System.out.println(ex.getMessage());
  }
    
}
         
         
        if (e.getSource() == update) {
            
        try{
             PreparedStatement ps = con.prepareStatement("UPDATE author SET First_Name = ?, Last_Name = ?, ISBN = ? WHERE Author_Id = ?");

        ps.setString(1, First_Name.getText());
        ps.setString(2, Last_Name.getText());
        ps.setInt(3, Integer.parseInt(ISBN.getText()));
        ps.setInt(4, Integer.parseInt(Author_Id.getText()));
        ps.executeUpdate();
      } catch (Exception ex) {
        System.out.println(ex.getMessage());
              }
    
        }
        if (e.getSource() == delete){
            int searching = Integer.parseInt(Author_Id.getText());
      try {
        stmt.executeUpdate("DELETE FROM publisher WHERE Author_Id = " + searching);
      } catch (SQLException ex) {
        Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
      }
        }
        if (e.getSource() == search){
            int searching = Integer.parseInt(Author_Id.getText());
      try {
        PreparedStatement pts = con.prepareStatement("select * from Author WHERE Author_Id=" + searching);
        rs = pts.executeQuery();
        while (rs.next()) {
          System.out.println("Author Id: " + rs.getInt(1));
          System.out.println("First_Name " + rs.getString(2));
          System.out.println("Last_Name: " + rs.getString(3));
          System.out.println();
        }
      } catch (SQLException ex) {
        System.out.println("Id not found / Error");
        Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      try {
        PreparedStatement pts = con.prepareStatement("select * from book_author WHERE Author_Id=" + searching);
        rs = pts.executeQuery();
        while (rs.next()) {
          System.out.println("Book made(ISBN): " + rs.getInt(1));
            System.out.println("Author Id: : " + rs.getInt(2));
          System.out.println();
        }
      } catch (SQLException ex) {
        System.out.println("Id not found / Error");
        Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
