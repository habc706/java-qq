package customer.chat_view.component;

import javax.swing.*;
import java.awt.*;

/**
 * 封装JLabel 实现头像显示，
 */
public class Avatar extends JLabel {

    private String avatarID;
    private int width;
    private int height;

    public Avatar(String avatarID, int width, int height) {
        this.avatarID = avatarID;
        this.width = width;
        this.height = height;

        ImageIcon imageIcon = new ImageIcon("project/src/imges/avatar/" + avatarID + ".png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        this.setIcon(imageIcon);
    }

    public String getAvatarID() {
        return avatarID;
    }

    public void setAvatarID(String avatarID) {  //project/src/imges/avatar/   imges/avatar/tx3948.png
        this.avatarID = avatarID;
        ImageIcon imageIcon = new ImageIcon("project/src/imges/avatar/" + avatarID + ".png");
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        this.setIcon(imageIcon);
        this.repaint();
    }

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
