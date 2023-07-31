///publisher 


package bookstoreframe;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Hazem
 */
public class ThirdFrame extends Frame implements ActionListener, WindowListener{
    
      Font font4 = new Font("Arial", Font.PLAIN, 24);

    
  private TextField Publisher_Id = new TextField("");
  private TextField Name = new TextField("");
  private TextField City = new TextField("");
  private TextField Phone = new TextField("");
  private TextField ISBN = new TextField("");
  ResultSet rs;
  private Label info1 = new Label("Publisher_Id:");
  private Label info2 = new Label("Name:");
  private Label info3 = new Label("City:");
  private Label info4 = new Label("Phone:");
  private Label info5 = new Label("ISBN:");
  Statement stmt;
    
    Button insert=new Button("Insert New Publisher");
    Button insertbook=new Button("Insert New Book");
    Button delete=new Button("Delete");
    Button update=new Button("Update");
    Button search=new Button("Search");
    Button back=new Button("Back");
    Button exit=new Button("Exit");
     Connection con;
ThirdFrame() {
    
    super("BookStore Publishers");
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
          
          stmt = con.createStatement();
          System.out.println("CONNECTED!");
        } catch (SQLException ex) {
            System.out.println("SQL CONNECTION ERROR!");
        }
        
    info1.setBounds(20, 50, 80, 30);
    add(info1);
    info2.setBounds(20, 100, 80, 30);
    add(info2);
    info3.setBounds(20, 150, 80, 30);
    add(info3);
    info4.setBounds(20, 200, 80, 30);
    add(info4);
    info5.setBounds(20, 250, 80, 30);
    add(info5);
   

    Publisher_Id.setBounds(100, 50, 200, 30);
    Publisher_Id.setFont(font4);
    add(Publisher_Id);
    
    Name.setBounds(100, 100, 200, 30);
    Name.setFont(font4);
    add(Name);
    
    City.setBounds(100, 150, 200, 30);
    City.setFont(font4);
    add(City);
    
    Phone.setBounds(100, 200, 200, 30);
    Phone.setFont(font4);
    add(Phone);
    
    ISBN.setBounds(100, 250, 200, 30);
    ISBN.setFont(font4);
    add(ISBN);
    
    

    update.setBounds(50, 330, 130, 35);
    update.addActionListener(this);
    add(update);
    
    insertbook.setBounds(301, 200, 130, 30);
    insertbook.addActionListener(this);
    add(insertbook);
    
    delete.setBounds(185, 330, 130, 35);
    delete.addActionListener(this);
    add(delete);
    
    insert.setBounds(50, 290, 130, 35);
    insert.addActionListener(this);
    add(insert);

    search.setBounds(185, 290, 130, 35);
    search.addActionListener(this);
    add(search);


    back.setBounds(320, 290, 130, 35);
    back.addActionListener(this);
    add(back);
    
    exit.setBounds(320, 330, 130, 35);
    exit.addActionListener(this);
    add(exit);
   

    setLayout(null);
    setBounds(740, 320, 450, 370);
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
    // Insert into publisher table
    PreparedStatement psPublisher = con.prepareStatement("INSERT INTO publisher VALUES (?,?,?,?)");
    psPublisher.setInt(1, Integer.parseInt(Publisher_Id.getText()));
    psPublisher.setString(2, Name.getText());
    psPublisher.setString(3, City.getText());
    psPublisher.setInt(4, Integer.parseInt(Phone.getText()));
    psPublisher.executeUpdate();

    // Insert into book_publisher table
    
  } catch (Exception ex) {
    System.out.println(ex.getMessage());
  }
}
if(e.getSource() == insertbook){
    try {
    PreparedStatement psBookPublisher = con.prepareStatement("INSERT INTO book_publisher VALUES (?,?)");
    psBookPublisher.setInt(1, Integer.parseInt(ISBN.getText()));
    psBookPublisher.setInt(2, Integer.parseInt(Publisher_Id.getText()));
    psBookPublisher.executeUpdate();
    } catch (Exception ex) {
    System.out.println(ex.getMessage());
  }
    
}
        if (e.getSource() == update) {
            
        try{
             PreparedStatement ps = con.prepareStatement("UPDATE publisher SET Name = ?, City = ?, Phone = ? WHERE Publisher_Id = ?");

        ps.setString(1, Name.getText());
        ps.setString(2, City.getText());
        ps.setInt(3, Integer.parseInt(Phone.getText()));
        ps.setInt(4, Integer.parseInt(Publisher_Id.getText()));
        ps.executeUpdate();
      } catch (Exception ex) {
        System.out.println(ex.getMessage());
              }
    
        }
       if (e.getSource() == delete) {
  int publisherId = Integer.parseInt(Publisher_Id.getText());

  try {
    // Delete from book_publisher table
    PreparedStatement psBookPublisher = con.prepareStatement("DELETE FROM book_publisher WHERE Publisher_Id = ?");
    psBookPublisher.setInt(1, publisherId);
    psBookPublisher.executeUpdate();

    // Delete from publisher table
    PreparedStatement psPublisher = con.prepareStatement("DELETE FROM publisher WHERE Publisher_Id = ?");
    psPublisher.setInt(1, publisherId);
    psPublisher.executeUpdate();
  } catch (SQLException ex) {
    Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
  }
}

        if (e.getSource() == search){
            int searching = Integer.parseInt(Publisher_Id.getText());
      try {
        PreparedStatement pts = con.prepareStatement("select * from publisher WHERE Publisher_Id=" + searching);
        rs = pts.executeQuery();
        while (rs.next()) {
          System.out.println("Publisher Id: " + rs.getInt(1));
          System.out.println("Name " + rs.getString(2));
          System.out.println("City: " + rs.getString(3));
          System.out.println("Phone: " + rs.getInt(4));
          System.out.println();
        }
      } catch (SQLException ex) {
        System.out.println("Id not found / Error");
        Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
      }
      try {
        PreparedStatement pts = con.prepareStatement("select * from book_publisher WHERE Publisher_Id=" + searching);
        rs = pts.executeQuery();
        while (rs.next()) {
          System.out.println("Book made(ISBN): " + rs.getInt(1));
            System.out.println("Publisher Id: : " + rs.getInt(2));
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
