package customer.chat_view.component;

//import entity.User;
//import tools.ChatViewManage;
//import view.ChatView;

import customer.chat_mess.user;
import customer.chat_view.ChatView;
import customer.client_service.ChatViewManger;
import customer.public_view.Public_data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FriendPanel extends JPanel implements MouseListener {

    private final int panelHeight = 60;
    private final int panelWidth = 280;
    private JLabel avatar;
    private JLabel nickname;
    private JLabel sign;

    private customer.chat_mess.user user;
    private user friend;


    public FriendPanel(user user, user friend) {
        this.user = user;
        this.friend = friend;

        // 好友面板
        this.setLayout(null);
        this.setBackground(Color.white);
        this.setSize(panelWidth, panelHeight);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        this.addMouseListener(this);

        // 头像
        avatar = new Avatar(friend.getAvatarID(), 40, 40);
        avatar.setBounds(10, 10, 40, 40);

        // 昵称
        nickname = new JLabel();
        nickname.setBounds(60, 10, 210, 18);
        nickname.setForeground(new Color(0, 0, 0));
        nickname.setText(friend.getUsrid());

        // 签名
        sign = new JLabel();
        sign.setBounds(60, 30, 210, 18);
        sign.setForeground(new Color(153, 153, 153));
        sign.setText(friend.getSign());

        this.add(avatar);
        this.add(nickname);
        this.add(sign);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // 检测鼠标右键单击
        if (e.getClickCount() == 1) {
            if(ChatViewManger.getChatFrame(friend.getUsrid())!=null){
                System.out.println("窗口已经存在");

            }else {
                ChatView chatView = new ChatView(Public_data.getUser(), friend);
                ChatViewManger.addChatFrame(friend.getUsrid(), chatView);

            }


        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JPanel jPanel = (JPanel) e.getSource();
        jPanel.setBackground(new Color(220, 220, 220));

    }

    @Override
    public void mouseExited(MouseEvent e) {
        JPanel jPanel = (JPanel) e.getSource();
        jPanel.setBackground(Color.white);

    }

}
