package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class CallPage extends JFrame {
    public static void main(String[] args) {
        new CallPage().setVisible(true);
    }

    JLabel fl, Name, line, ind;
    JButton b1, b2, bb, deleteBtn, twitterBtn, linkedinBtn, gmailBtn, fbBtn, mapBtn, mapSearchBtn;
    ImageIcon i, i2, i3, i4, gi1, gi2, li1, li2, ti1,ti2, fbi1, fbi2, di1, di2, mapi1, mapi2, msi1, msi2;

    CallPage() {}
    CallPage(String name, String num, HashMap<String, String> map) {

        ArrayList<Image> icons  = new ArrayList<>();
        icons.add(new ImageIcon(ClassLoader.getSystemResource("com/company/Images/telephone1.png")).getImage());
        super.setIconImages(icons);
        setBounds(230, 50, 578, 700);
        setTitle("Call");
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        i3 = new ImageIcon(ClassLoader.getSystemResource("com/company/Images/back.png"));
        Image img = i3.getImage().getScaledInstance(30,25, Image.SCALE_DEFAULT);
        i4 = new ImageIcon(img);
        bb = new JButton(i4);
        bb.setBounds(15,15,30,25);
        bb.setBackground(Color.white);
        bb.setForeground(Color.BLACK);
        bb.setBorderPainted(false);
        add(bb);
        bb.addActionListener(e -> {
            this.setVisible(false);
            new Main(map).setVisible(true);
        });

        di1 = new ImageIcon(ClassLoader.getSystemResource("com/company/Images/delete.png"));
        Image di = di1.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        di2 = new ImageIcon(di);
        deleteBtn = new JButton(di2);
        deleteBtn.setBounds(518, 15, 30, 30);
        deleteBtn.setBackground(Color.white);
        deleteBtn.setBorderPainted(false);
        deleteBtn.setToolTipText("delete contact");
        deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(deleteBtn);
        deleteBtn.addActionListener(e -> {
            map.remove(num);
            File file = new File("D:/contactList_data.txt");
            file.delete();

            System.out.println(map.size());
            System.out.println(map);
            int mapSize = map.size() - 1;
            int i = 0;
            for(String key: map.keySet()) {
                try{
                    String str = "";
                    if(i == mapSize) {
                        str = map.get(key) + "=" + key;
                    }
                    else {
                        str = map.get(key) + "=" + key + "\n";
                    }
                    BufferedWriter fw = new BufferedWriter(new FileWriter("D:/contactList_data.txt", true));
                    fw.write(str);
                    fw.close();
                }catch(IOException e1){
                    e1.printStackTrace();
                }
                i++;
            }
            this.setVisible(false);
            new Main(map).setVisible(true);
            new Contact(name, num, map, new Main(map)).setVisible(false);
        });

        ArrayList<Integer> colors = new ArrayList<>();
        for(int i = 50; i<= 255; i++) {
            colors.add(i);
        }
        fl = new JLabel(name.charAt(0) + "");
        fl.setBounds(220, 100, 120, 120);
//        fl.setBorder(BorderFactory.createMatteBorder(40,40,40,40,new Color(Main.getRandomElement(colors), Main.getRandomElement(colors), Main.getRandomElement(colors))));
        fl.setFont(new Font("Serif",Font.BOLD,60));
        fl.setOpaque(true);
        fl.setBackground(new Color(Main.getRandomElement(colors), Main.getRandomElement(colors), Main.getRandomElement(colors)));
        fl.setForeground(Color.white);
        fl.setHorizontalAlignment(SwingConstants.CENTER);
        add(fl);


        Name = new JLabel(name);
        Name.setBounds(20, 280, 508, 100);
        Name.setForeground(Color.BLACK);
        Name.setFont(new Font("verdana", Font.PLAIN, 38));
        Name.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.lightGray));
        add(Name);


        i = new ImageIcon(ClassLoader.getSystemResource("com/company/Images/call.png"));
        Image i1 = i.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        i2 = new ImageIcon(i1);

        b1 = new JButton(i2);
        b1.setBounds(20, 435, 30,30);
        b1.setBackground(Color.white);
        b1.setBorderPainted(false);
        add(b1);
        b1.addActionListener(e -> {
            this.setVisible(false);
            new Call(name, num, map).setVisible(true);
        });

        b2 = new JButton("<html>" + num + "<br>" + "mobile" + "</html>");
        b2.setBounds(70,423,180,60);
        b2.setForeground(Color.black);
        b2.setBackground(Color.white);
        b2.setBorderPainted(false);
        b2.setFont(new Font("Serif", Font.PLAIN, 28));
        b2.setHorizontalAlignment(SwingConstants.LEFT);
        add(b2);
        b2.addActionListener(e -> {
            this.setVisible(false);
            new Call(name,num, map).setVisible(true);
        });

        gi1 = new ImageIcon(ClassLoader.getSystemResource("com/company/Images/gmail-logo1.png"));
        Image gi = gi1.getImage().getScaledInstance(29,31,Image.SCALE_DEFAULT);
        gi2 = new ImageIcon(gi);
        gmailBtn = new JButton(gi2);
        gmailBtn.setBounds(380, 442, 29,31);
        gmailBtn.setBackground(Color.white);
        gmailBtn.setBorderPainted(false);
        gmailBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gmailBtn.setToolTipText("Gmail.com");
        add(gmailBtn);
        gmailBtn.addActionListener(e -> {
            open("https://mail.google.com/mail/u/0/#inbox");
        });

        li1 = new ImageIcon(ClassLoader.getSystemResource("com/company/Images/linkedin-logo1.png"));
        Image li = li1.getImage().getScaledInstance(27,25,Image.SCALE_DEFAULT);
        li2 = new ImageIcon(li);
        linkedinBtn = new JButton(li2);
        linkedinBtn.setBounds(420, 445, 27,25);
        linkedinBtn.setBackground(Color.white);
        linkedinBtn.setBorderPainted(false);
        linkedinBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        linkedinBtn.setToolTipText("LinkedIn.com");
        add(linkedinBtn);
        linkedinBtn.addActionListener(e -> {
            open("https://www.linkedin.com/feed/");
        });

        ti1 = new ImageIcon(ClassLoader.getSystemResource("com/company/Images/twitter-logo1.png"));
        Image ti = ti1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ti2 = new ImageIcon(ti);
        twitterBtn = new JButton(ti2);
        twitterBtn.setBounds(460, 445, 25,25);
        twitterBtn.setBackground(Color.white);
        twitterBtn.setBorderPainted(false);
        twitterBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        twitterBtn.setToolTipText("Twitter.com");
        add(twitterBtn);
        twitterBtn.addActionListener(e -> {
            open("https://twitter.com/home");
        });


        fbi1 = new ImageIcon(ClassLoader.getSystemResource("com/company/Images/facebook-logo1.png"));
        Image fbi = fbi1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        fbi2 = new ImageIcon(fbi);
        fbBtn = new JButton(fbi2);
        fbBtn.setBounds(500, 445, 25,25);
        fbBtn.setBackground(Color.white);
        fbBtn.setBorderPainted(false);
        fbBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fbBtn.setToolTipText("Facebook.com");
        add(fbBtn);
        fbBtn.addActionListener(e -> {
            open("https://www.facebook.com/");
        });


        line = new JLabel();
        line.setBounds(20, 500, 508, 100);
        line.setBorder(BorderFactory.createMatteBorder(1,0,1,0,Color.lightGray));
        add(line);

        mapi1 = new ImageIcon(ClassLoader.getSystemResource("com/company/Images/location.png"));
        Image mapi = mapi1.getImage().getScaledInstance(50, 60, Image.SCALE_DEFAULT);
        mapi2 = new ImageIcon(mapi);
        mapBtn = new JButton(mapi2);
        mapBtn.setBounds(10, 20, 50,60);
        mapBtn.setBackground(Color.white);
        mapBtn.setToolTipText("search places");
        mapBtn.setBorderPainted(false);
        
        ind = new JLabel("India");
        ind.setBounds(75, 45, 50, 30);
        ind.setForeground(Color.gray);
        ind.setFont(new Font("Sanserif", Font.PLAIN, 20));


        msi1 = new ImageIcon(ClassLoader.getSystemResource("com/company/Images/maps.png"));
        Image msi = msi1.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        msi2 = new ImageIcon(msi);
        mapSearchBtn = new JButton(msi2);
        mapSearchBtn.setBounds(435, 30, 30, 30);
        mapSearchBtn.setBackground(Color.white);
        mapSearchBtn.setToolTipText("Search your location here");
        mapSearchBtn.setBorderPainted(false);

        line.add(mapSearchBtn);
        line.add(mapBtn);
        line.add(ind);

        mapBtn.addActionListener(e -> {
            open("https://www.google.com/maps/place/India/@20.0094409,64.4213648,4z/data=!3m1!4b1!4m5!3m4!1s0x30635ff06b92b791:0xd78c4fa1854213a6!8m2!3d20.593684!4d78.96288");
        });
        mapSearchBtn.addActionListener(e -> {
            open("https://www.google.com/maps/place/India/@20.0094409,64.4213648,4z/data=!3m1!4b1!4m5!3m4!1s0x30635ff06b92b791:0xd78c4fa1854213a6!8m2!3d20.593684!4d78.96288");
        });

    }

    private void open(String url) {

        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
