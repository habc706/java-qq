package customer.chat_view;


import customer.chat_mess.chat;
import customer.chat_mess.user;
import customer.chat_view.component.Avatar;
import customer.chat_view.component.ChatBubble;
import customer.client_service.ChatViewManger;
import customer.public_view.Public_data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChatView extends JFrame implements ActionListener, WindowListener {

    private user me;
    private user friend;
    public ChatView(user me,user friend){

        this.friend=friend;
        this.me = Public_data.getUser();
        initComponents();
        getChatList();
        showChats(Public_data.get_chatnews(friend.getUsrid()));

        this.addWindowListener(new WindowAdapter() {
            //向服务器发送离线消息
            @Override
            public void windowClosing(WindowEvent e) {
                //程序正常退
                ChatViewManger.removeChatFrame(friend.getUsrid());
            }
        });

    }

    private void getChatList() {   //初始化历史记录
        //        Message message = new Message(MessageType.GET_CHAT_LIST);
//        ArrayList<User> users = new ArrayList<>();
//        users.add(user);
//        users.add(friend);
//        message.setContent(users);
//        ClientThread thread = ThreadManage.getThread(user.getQq());
//        thread.send(message);



    }


    /**
     * 获取聊天记录
     */


    /**
     * 显示聊天记录
     */
    public void showChats(ArrayList<chat> list) {
        textPane1.removeAll();
        for (chat chat : list) {

            String nickName = (chat.getSenderQQ().equals(me.getUsrid())   ? me.getUsrid() : friend.getUsrid());
            String time = chat.getSendTime();
            String content = chat.getContent();
            ChatBubble chatBubble = new ChatBubble(nickName, time, content);
            textPane1.add(chatBubble);
        }
        textPane1.updateUI();
        scrollToBottom();
    }

    /**
     * 聊天内容区域滚动到底部
     */
    public void scrollToBottom() {
        JScrollBar verticalScrollBar = scrollPane1.getVerticalScrollBar();
        // 不用SwingUtilities.invokeLater的话会报错
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                verticalScrollBar.updateUI();
                verticalScrollBar.setValue(verticalScrollBar.getMaximum());
            }
        });
    }

    /**
     * 接收聊天消息
     */
//    public void receiveChat(Chat chat) {
//        String nickName = chat.getSenderQQ() == user.getQq() ? user.getNickname() : friend.getNickname();
//        Long time = chat.getSendTime();
//        String content = chat.getContent();
//        ChatBubble chatBubble = new ChatBubble(nickName, time, content);
//        textPane1.add(chatBubble);
//        textPane1.updateUI();
//        scrollToBottom();
//    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        panel1 = new JPanel();
        label1 = new Avatar(friend.getAvatarID(), 60, 60);
        label2 = new JLabel();
        label3 = new JLabel();
        scrollPane1 = new JScrollPane();
        textPane1 = new JPanel();
        panel2 = new JPanel();
        button1 = new JButton();
        button2 = new JButton();
        scrollPane2 = new JScrollPane();
        textPane2 = new JTextPane();
        panel3 = new JPanel();
        button3 = new JButton();
        button4 = new JButton();

        //======== this ========
        setVisible(true);
        setBackground(new Color(204, 204, 204));
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        this.addWindowListener(this);

        //======== panel1 ========
        {
            panel1.setBackground(new Color(3, 37, 108));
            panel1.setLayout(null);

            //---- label1 ----
            panel1.add(label1);   //=========================================-0-0-0--0-0-0-0-0-0-00-0-
            label1.setBounds(10, 10, 60, 60);

            //---- label2 ----
           // label2.setText(friend.getNickname());
            label2.setFont(new Font(".AppleSystemUIFontMonospaced", Font.PLAIN, 22));
            label2.setForeground(Color.white);
            panel1.add(label2);
            label2.setBounds(80, 14, 300, label2.getPreferredSize().height);

            //---- label3 ----
          //  label3.setText(friend.getSign());
            label3.setForeground(new Color(204, 204, 204));
            panel1.add(label3);
            label3.setBounds(80, 46, 300, label3.getPreferredSize().height);
        }
        contentPane.add(panel1);
        panel1.setBounds(0, 0, 400, 80);

        //======== scrollPane1 ========
        {
            textPane1.setLayout(new GridLayout(0, 1));
            scrollPane1.setViewportView(textPane1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(0, 80, 400, 200);

        //======== panel2 ========
        {
            panel2.setLayout(null);

            //---- button1 ----
            button1.setText("\u56fe\u7247");
            panel2.add(button1);
            button1.setBounds(5, 0, 60, 30);
            button1.addActionListener(this);

            //---- button2 ----
            button2.setText("\u6587\u4ef6");
            panel2.add(button2);
            button2.setBounds(65, 0, 60, 30);
            button2.addActionListener(this);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < panel2.getComponentCount(); i++) {
                    Rectangle bounds = panel2.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel2.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel2.setMinimumSize(preferredSize);
                panel2.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel2);
        panel2.setBounds(0, 280, 400, 30);

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(textPane2);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(0, 310, 400, 120);

        //======== panel3 ========
        {
            panel3.setLayout(null);

            //---- button3 ----
            button3.setText("\u53d1\u9001");
            button3.setBackground(new Color(3, 37, 108));
            button3.setForeground(Color.white);
            panel3.add(button3);
            button3.setBounds(315, 5, 80, 30);
            button3.addActionListener(this);

            //---- button4 ----
            button4.setText("\u5173\u95ed");
            button4.addActionListener(this);
            panel3.add(button4);
            button4.setBounds(230, 5, 80, 30);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < panel3.getComponentCount(); i++) {
                    Rectangle bounds = panel3.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel3.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel3.setMinimumSize(preferredSize);
                panel3.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(panel3);
        panel3.setBounds(0, 430, 400, 40);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private static JScrollPane scrollPane1;
    private JPanel textPane1;
    private JPanel panel2;
    private JButton button1;
    private JButton button2;
    private JScrollPane scrollPane2;
    private JTextPane textPane2;
    private JPanel panel3;
    private JButton button3;
    private JButton button4;

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        if (source == button3) {
            // 消息不能为空
            if (!textPane2.getText().equals("")) {

                chat chat = new chat( );
                chat.setContent(textPane2.getText());
                chat.setReceiveQQ(friend.getUsrid());
                chat.setSenderQQ(Public_data.getUser().getUsrid());
               // chat.setSendTime(System.currentTimeMillis());
                //把消息发送到服务器

                Public_data.getUser_login().sent_mes(friend.getUsrid(), chat);


                //这里重新绘画视图,,,发现这里时空值
                showChats(Public_data.get_chatnews(friend.getUsrid()));


                textPane2.setText("");

//    更新消息列表


            } else {
                JOptionPane.showMessageDialog(this, "消息不能为空！");
            }
        } else if (source == button4) {
            this.dispose();// 关闭界面
            //ChatViewManage.removeChatFrame(friend.getQq());
        } else if (source == button1) {
            JOptionPane.showMessageDialog(this, "暂未实现该功能！");
        } else if (source == button2) {
            JOptionPane.showMessageDialog(this, "暂未实现该功能！");
        }
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
       // ChatViewManage.removeChatFrame(friend.getQq());
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
