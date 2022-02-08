package customer.chat_view;


import customer.chat_mess.Message;
import customer.chat_mess.Message_type;
import customer.chat_mess.Register_mess;
import customer.chat_view.component.Avatar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
public class Register extends JFrame implements ActionListener {

    private String avatarID = "tx3948"; // 这是默认的头像号码

    public static void main(String[] args) {
        new Register();
    }
    public Register() {
        initComponents();
    }

    private void initComponents() {
        button1 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new Avatar("tx3948", 70, 70);
        button2 = new JButton();
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JPasswordField();
        textField4 = new JPasswordField();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- button1 ----
        button1.setText("\u63d0\u4ea4");
        contentPane.add(button1);
        button1.setBackground(new Color(3, 37, 108));
        button1.setForeground(Color.white);
        button1.setBounds(150, 290, 100, 35);
        button1.addActionListener(this);

        //---- label1 ----
        label1.setText("\u6635\u79f0");
        label1.setHorizontalTextPosition(SwingConstants.CENTER);
        label1.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(label1);
        label1.setBounds(55, 135, 75, label1.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u4e2a\u6027\u7b7e\u540d");
        label2.setHorizontalTextPosition(SwingConstants.CENTER);
        label2.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(label2);
        label2.setBounds(55, 175, 75, 15);

        //---- label3 ----
        label3.setText("\u5bc6\u7801");
        label3.setHorizontalTextPosition(SwingConstants.CENTER);
        label3.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(label3);
        label3.setBounds(55, 215, 75, 15);

        //---- label4 ----
        label4.setText("\u786e\u8ba4\u5bc6\u7801");
        label4.setHorizontalTextPosition(SwingConstants.CENTER);
        label4.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(label4);
        label4.setBounds(55, 255, 75, label4.getPreferredSize().height);

        //---- label5 ----
        contentPane.add(label5);
        label5.setBounds(165, 15, 70, 70);

        //---- button2 ----
        button2.setText("\u9009\u62e9\u5934\u50cf");
        contentPane.add(button2);
        button2.setBackground(new Color(3, 37, 108));
        button2.setForeground(Color.white);
        button2.setBounds(160, 90, 80, button2.getPreferredSize().height);
        button2.addActionListener(this);

        contentPane.add(textField1);
        textField1.setBounds(145, 130, 200, textField1.getPreferredSize().height);
        contentPane.add(textField2);
        textField2.setBounds(145, 170, 200, 27);
        contentPane.add(textField3);
        textField3.setBounds(145, 210, 200, 27);
        contentPane.add(textField4);
        textField4.setBounds(145, 250, 200, 27);

        contentPane.setPreferredSize(new Dimension(400, 360));
        pack();
        setLocationRelativeTo(getOwner());
        setVisible(true);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JButton button1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private Avatar label5;
    private JButton button2;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField textField3;
    private JPasswordField textField4;

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == button1) { //选择的是提交按钮
            String nickName = textField1.getText();
            String sign = textField2.getText();
            String pwd = String.valueOf(textField3.getPassword());
            String againPwd = String.valueOf(textField4.getPassword());

//            HashMap<String,String> hashMap = new HashMap<>();
//            hashMap.put("nickName",nickName);
//            hashMap.put("sign",sign);
//            hashMap.put("pwd",pwd);
            //检验输入数据的合法性
            if (check(nickName, sign, pwd, againPwd)) {
                Register_mess register_mess = new Register_mess();
                register_mess.setSign(sign);
                register_mess.setPasswd(pwd);
                register_mess.setUsrid(nickName);
                register_mess.setAvatarID(avatarID);

                Socket socket = null;
                try {
                    socket = new Socket(InetAddress.getByName("127.0.0.1"),8089);
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(register_mess);
                    //接受服务端的成功创建消息

                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    try {
                        Message ms =(Message) ois.readObject();
                        if(ms.getMessage_type().equals(Message_type.MESSAGE_REGISTER_SUCESS)){
                            JOptionPane.showMessageDialog(this, "创建成功!");
                        }else if(ms.getMessage_type().equals(Message_type.MESSAGE_REGISTER_FAILED)){

                            JOptionPane.showMessageDialog(this, "账户已经存在!");
                        }

                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    //ms是来自服务端的消息类



                } catch (IOException ex) {
                    ex.printStackTrace();
                }finally {
                    try {
                        if (socket != null) {
                            socket.close();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }


                }


            } else if (source == button2) {   //选择的是头像选择按钮
            new SelectAvatar(this);
        }
    }


    //检验
    private Boolean check(String n, String s, String p, String ap) {
        if (n.equals("")) {
            JOptionPane.showMessageDialog(this, "昵称不能为空!");
            return false;
        }
        if (s.equals("")) {
            JOptionPane.showMessageDialog(this, "个性签名不能为空!");
            return false;
        }
        if (p.equals("") || ap.equals("")) {
            JOptionPane.showMessageDialog(this, "密码不能为空，请重新输入！");
            return false;
        }
        if (!p.equals(ap)) {
            JOptionPane.showMessageDialog(this, "密码输入错误，请重新输入！");
            return false;
        }
        return true;
    }



    class SelectAvatar extends JDialog implements ActionListener {
        public SelectAvatar(Window owner) {
            super(owner);
            initComponents();
            loadAvatar();
        }

        private void initComponents() {
            dialogPane = new JPanel();
            scrollPane1 = new JScrollPane();
            contentPanel = new JPanel();
            buttonBar = new JPanel();
            okButton = new JButton();

            //======== this ========
            setResizable(false);
            setVisible(true);
            Container contentPane = getContentPane();
            contentPane.setLayout(new BorderLayout());

            //======== dialogPane ========
            {
                dialogPane.setLayout(new BorderLayout());

                //======== scrollPane1 ========
                {

                    //======== contentPanel ========
                    {
                        contentPanel.setLayout(new GridLayout(0, 5, 2, 2));
                    }
                    scrollPane1.setViewportView(contentPanel);
                }
                dialogPane.add(scrollPane1, BorderLayout.CENTER);

                //======== buttonBar ========
                {
                    buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                    buttonBar.setLayout(new GridBagLayout());
                    ((GridBagLayout) buttonBar.getLayout()).columnWidths = new int[]{0, 80};
                    ((GridBagLayout) buttonBar.getLayout()).columnWeights = new double[]{1.0, 0.0};

                    //---- okButton ----
                    okButton.setText("OK");
                    okButton.addActionListener(this);
                    buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 0), 0, 0));
                }
                dialogPane.add(buttonBar, BorderLayout.PAGE_END);
            }
            contentPane.add(dialogPane, BorderLayout.CENTER);
            setSize(400, 300);
            setLocationRelativeTo(getOwner());

        }

        private void loadAvatar() { // 显示图像列表里的内容
            File file = new File("project/src/imges/avatar");
            File[] tempList = file.listFiles();
            ArrayList<Avatar> avatars = new ArrayList<Avatar>();
            for (File file1 : tempList) {
                if (file1.getName().endsWith(".png")) {
                    String name = file1.getName();
                    String avatarID = name.substring(0, name.length() - 4);
                    Avatar avatar = new Avatar(avatarID, 70, 70);
                    avatars.add(avatar);
                }
            }

            for (Avatar avatar : avatars) {
                avatar.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        for (Avatar avatar1 : avatars) {
                            avatar1.setBorder(null);
                        }
                        avatar.setBorder(new LineBorder(Color.black));
                        avatarID = avatar.getAvatarID();
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
                contentPanel.add(avatar);
            }
            contentPanel.revalidate();
            contentPanel.repaint();
        }
        private JPanel dialogPane;
        private JScrollPane scrollPane1;
        private JPanel contentPanel;
        private JPanel buttonBar;
        private JButton okButton;

        @Override
        public void actionPerformed(ActionEvent e) {
            label5.setAvatarID(avatarID);
            this.dispose();
        }
    }
}