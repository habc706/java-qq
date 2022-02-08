package customer.chat_view.component;

import javax.swing.*;
import java.awt.*;

public class ChatBubble extends JPanel {
    private String nickName;
    private String time;
    private String content;

    public ChatBubble() {
        initComponents();
    }



    public ChatBubble(String nickName, String time, String content) {
        this.nickName = nickName;
        this.time = time;
        this.content = content;
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        label2 = new JLabel();

        //======== this ========
        setLayout(null);

        //---- label1 ----

        label1.setText(nickName + "[" +time+ "]");
        add(label1);
        label1.setBounds(0, 0, 300, label1.getPreferredSize().height);

        //---- label2 ----
        label2.setText(content);
        label2.setFont(new Font(".AppleSystemUIFontMonospaced", Font.PLAIN, 16));
        add(label2);
        label2.setBounds(0, 20, 300, 20);

        setPreferredSize(new Dimension(300, 40));
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
