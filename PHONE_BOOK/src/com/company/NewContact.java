package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class NewContact extends JFrame  {
    public static void main(String[] args) {
        new NewContact().setVisible(true);
    }

    JLabel regl, firstname, lastname, il;
    JTextField t1,t2, t3;
    JButton b1, b2;
    ImageIcon i, i2;
    public static JTextField t7;
    public  static JPasswordField t8;
    public static TreeMap<String,String> tm;


    NewContact() {}
    NewContact(Map<String, String> map)  {

        ArrayList<Image> icons  = new ArrayList<>();
        icons.add(new ImageIcon(ClassLoader.getSystemResource("com/company/Images/add-user1.png")).getImage());
        super.setIconImages(icons);
        setBounds(230, 50, 578, 700);
        getContentPane().setBackground(Color.white);
        setTitle("New Contact");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        regl = new JLabel("New contact");
        regl.setBounds(105,50,343,40);
        regl.setHorizontalAlignment(SwingConstants.CENTER);
//        regl.setBorder(BorderFactory.createMatteBorder(0,0,5,0,Color.orange));
        regl.setFont(new Font("Roman",Font.BOLD,50));
        regl.setForeground(Color.getHSBColor(0.2f,0.4f, 0.4f));
        add(regl);


//        firstname = new JLabel("Name            : ");
//        firstname.setBounds(60,185,180,25);
//        firstname.setFont(new Font("Roman",Font.PLAIN,18));
//        firstname.setForeground(Color.black);
//        titledBorder = new TitledBorder(new LineBorder(Color.blue), "Panel 1");
//        add(firstname);

        t1 = new JTextField("First Name");
        t1.setBounds(180,165,200,100);
        t1.setHorizontalAlignment(SwingConstants.CENTER);
        t1.setFont(new Font("Arial",Font.PLAIN,22));
        t1.setForeground(Color.DARK_GRAY);
        t1.setBackground(Color.getHSBColor(175,219,245));
        t1.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.white));
        add(t1);
        t1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                t1.setText(t1.getText());
            }
            @Override
            public void mousePressed(MouseEvent e) {
                t1.setText("");
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        t2 = new JTextField("Last Name");
        t2.setBounds(180,305,200,100);
        t2.setHorizontalAlignment(SwingConstants.CENTER);
        t2.setFont(new Font("Arial",Font.PLAIN,22));
        t2.setForeground(Color.DARK_GRAY);
        t2.setBackground(Color.getHSBColor(175,219,245));
        t2.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.white));
        add(t2);
        t2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                t2.setText(t2.getText());
            }
            @Override
            public void mousePressed(MouseEvent e) {
                t2.setText("");
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) { }
        });

        t3 = new JTextField("Contact Number");
        t3.setBounds(180,445,200,100);
        t3.setHorizontalAlignment(SwingConstants.CENTER);
        t3.setFont(new Font("Arial",Font.PLAIN,22));
        t3.setForeground(Color.DARK_GRAY);
        t3.setBackground(Color.getHSBColor(175,219,245));
        t3.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.white));
        add(t3);
        t3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                t3.setText(t3.getText());
            }
            @Override
            public void mousePressed(MouseEvent e) {
                t3.setText("");
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        b1 = new JButton("Save");
        b1.setBounds(203,585,150,45);
        b1.setForeground(Color.black);
        b1.setBackground(Color.cyan);
        b1.setFont(new Font("Tacoma",Font.PLAIN,25));
        b1.setBorder(BorderFactory.createMatteBorder(0,0,4,0,Color.blue));
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(b1);
        b1.addActionListener(e -> {
//            File Handling
            try{
                String name = t1.getText() + " " + t2.getText();
                String contactnum = t3.getText();
//                System.out.println(name + " " + contactnum);

                String str = "\n" + name + "=" + contactnum;
                BufferedWriter fw = new BufferedWriter(new FileWriter("D:/contactList_data.txt", true));
                fw.write(str);
                fw.close();
            }catch(IOException e1){
                e1.printStackTrace();
            }

            Map<String, String> Newmap = new HashMap<>();

            try {
                File myObj = new File("D:/contactList_data.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                System.out.println(data);
                    String[] str = data.split("=");
                    Newmap.put(str[1], str[0]);
                }
                myReader.close();
            } catch (FileNotFoundException ev) {
                System.out.println("An error occurred.");
                ev.printStackTrace();
            }

            new Main(Newmap).setVisible(true);
            this.setVisible(false);
        });

        i = new ImageIcon(ClassLoader.getSystemResource("com/company/Images/btoh1.png"));
        Image i1 = i.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        i2 = new ImageIcon(i1);
        b2  = new JButton(i2);
        b2.setBounds(10, 10, 45, 40);
        b2.setBackground(Color.white);
        b2.setBorderPainted(false);
        b2.setToolTipText("Home");
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(b2);
        b2.addActionListener(e -> {
            new Main(map).setVisible(true);
            this.setVisible(false);
        });


    }

//    @Override
//    public void actionPerformed(ActionEvent ae) {
//        //File Handling
//        try{
//            String str = t7.getText()+"="+t8.getText()+"\n";
//
//            BufferedWriter fw = new BufferedWriter(new FileWriter("D:/FakePasswords.txt",true));
//            fw.write(str);
//            fw.close();
//            this.setVisible(false);
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }
}
