/*
 * Created by JFormDesigner on Mon Jun 14 14:49:44 CST 2021
 */

package customer.chat_view;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author unknown
 */
public class Forget extends JFrame implements ActionListener {
    public Forget() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        button1 = new JButton();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        textField1 = new JTextField();
        passwordField1 = new JPasswordField();
        passwordField2 = new JPasswordField();
        passwordField3 = new JPasswordField();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- button1 ----
        button1.setText("\u63d0\u4ea4");
        contentPane.add(button1);
        button1.setBounds(145, 190, 110, 35);
        button1.setBackground(new Color(3, 37, 108));
        button1.setForeground(Color.white);
        button1.addActionListener(this);

        //---- label1 ----
        label1.setText("QQ\u53f7");
        label1.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(label1);
        label1.setBounds(70, 35, 65, label1.getPreferredSize().height);
        contentPane.add(textField1);
        textField1.setBounds(145, 30, 175, textField1.getPreferredSize().height);

        //---- label2 ----
        label2.setText("\u65e7\u5bc6\u7801");
        label2.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(label2);
        label2.setBounds(70, 75, 65, 15);

        //---- label3 ----
        label3.setText("\u65b0\u5bc6\u7801");
        label3.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(label3);
        label3.setBounds(70, 115, 65, 15);

        //---- label4 ----
        label4.setText("\u786e\u8ba4\u5bc6\u7801");
        label4.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(label4);
        label4.setBounds(70, 154, 65, 15);
        contentPane.add(passwordField1);
        passwordField1.setBounds(145, 70, 175, passwordField1.getPreferredSize().height);
        contentPane.add(passwordField2);
        passwordField2.setBounds(145, 110, 175, 27);
        contentPane.add(passwordField3);
        passwordField3.setBounds(145, 149, 175, 27);

        contentPane.setPreferredSize(new Dimension(400, 260));
        pack();
        setLocationRelativeTo(getOwner());
        setVisible(true);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JButton button1;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JPasswordField passwordField3;

 //   @Override
//    public void actionPerformed(ActionEvent e) {
//        String qq = textField1.getText();
//        String op = String.valueOf(passwordField1.getPassword());
//        String p = String.valueOf(passwordField2.getPassword());
//        String ap = String.valueOf(passwordField3.getPassword());
//        if (check(qq, op, p, ap)) {
//            System.out.println("检查通过");
//            Boolean aBoolean = new UserService().changPassword(Integer.parseInt(qq), p);
//            if (aBoolean) {
//                JOptionPane.showMessageDialog(this, "修改密码成功！");
//                this.dispose();
//            } else {
//                JOptionPane.showMessageDialog(this, "修改密码失败！");
//            }
//        }
//    }

    private Boolean check(String qq, String op, String p, String ap) {
        if (qq.equals("")) {
            JOptionPane.showMessageDialog(this, "QQ号不能为空！");
            return false;
        }
        if (qq.length() > 4) {
            JOptionPane.showMessageDialog(this, "QQ号格式错误，请重新输入！");
            return false;
        }
        if (op.equals("") || p.equals("") || ap.equals("")) {
            JOptionPane.showMessageDialog(this, "密码不能为空！");
            return false;
        }
        if (!p.equals(ap)) {
            JOptionPane.showMessageDialog(this, "密码错误，请重新输入！");
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
