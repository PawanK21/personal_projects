package com.company;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;


public class Main extends JFrame {

    JButton createNewContactBtn;
    JButton b1,rbtn;
    JTextField t1;
    ImageIcon i, img, ri, ri1, ticon ;
    JPanel contactFrame;
//    JLabel label;

    Main() {}
    Main(Map<String, String> map) {

        ArrayList<Image> icons  = new ArrayList<>();
        icons.add(new ImageIcon(ClassLoader.getSystemResource("com/company/Images/phone-book1.png")).getImage());
        super.setIconImages(icons);

        setBounds(230, 50, 578, 730);
        setTitle("PhoneBook");
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        i = new ImageIcon(ClassLoader.getSystemResource("com/company/Images/search_icon3.png")); // get image
        Image i1 = i.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT); // resized image
        ImageIcon i2 = new ImageIcon(i1); // set image

        b1 = new JButton(i2);
        b1.setBounds(20,10,50,50);
        b1.setToolTipText("Search contact");
        b1.setBorderPainted(false);
        b1.setBackground(Color.white);
        b1.setForeground(Color.blue);
        b1.setFont(new Font(Font.DIALOG,Font.BOLD,25));
        b1.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.gray));
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));


        t1 = new JTextField("Search contacts");
        t1.setHorizontalAlignment(SwingConstants.CENTER);
        t1.setToolTipText("Search contacts");
        t1.setBounds(45,30,420,70);
        t1.setFont(new Font(Font.DIALOG,Font.ITALIC,22));
        t1.setForeground(Color.DARK_GRAY);
        t1.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.gray));
        t1.setBorder(new RoundedBorder(20));
        t1.add(b1);
        ArrayList<String> namesList = new ArrayList<>();
        for(String s: map.keySet()) {
            namesList.add(map.get(s).toLowerCase());
        }
//        System.out.println(namesList);
        add(t1);
        t1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (t1.getText().equals("Search contacts")) {
                    t1.setText("");
//                    t1.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (t1.getText().isEmpty()) {
//                    t1.setForeground(Color.GRAY);
                    t1.setText("Search contacts");
                }
            }
        });

        ri = new ImageIcon(ClassLoader.getSystemResource("com/company/Images/refresh.png"));
        Image ri2 = ri.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        ri1 = new ImageIcon(ri2);
        rbtn = new JButton(ri1);
        rbtn.setBounds(470,40,50,50);
        rbtn.setToolTipText("Refresh");
        rbtn.setBackground(Color.white);
        rbtn.setBorderPainted(false);
        add(rbtn);
        rbtn.addActionListener(e -> {
            this.setVisible(false);
            this.setVisible(true);
            if(!(t1.getText().equals("Search contacts"))) {
                t1.setText("Search contacts");
            }
        });


        img = new ImageIcon(ClassLoader.getSystemResource("com/company/Images/create_contactImg2.png"));
        Image img1 = img.getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT);
        ImageIcon img2  = new ImageIcon(img1);

        contactFrame = new JPanel();
        contactFrame.setBounds(40, 120, 480, 530);
        contactFrame.setBackground(Color.white);
        contactFrame.setLayout(null);
//        contactFrame.setLayout(new FlowLayout());
        add(contactFrame);

        createNewContactBtn = new JButton("Create new contact", img2);
        createNewContactBtn.setToolTipText("Add new contact");
        createNewContactBtn.setHorizontalAlignment(SwingConstants.CENTER);
        createNewContactBtn.setBounds(120,10,250,30);
        createNewContactBtn.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        createNewContactBtn.setBorderPainted(false);
        createNewContactBtn.setBackground(Color.white);
        createNewContactBtn.setForeground(Color.black);
        createNewContactBtn.setFont(new Font(Font.DIALOG,Font.PLAIN,18));
        createNewContactBtn.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.gray));
        createNewContactBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contactFrame.add(createNewContactBtn);
        createNewContactBtn.addActionListener(e -> {
            new NewContact(map).setVisible(true);
            this.setVisible(false);
        });


        JPanel contactPanel = new JPanel(new BorderLayout());
        contactPanel.setBounds(40,60,400,450);
        contactPanel.setBackground(Color.cyan);
        contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contactPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(40, 60,400, 450);
        contactFrame.add(scrollPane);


//        JPanel contactPanel = new JPanel();
//        contactPanel.setLayout(new BorderLayout());
//        contactPanel.setBounds(40,60,400,450);
//        contactPanel.setBackground(Color.cyan);
//        contactPanel.setLayout(null);
//        contactFrame.add(contactPanel);
//
//
//        JScrollBar verticalBar = new JScrollBar( JScrollBar.VERTICAL, 0, 100, 0, 440);
//        verticalBar.setBounds(440,59,20,440);
//        contactFrame.add(verticalBar, BorderLayout.EAST);


        ArrayList<String> mapedList = myEffSort(map); // merge sort
