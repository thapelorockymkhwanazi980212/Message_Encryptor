
package za.ac.tut.SecureMessageFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import za.ac.tut.Message.Message;
import za.ac.tut.MessageEncrypto.MessageEncryptor;

/**
 *
 * @author Thapelo Mkhwanazi
 */
public class MyFrame extends JFrame
{
    private JLabel headinglabel;
    private JTextArea plainMessageTextArea, encryptedMessageTextArea;
    private JButton encryptbutton;
    private JScrollPane plainmessagescrollpane, encryptmessagescrollpane;
    private JPanel headingpanel, plainmessagepanel, encryptmessagepanel, buttonpanel, mainpanel;
    private JMenu menu;
    private JMenuBar menubar;
    private JMenuItem openmenuitem, savemenuitem, clearmenuitem, exitmenuitem;
    
    public MyFrame()
    {
        setTitle("Secure Messages");
        setSize(50, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(false);
        
        headinglabel = new JLabel("Message Encryptor");
        headinglabel.setForeground(Color.BLUE);
        headinglabel.setFont(new Font("SERIF", Font.BOLD + Font.ITALIC,  30));
        headinglabel.setBorder(new BevelBorder(BevelBorder.RAISED));
        
        plainMessageTextArea = new JTextArea(10,30);
        plainMessageTextArea.setEditable(false);
        
        encryptedMessageTextArea = new JTextArea(10, 30);
        encryptedMessageTextArea.setEditable(false);
        
        encryptbutton = new JButton("Encrypt Message>>>");
        encryptbutton.addActionListener(new encryptbuttonlistener());
        
        plainmessagescrollpane = new JScrollPane(plainMessageTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        encryptmessagescrollpane = new JScrollPane(encryptedMessageTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
       

        openmenuitem = new JMenuItem("Open File..");
        openmenuitem.addActionListener(new openButtonHandler());
        
        savemenuitem = new JMenuItem("Save to file.."); 
        savemenuitem.addActionListener(new saveButtonHandler());
        
        clearmenuitem = new JMenuItem("Clear");
        clearmenuitem.addActionListener(new clearButtonHandler());
        
        exitmenuitem = new JMenuItem("Exit");
        exitmenuitem.addActionListener(new exitButtonHandler());
        
        menu = new JMenu("File");
        menu.add(openmenuitem);
        menu.add(savemenuitem);
        menu.addSeparator();
        menu.add(clearmenuitem);
        menu.add(exitmenuitem);
        
         menubar = new JMenuBar();
         menubar.add(menu);
         
         setJMenuBar(menubar);
        
        //panels yanong
        headingpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headingpanel.add(headinglabel);
        
       plainmessagepanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
       plainmessagepanel.add(plainmessagescrollpane);
       plainmessagepanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1),"Plain message"));
       
       encryptmessagepanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
       encryptmessagepanel.add(encryptmessagescrollpane);
       encryptmessagepanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 1),"Encrypted message"));
       
       buttonpanel = new JPanel(new GridLayout(5, 1, 1, 1));
       buttonpanel.add(encryptbutton);
       
       
       mainpanel = new JPanel(new BorderLayout());
       mainpanel.add(headingpanel, BorderLayout.NORTH);
       mainpanel.add(plainmessagepanel, BorderLayout.WEST);
       mainpanel.add(buttonpanel, BorderLayout.CENTER);
       mainpanel.add(encryptmessagepanel, BorderLayout.EAST);
       
        add(mainpanel);
        
        setVisible(true);
        pack();
    }
    
    public class exitButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    
    }
    
     public class clearButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            plainMessageTextArea.setText("");
            encryptedMessageTextArea.setText("");
        }
    
    }
     
    public class openButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser fc = new JFileChooser();
            int val;
            BufferedReader br;
            File file;
            
            val = fc.showOpenDialog(MyFrame.this);
            
            if(val == JFileChooser.APPROVE_OPTION)
            {
                file = fc.getSelectedFile();
                String data, record = "";
                
                
                try 
                {
                    br = new BufferedReader(new FileReader(file));
                    
                    while((data = br.readLine()) != null)
                    {
                        record = record + data + "\n";
                    }
                    
                    br.close();
                    plainMessageTextArea.setText(record);
                } 
                catch (IOException ex)
                {
                    Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "The selected value is " + val);
            }
        }
    
    }
        
   public class saveButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
           JFileChooser fc = new JFileChooser();
           File file;
           int val;
           BufferedWriter bw;
           String message;
            
           val = fc.showSaveDialog(MyFrame.this);
           
           if(val == JFileChooser.APPROVE_OPTION)
           {
               file = fc.getSelectedFile();
               
               try 
               {
                   bw = new BufferedWriter(new FileWriter(file, true));
                   
                   message = encryptedMessageTextArea.getText();
                   bw.write(message);
                   bw.newLine();
                   bw.close();
                   
                   JOptionPane.showMessageDialog(null, "File Written Successfully");
               }
               catch (IOException ex) 
               {
                   Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        }
    
    }

    public class encryptbuttonlistener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            String plainMessage;
            Message message;
            MessageEncryptor me;
            
            //ke kereya mesasge o mo plain from textarea then ko isa to Message class coz ona o tseya message 
            // and MessageEncryptor class o tseya Mesesage
            
            plainMessage = plainMessageTextArea.getText();  
                                                            
            message = new Message(plainMessage);
            
            me = new MessageEncryptor();
            Message encryptedMsg = me.Encrytor(message);
            
            encryptedMessageTextArea.setText(encryptedMsg.toString());
        }
        
    }
}
