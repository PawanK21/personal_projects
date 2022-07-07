package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class Contact extends JFrame {
    public static void main(String[] args) {
        new Contact().setVisible(true);
    }
    JButton sb1, sb2, b2;
    ImageIcon i, i2;
    Contact(){}
    Contact(String name, String num, Map<String, String> map, Main obj) {
        ArrayList<Image> icons  = new ArrayList<>();
        icons.add(new ImageIcon(ClassLoader.getSystemResource("com/company/Images/user1.png")).getImage());
        super.setIconImages(icons);
        setBounds(800, 240, 340, 150);
        setTitle(name);
        getContentPane().setBackground(Color.cyan);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        sb1 = new JButton((name.charAt(0) + "").toUpperCase());
        sb1.setToolTipText(name.substring(0));
        sb1.setBounds(35, 45, 45,45);
        sb1.setForeground(Color.gray);
        sb1.setBackground(Color.cyan);
        sb1.setBorder(new Main.RoundedBorder(15));
        sb1.setFont(sb1.getFont().deriveFont(13.0f));
        add(sb1);
        sb1.addActionListener(e -> {
            obj.setVisible(false);
        });
        sb1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new CallPage(name, num, (HashMap)map).setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        sb2 = new JButton(name.substring(0));
        sb2.setToolTipText(name.substring(0));
        sb2.setBounds(80,45,200,30);
        sb2.setForeground(Color.gray);
        sb2.setBackground(Color.cyan);
        sb2.setBorderPainted(false);
        sb2.setFont(sb2.getFont().deriveFont(22.0f));
        sb2.setHorizontalAlignment(SwingConstants.LEFT);
        add(sb2);
        sb2.addActionListener(e -> {
            obj.setVisible(false);
        });
        sb2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new CallPage(name, num, (HashMap)map).setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        i = new ImageIcon(ClassLoader.getSystemResource("com/company/Images/bbtn2.png"));
        Image i1 = i.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        i2 = new ImageIcon(i1);
        b2  = new JButton(i2);
        b2.setBounds(8, 8, 25, 20);
        b2.setBackground(Color.cyan);
        b2.setBorderPainted(false);
        b2.setToolTipText("Home");
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(b2);
        b2.addActionListener(e -> {
            this.setVisible(false);
            new Main(map).setVisible(false);
        });
    }
}