//        System.out.println(mapedList);
        // event on search button
        b1.addActionListener(e -> {
            String str = t1.getText();

            String num = "";

            for(String key: mapedList) {
                String[] arr = key.split("-");
                if((arr[0].toLowerCase()).equals(str.toLowerCase())) {
                    num = arr[1];
                    break;
                }
            }

            boolean searchContact = effSearch(namesList, str.toLowerCase());
            if(searchContact) {
                String name = (str.charAt(0) + "") + str.substring(1);
                System.out.println("found");
                new Contact(name, num, map, this).setVisible(true);
                this.setVisible(true);
            }
            else {
//                System.out.println("Not found");
                JOptionPane.showMessageDialog(rootPane, "contact does not exist !");
            }
        });

        int sep = 60;
        int hsep = 30;
        JButton contactButton;
        JButton btnsymbl;
        ArrayList<Integer> colors = new ArrayList<>();
       for(int i = 50; i<= 255; i++) {
           colors.add(i);
       }

        for(String string: mapedList) {
            String[] strArr = string.split("-");

//            JLabel label = new JLabel();
//            label.setBounds(20, hsep, 380,55);
//            label.setBackground(Color.gray);

            btnsymbl = new JButton((strArr[0].charAt(0) + "").toUpperCase());
            btnsymbl.setToolTipText(strArr[0]);
            btnsymbl.setBounds(25, hsep, 50,45);
            btnsymbl.setBackground(new Color(getRandomElement(colors), getRandomElement(colors), getRandomElement(colors)));
//            btnsymbl.setBorder(new RoundedBorder(15));
            btnsymbl.setFont(btnsymbl.getFont().deriveFont(13.0f));
            btnsymbl.addActionListener(e -> {
                this.setVisible(false);
            });
            btnsymbl.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new CallPage(strArr[0], strArr[1], (HashMap)map).setVisible(true);
                }

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
            });

            contactButton = new JButton(strArr[0]);
            contactButton.setToolTipText(strArr[0]);
            contactButton.setBounds(85,hsep,250,30);
            contactButton.setBackground(Color.cyan);
            contactButton.setBorderPainted(false);
            contactButton.setFont(contactButton.getFont().deriveFont(18.0f));
            contactButton.setHorizontalAlignment(SwingConstants.LEFT);
            contactButton.addActionListener(e -> {
                this.setVisible(false);
            });
            contactButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    new CallPage(strArr[0], strArr[1], (HashMap)map).setVisible(true);
                }

                @Override
                public void mousePressed(MouseEvent e) {}

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
            });

            hsep += sep;
////            label.add(btnsymbl);
////            label.add(contactButton);
////            contactPanel.add(label);
//            contactPanel.add(btnsymbl, BorderLayout.LINE_START);
//            contactPanel.add(contactButton, BorderLayout.CENTER);
            contactPanel.add(btnsymbl);
            contactPanel.add(contactButton);

        }
    }
    
    protected static class RoundedBorder implements Border {
        private final int radius;
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }
        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
        Map<String, String> map = new HashMap<>(); // Map<String (phone number), String (Name)>
        try {
            File myObj = new File("D:/contactList_data.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
//                System.out.println(data);
                String[] str = data.split("=");
//                System.out.println(str[1] + "-> " + str[0]);
                map.put(str[1], str[0]);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Main o = new Main(map);
        o.setVisible(true);
    }

    private static ArrayList<String> myEffSort(Map<String, String> map) {
        ArrayList<String> names  = new ArrayList<>();
        for(String str : map.keySet()) {
            names.add(map.get(str) +"-"+ str);
        }
//        System.out.println(names);

        int lo = 0;
        int hi = names.size() - 1;

        ArrayList<String> sortedNames = mergeSort(names, 0, names.size() - 1 );

        return sortedNames;
    }
    private static ArrayList<String> mergeSort(ArrayList<String> names, int lo, int hi) {

        if(lo == hi) {
            ArrayList<String> bl = new ArrayList<>();
            bl.add(names.get(lo));
            return bl;
        }
        int mid = lo + (hi - lo)/2;
        ArrayList<String> left = mergeSort(names,lo, mid);
        ArrayList<String> right = mergeSort(names, mid + 1, hi);
        ArrayList<String> sortedNames = mergeTwoSortedList(left, right);

        return sortedNames;
    }
    private static ArrayList<String> mergeTwoSortedList(ArrayList<String> left, ArrayList<String> right) {
        ArrayList<String> list = new ArrayList<>();
        int i = 0,j = 0, ls = left.size() , rs = right.size(); // rs -> right siz; // ls -> left size
        while(i < ls && j < rs) {
            if(left.get(i).compareTo(right.get(j)) < 0) {
                list.add(left.get(i));
                i++;
            }
            else {
                list.add(right.get(j));
                j++;
            }
        }
        while(i < ls) {
            list.add(left.get(i));
            i++;
        }
        while(j < rs) {
            list.add(right.get(j));
            j++;
        }
        return list;
    }

    private boolean effSearch(ArrayList<String> contactList, String tar) {

        int lo = 0;
        int hi = contactList.size() - 1;
        ArrayList<String> list = mergeSort(contactList, lo, hi);
//        System.out.println(list);
        while(lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if(list.get(mid).equals(tar)) {
                return true;
            }
            else if(list.get(mid).compareTo(tar) < 0){
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        return false;
    }

    public static int getRandomElement(ArrayList<Integer> list)
    {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}