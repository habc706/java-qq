package customer.chat_view;

import customer.client_service.user_login;

public class windows {
    private boolean loop = true;  //登录界面，如果未登录或登录失败就一直显示界面
    private user_login login = new user_login();

    public static void main(String[] args) {
        new windows().mainmenu();
    }
    private String usrid = "100"; //接受键盘输入
    private String passwd = "123456";
//     vulidusers.put("100",new user("100","123456"));
//        vulidusers.put("101",new user("101","123457"));
//        vulidusers.put("102",new user("102","123458"));
    void mainmenu(){
        if(login.check(usrid,passwd)){
            System.out.println("chenggong");
            System.out.println("100用户窗口");
            //login.onlinFriendlist();
           // login.say_to_all("102来问大家都还好");
            //login.sent_mes("100","101问100号,你好ma,100号");
            login.get_offline_mes();
            //System.exit(0);



        }

    }


}
