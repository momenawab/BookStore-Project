///book

package bookstoreframe;

import java.awt.*;
import java.awt.Frame;
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

public class SecondFrame extends Frame implements ActionListener, WindowListener {

    
  Font font4 = new Font("Arial", Font.PLAIN, 24);
  ResultSet rs;
  private TextField ISBN = new TextField("");
  private TextField Title = new TextField("");
  private TextField Type = new TextField("");
  private TextField Price = new TextField("");
  private TextField Page_Count = new TextField("");

  private Label info1 = new Label("ISBN:");
  private Label info2 = new Label("Title:");
  private Label info3 = new Label("Type:");
  private Label info4 = new Label("Price:");
  private Label info5 = new Label("Page Count:");
  Statement stmt;
  Button insert = new Button("Insert");
  Button delete = new Button("Delete");
  Button update = new Button("Update");
  Button search = new Button("Search");
  Button back = new Button("Back");
  Button exit = new Button("Exit");
  Connection con;
  SecondFrame(){

    super("BookStore Books");
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
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

    ISBN.setBounds(100, 50, 200, 30);
    ISBN.setFont(font4);
    add(ISBN);
    
    Title.setBounds(100, 100, 200, 30);
    Title.setFont(font4);
    add(Title);
    
    Type.setBounds(100, 150, 200, 30);
    Type.setFont(font4);
    add(Type);
    
    Price.setBounds(100, 200, 200, 30);
    Price.setFont(font4);
    add(Price);
    
    Page_Count.setBounds(100, 250, 200, 30);
    Page_Count.setFont(font4);
    add(Page_Count);


    update.setBounds(50, 330, 130, 35);
    update.addActionListener(this);
    add(update);
    
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
    if (e.getSource() == back) {
      setVisible(false);
      FirstFrame First = new FirstFrame();
      First.setVisible(true);
    }
    if (e.getSource() == insert) {
      try {
        PreparedStatement ps = con.prepareStatement("INSERT INTO book VALUES (?, ?,?,?,?)");
        ps.setInt(1, Integer.parseInt(ISBN.getText()));
        ps.setString(2, Title.getText());
        ps.setString(3, Type.getText());
        ps.setInt(4, Integer.parseInt(Price.getText()));
        ps.setInt(5, Integer.parseInt(Page_Count.getText()));
        ps.executeUpdate();
      } catch (Exception ex) {
        System.out.println(ex.getMessage());
      }
    }
    if (e.getSource() == update) {
      try {
        int searching = Integer.parseInt(ISBN.getText());
        PreparedStatement ps = con.prepareStatement("UPDATE book SET Title = ?, Type = ?, Price = ?, Page_Count = ? WHERE ISBN = ?");
        ps.setString(1, Title.getText());
        ps.setString(2, Type.getText());
        ps.setInt(3, Integer.parseInt(Price.getText()));
        ps.setInt(4, Integer.parseInt(Page_Count.getText()));
        ps.setInt(5, searching);
        ps.executeUpdate();
      } catch (Exception ex) {
        System.out.println(ex.getMessage());
      }
    }
    if (e.getSource() == delete) {
      int searching = Integer.parseInt(ISBN.getText());
      try {
        stmt.executeUpdate("DELETE FROM book WHERE ISBN = " + searching);
      } catch (SQLException ex) {
        Logger.getLogger(SecondFrame.class.getName()).log(Level.SEVERE, null, ex);
      }

    }
    if (e.getSource() == search) {

      int searching = Integer.parseInt(ISBN.getText());
      try {
        PreparedStatement pts = con.prepareStatement("select * from book WHERE ISBN=" + searching);
        rs = pts.executeQuery();
        //while (rs.next()) {
          System.out.println("ISBN: " + rs.getInt(1));
          System.out.println("Title " + rs.getString(2));
          System.out.println("Type: " + rs.getString(3));
          System.out.println("Price: " + rs.getInt(4));
          System.out.println("Page Count: " + rs.getInt(5));
          System.out.println();
        //}
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