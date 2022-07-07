package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Call extends JFrame {
    public static void main(String[] args) {
        new Call().setVisible(true);
    }

    JLabel pl, nameLabel, numLabel, callingLabel;
    ImageIcon pi1, pi2, cpi1, cpi2, cei1, cei2;
    JButton callPickupBtn, callEndBtn;

    Call() {}
    Call(String name, String num, HashMap<String, String> map) {
        ArrayList<Image> icons  = new ArrayList<>();
        icons.add(new ImageIcon(ClassLoader.getSystemResource("com/company/Images/call_title_icon.png")).getImage());
        super.setIconImages(icons);
        setBounds(230, 50, 578, 700);
        setTitle("Call");
        getContentPane().setBackground(Color.cyan);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        pi1 = new ImageIcon(ClassLoader.getSystemResource("com/company/Images/user.png"));
        Image pi = pi1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        pi2 = new ImageIcon(pi);
        pl = new JLabel(pi2);
        pl.setBounds(200, 100, 150,150);
//        pl.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.white));
//        pl.setBackground(Color.gray);
        add(pl);

        callingLabel = new JLabel("Calling...");
        callingLabel.setBounds(220, 260, 200, 30);
        callingLabel.setFont(new Font("Sanserif", Font.PLAIN, 20));
//        callingLabel.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.white));
//        callingLabel.setBackground(Color.black);
        add(callingLabel);

        //nameLabel
        nameLabel = new JLabel(name);
        nameLabel.setBounds(170, 290, 400, 60);
        nameLabel.setFont(new Font("Sanserif", Font.PLAIN, 40));
//        nameLabel.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.white));
//        nameLabel.setBackground(Color.gray);
        add(nameLabel);


        //numLabel
        numLabel = new JLabel("Main +" + num);
        numLabel.setBounds(170, 350, 400, 30);
        numLabel.setFont(new Font("Sanserif", Font.PLAIN, 24));
//        numLabel.setBorder(BorderFactory.createMatteBorder(0,0,2,0,Color.white));
//        numLabel.setBackground(Color.black);
        add(numLabel);

        cei1 = new ImageIcon(ClassLoader.getSystemResource("com/company/Images/call_end.png"));
        Image cei = cei1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        cei2 = new ImageIcon(cei);
        callEndBtn = new JButton(cei2);
        callEndBtn.setBounds(239, 540, 80,80);
        callEndBtn.setToolTipText("Call End");
        callEndBtn.setBackground(Color.cyan);
        callEndBtn.setBorderPainted(false);
        add(callEndBtn);
        callEndBtn.addActionListener(e -> {
            this.dispose();
            new Main(map).setVisible(true);
        });

    }
}
