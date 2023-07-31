package bookstoreframe;

import static java.awt.Color.gray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import static java.awt.Color.black;
import static java.awt.Color.white;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

public class BookStoreFrame extends Frame implements ActionListener {

  Font font1 = new Font("Arial", Font.BOLD, 24);
  Font font2 = new Font("Arial", Font.PLAIN, 15);
  Font font4 = new Font("Arial", Font.PLAIN, 24);
  Font font3 = new Font("Arial", Font.BOLD, 25);
  Label pass_label = new Label("Welcome To Modern Bookstore");
  Label user = new Label("Username");
  Label pass = new Label("Password");
  TextField _user = new TextField("");
  TextField _pass = new TextField("");
  Checkbox showPasswordCheckBox = new Checkbox("");

  Button log = new Button("Login");
  private Label info = new Label("Show password");

  BookStoreFrame() {

    super("BookStore");
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    setLayout(null);
    setBounds(740, 240, 450, 600);

    log.setBounds(150, 500, 150, 30);
    log.addActionListener(this);
    add(log);

    user.setBounds(50, 215, 70, 30);
    user.setFont(font2);
    add(user);

    _user.setBounds(50, 250, 250, 30);
    _user.addActionListener(this);
    _user.setFont(font4);
    add(_user);

    pass.setBounds(50, 315, 70, 30);
    pass.setFont(font2);
    add(pass);

    _pass.setBounds(50, 350, 250, 30);
    _pass.addActionListener(this);
    _pass.setFont(font4);
    _pass.setEchoChar('*');
    add(_pass);

    add(showPasswordCheckBox);
    showPasswordCheckBox.setBounds(50, 400, 20, 30);
    add(info);
    info.setBounds(70, 400, 250, 30);

    pass_label.setBounds(50, 70, 950, 70);
    pass_label.setFont(font1);
    add(pass_label);

    setVisible(true);
    setResizable(false);
    showPasswordCheckBox.addItemListener(new ItemListener() {
      @Override
      public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == showPasswordCheckBox) {
          if (showPasswordCheckBox.getState()) {
            _pass.setEchoChar((char) 0);
          } else {
            _pass.setEchoChar('*');
          }
        }
      }
    });

  }

  public static void main(String[] args) {
      
    BookStoreFrame b = new BookStoreFrame();
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == log) {
      if (_pass.getText().equals("admin") && _user.getText().equals("admin")) {
        setVisible(false);
        FirstFrame s_fram = new FirstFrame();
        s_fram.setVisible(true);
      } else {
        JOptionPane.showMessageDialog(null, "incorrect Username or Password");

      }

    }

  }
}