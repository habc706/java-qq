package customer.chat_view;

//应该在这个界面添加一个退出后停止线程的操作！！！！！！

import customer.chat_mess.user;
import customer.chat_view.component.Avatar;
import customer.chat_view.component.FriendPanel;
import customer.client_service.user_login;
import customer.public_view.Public_data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class FriendList extends JFrame {

    private static final long serialVersionUID = 1L;

    private  int windowsWedth = 300;
    private  int windowsHeight = 600;
    private  Container container = this.getContentPane();// 本窗口面板
    private  JPanel friendList;// 好友列表
    private  JScrollPane jScrollPane;
    private JPanel bottom;// 底部面板
    private JButton addFriend;// 添加好友
    private user user;// 用户对象
    private user_login user_login;

    public static void main(String[] args) {
        //new FriendList(new user());
    }
    public FriendList(user user,user_login user_login) {
        this.user_login = user_login;
        this.user = user;
        this.setSize(windowsWedth, windowsHeight);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //先向服务器发起请求获去用户详细信息

        try {
            updateFriendList();
            Thread.sleep(1000);
            // 获取本窗体容器设置布局
            container = this.getContentPane();
            container.setLayout(null);

            //发现这里是null
            JLabel jbl_photo = new Avatar(user.getAvatarID(), 80, 80);
            jbl_photo.setBounds(20, 22, 80, 80);
            container.add(jbl_photo);

            // qq昵称
            JLabel jbl_qqName = new JLabel(user.getUsrid());
            jbl_qqName.setForeground(Color.white);
            jbl_qqName.setFont(new Font("", Font.BOLD, 18));
            jbl_qqName.setBounds(120, 30, 140, 20);
            container.add(jbl_qqName);

            // 签名
            JTextField sign = new JTextField();
            sign.setBounds(120, 60, 140, 22);
            sign.setBackground(new Color(3, 37, 108));
            sign.setForeground(Color.white);
            sign.setBorder(null);
            sign.setText(user.getSign());
            container.add(sign);

            //上半部分背景图
            JLabel jbl_background = new JLabel();
            jbl_background.setBackground(new Color(3, 37, 108));
            jbl_background.setOpaque(true);
            jbl_background.setBounds(0, 0, windowsWedth, 128);
            container.add(jbl_background);

            // 得到显示器屏幕的宽高设置窗体居中
            int width = Toolkit.getDefaultToolkit().getScreenSize().width;
            int height = Toolkit.getDefaultToolkit().getScreenSize().height;
            this.setBounds((width - windowsWedth) / 2, (height - windowsHeight) / 2, 0, 0);
            this.setMinimumSize(new Dimension(windowsWedth, windowsHeight));

            // 添加好友按钮
            addFriend = new JButton("添加好友");
            addFriend.setBackground(new Color(3, 37, 108));
            addFriend.setForeground(Color.white);

            // 窗体底部面板
            bottom = new JPanel(new GridLayout(1, 0));
            bottom.setBounds(0, 542, windowsWedth, 30);
            bottom.add(addFriend);
            container.add(bottom);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        addFriend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddFriend();
            }
        });

        this.addWindowListener(new WindowAdapter() {
            //向服务器发送离线消息
            @Override
            public void windowClosing(WindowEvent e) {
                //程序正常退出
                user_login.logout();
                System.exit(0);
            }
        });
        showFriendList(Public_data.getFriendlist());

        //设置窗体可见
        this.setVisible(true);
    }

    public void updateFriendList() {
//        // 向服务器请求好友列表
//
        user_login.update_friend_list(Public_data.getUser().getUsrid());

            friendList = new JPanel();
            friendList.setLayout(null);

            if (jScrollPane != null) {
                container.remove(jScrollPane);
            }
            jScrollPane = new JScrollPane();
            jScrollPane.setLocation(0, 128);
            jScrollPane.setViewportView(friendList);
            jScrollPane.setSize(windowsWedth, windowsHeight - 184);
            jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);// 需要时显示（默认）
            jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);// 从不显示
            container.add(jScrollPane);


    }


    //在这里传入Public_data里面的参数就好，先把原来的移除
    public void showFriendList(ArrayList<user> list) {
        int panelHeight = 60;
        friendList.removeAll();

        for (int i = 0; i < list.size(); i++) {
            user friend = list.get(i);
            // 创建好友面板
            JPanel friendPanel = new FriendPanel(Public_data.getUser(), friend);
            friendPanel.setLocation(0, i * panelHeight);
            friendList.add(friendPanel);
        }

        friendList.setPreferredSize(new Dimension(windowsWedth, list.size() * panelHeight));
        container.repaint();
    }



    class AddFriend extends JFrame implements ActionListener {
        public AddFriend() {
            initComponents();
        }

        private void initComponents() {
            // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            // Generated using JFormDesigner Evaluation license - unknown
            label1 = new JLabel();
            textField1 = new JTextField();
            button1 = new JButton();

            //======== this ========
            setVisible(true);
            Container contentPane = getContentPane();
            contentPane.setLayout(null);

            //---- label1 ----
            label1.setText("\u597d\u53cbQQ\u53f7");
            contentPane.add(label1);
            label1.setBounds(new Rectangle(new Point(70, 35), label1.getPreferredSize()));
            contentPane.add(textField1);
            textField1.setBounds(140, 30, 200, textField1.getPreferredSize().height);

            //---- button1 ----
            button1.setText("\u6dfb\u52a0");
            contentPane.add(button1);
            button1.setBackground(new Color(3, 37, 108));
            button1.setForeground(Color.white);
            button1.setBounds(160, 75, 80, button1.getPreferredSize().height);
            button1.addActionListener(this);

            contentPane.setPreferredSize(new Dimension(400, 140));
            pack();
            setLocationRelativeTo(getOwner());
        }

        private JLabel label1;
        private JTextField textField1;
        private JButton button1;


        @Override  //添加好友
        public void actionPerformed(ActionEvent e) {
            String text = textField1.getText();
            if (text.equals("")) {
                JOptionPane.showMessageDialog(this, "请输入QQ号！");
            } else if (text.length() > 4) {
                JOptionPane.showMessageDialog(this, "请输入正确的QQ号！");
            } else {

                  text = text.trim();

                try {
                    user_login.add_friend(text);
                    Thread.sleep(1000);
                    if(Public_data.isIf_add_ok()){
                        JOptionPane.showMessageDialog(this, "添加成功！");
                        updateFriendList();
                        showFriendList(Public_data.getFriendlist());
                        //添加好友之后
                       // Public_data.getUser_login().
                    }else {
                        JOptionPane.showMessageDialog(this, "添加失败！");
                    }




                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                //添加好友后再展示好友列表


            }
        }
    }

}