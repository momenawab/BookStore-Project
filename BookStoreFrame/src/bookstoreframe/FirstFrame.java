/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreframe;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author Hazem
 */
public class FirstFrame extends Frame implements ActionListener, WindowListener{
    
     Button Books=new Button("Books");
    Button Publishers=new Button("Publishers");
    Button Authors=new Button("Authors");
    
    FirstFrame(){
        super("BookStore");
        addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent e){
        System.exit(0);
        }
        });
        setLayout(null);
    setBounds(740, 320, 450, 220);
    Books.setBounds(150, 50, 150, 30);
    Books.addActionListener(this);
    add(Books);
    Publishers.setBounds(150, 100, 150, 30);
    Publishers.addActionListener(this);
    add(Publishers);
    Authors.setBounds(150, 150, 150, 30);
    Authors.addActionListener(this);
    add(Authors);

    
    
}

    @Override
    public void actionPerformed(ActionEvent e) {
       
         if (e.getSource() == Books) {
             setVisible(false);
             SecondFrame frame = new SecondFrame();
             frame.setVisible(true);
             

         }
        if (e.getSource() == Publishers) {
             setVisible(false);
             ThirdFrame frame = new ThirdFrame();
             frame.setVisible(true);
             

         }
        if (e.getSource() == Authors) {
             setVisible(false);
             FourthFrame frame = new FourthFrame();
             frame.setVisible(true);
             

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
