package customer.chat_view;


import customer.chat_mess.user;
import customer.chat_view.component.Avatar;
import customer.client_service.user_login;
import customer.public_view.Public_data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    private user_login login = new user_login() ;
    private customer.chat_mess.user user = new user();
    private static final long serialVersionUID = 1L;

    private JLabel jlb_title;
    private JLabel jlb_north;
    private JLabel jlb_photo;
    private JTextField userName;
    private JTextField userId;
    private JPasswordField passWord;
    private JButton btn_login;
    private JLabel jlb_register;
    private JLabel jlb_forget;

    public static void main(String[] args) {
        new Login();
    }

    public Login() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 定义窗体的宽高
        int windowsWedth = 620;
        int windowsHeight = 500;

        // 获取此窗口容器 设置布局
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        // 设置标题
        jlb_title = new JLabel("盗版QQ");
        jlb_title.setFont(new Font("", Font.BOLD, 48));
        jlb_title.setBounds((windowsWedth - 340) / 2, 20, 340, 100);
        jlb_title.setForeground(Color.white);
        contentPane.add(jlb_title);

        // 处理背景图片标签
        jlb_north = new JLabel();
        jlb_north.setOpaque(true);
        jlb_north.setBackground(new Color(3, 37, 108));
        jlb_north.setBounds(0, 0, windowsWedth, 150);
        contentPane.add(jlb_north);

        // 处理账号密码框左边企鹅图片标签
        jlb_photo = new Avatar("tx3948", 80, 80);
        jlb_photo.setBounds(25, 170, 80, 80);
        contentPane.add(jlb_photo);


        userName = new JTextField();
        userName.setBounds((windowsWedth - 200) / 2 + 10, 170, 200, 30);
        String placeholder1 = "请输入账号";
        userName.setText(placeholder1);
        userName.setForeground(Color.GRAY);
        userName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //获取焦点时，清空提示内容
                String temp = userName.getText();
                if (temp.equals(placeholder1)) {
                    userName.setText("");
                    userName.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                //失去焦点时，没有输入内容，显示提示内容
                String temp = userName.getText();
                if (temp.equals("")) {
                    userName.setForeground(Color.GRAY);
                    userName.setText(placeholder1);
                }
            }
        });
        contentPane.add(userName);

        // 处理密码输入框
        passWord = new JPasswordField();
        passWord.setBounds((windowsWedth - 200) / 2 + 10, 220, 200, 30);
        String placeholder2 = "请输入密码";
        passWord.setEchoChar((char) 0);
        passWord.setText(placeholder2);
        passWord.setForeground(Color.GRAY);
        passWord.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //获取焦点时，清空提示内容
                String temp = String.valueOf(passWord.getPassword());
                if (temp.equals(placeholder2)) {
                    passWord.setText("");
                    passWord.setForeground(Color.BLACK);
                    passWord.setEchoChar('*');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                //失去焦点时，没有输入内容，显示提示内容
                String temp = String.valueOf(passWord.getPassword());
                if (temp.equals("")) {
                    passWord.setForeground(Color.GRAY);
                    passWord.setText(placeholder2);
                    passWord.setEchoChar((char) 0);
                }
            }
        });
        contentPane.add(passWord);

        // 注册账号
        jlb_register = new JLabel("<html><u>" + "注册账号" + "</u></html>");
        jlb_register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//设置鼠标样式
        jlb_register.setBounds(windowsWedth - 90, 170, 100, 30);
        jlb_register.setForeground(new Color(3, 37, 108));
        jlb_register.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Register();
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
        contentPane.add(jlb_register);

        // 忘记密码
        jlb_forget = new JLabel("<html><u>" + "忘记密码" + "</u></html>");
        jlb_forget.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//设置鼠标样式
        jlb_forget.setBounds(windowsWedth - 90, 220, 200, 30);
        jlb_forget.setForeground(new Color(3, 37, 108));
        jlb_forget.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Forget();
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
        contentPane.add(jlb_forget);

        // 处理南部登录按钮
        btn_login = new JButton("登录");
        btn_login.setForeground(Color.white);
        btn_login.setBackground(new Color(3, 37, 108));
        btn_login.setBounds((windowsWedth - 200) / 2 + 10, 260, 200, 30);
        btn_login.addActionListener(this);
        contentPane.add(btn_login);

        this.setSize(windowsWedth, windowsHeight);// 设置窗体大小'
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);// 设置窗体可见
    }

    /**
     * 点击登录进行处理
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_login) {//点击登录
            // 获取账号密码
            String qq = userName.getText().trim();
            String pwd = new String(passWord.getPassword());

            if (!qq.equals("请输入QQ号") && !pwd.equals("请输入密码") && qq.length() <= 4) {
                   if(login.check(qq,pwd)){
//                       user.setPasswd(pwd);
//                       user.setUsrid(qq);//登录成功，即客户存在，（已经吧和服务器的通信线存进了MangerClientThread里面了）
                       //再建立一个socket回收

                       //创建新的列表是视图
                       login.getU();
                       Public_data.setUser_login(login);
                       Public_data.getUser_login().get_chat_list();

                       try {
                           Thread.sleep(2000);
                           user = Public_data.getUser(); //获取用户完整信息
                           FriendList friendList = new FriendList(user,login);// 创建好友列表主界面



                       } catch (InterruptedException ex) {
                           ex.printStackTrace();
                       }



//                       friendList.updateFriendList();// 获取好友列表



                        //关闭登录界面
                       this.dispose();
                   }else {

                       JOptionPane.showMessageDialog(this, "登录信息有误，请认真检查！");
                   }

//               }else {
//                   JOptionPane.showMessageDialog(this, "无法连接你呢到服务器！");
//
//               }


//
//                // 检查与服务器的连接
//                UserService userService = new UserService();
//                Socket client = userService.getClient();
//                if (client != null && !client.isClosed()) {
//                    // 将登录消息发送至服务器
//                    Message msg = userService.login(Integer.parseInt(qq), pwd);
//                    // 处理服务器返回的结果
//                    User loginUser = null;
//                    switch (msg.getType()) {
//                        case USER_LOGIN:// 登录成功
//                            // 创建与服务器通信的线程
//                            loginUser = (User) msg.getContent();
//                            ClientThread clientThread = new ClientThread(userService.getClient());
//                            clientThread.start();
//                            ThreadManage.addThread(loginUser.getQq(), clientThread);
//
//                            // 将用户信息和好友列表控件保存至通信线程中
//                            ClientThread thread = ThreadManage.getThread(loginUser.getQq());
//                            FriendList friendList = new FriendList(loginUser);// 创建好友列表主界面
//                            thread.setFriendList(friendList);
//                            thread.setUser(loginUser);
//                            friendList.updateFriendList();// 获取好友列表
//
//                            this.dispose();// 关闭登录界面
//                            break;
//                        case SIGNED_IN:// 用户已经登录
//                            JOptionPane.showMessageDialog(this, "用户已经登录！");
//                            userService.myStop();
//                            break;
//                        case INFO_ERROR:// qq或密码错误
//                            JOptionPane.showMessageDialog(this, "qq或密码错误！");
//                            userService.myStop();
//                            break;
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(this, "无法连接服务器！");
//                }
            } else {
                JOptionPane.showMessageDialog(this, "请输入正确的QQ号和密码！");
            }
        }
    }

}