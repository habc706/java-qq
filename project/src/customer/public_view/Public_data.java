package customer.public_view;

import customer.chat_mess.chat;
import customer.chat_mess.user;

import java.util.ArrayList;
import java.util.HashMap;

public class Public_data {
    public static user user;
    public static ArrayList<user> friendlist = new ArrayList<>();
    public  static  boolean if_add_ok = false;
    public  static customer.client_service.user_login user_login;
    public static HashMap<String,ArrayList<chat>> Total_chat_lists =new HashMap<>();



    public static customer.client_service.user_login getUser_login() {
        return user_login;
    }


    //初始化客户端消息内容，在刚登陆时调用 , 需要遍历整个朋友列表
    public static void setTotal_chat_lists(HashMap<String, ArrayList<chat>> total_chat_lists) {
        Total_chat_lists = total_chat_lists;
    }

    //添加与某个朋友的消息类型
    public static void add_chat(String friend,chat chat){
        Total_chat_lists.get(friend).add(chat);
        System.out.println("添加信息成功");
    }

    //获取与某一个好友的消息内容
    public static ArrayList<chat> get_chatnews(String friend){

        return Total_chat_lists.get(friend);
    }

    public static void setUser_login(customer.client_service.user_login user_login) {
        Public_data.user_login = user_login;
    }

    public static boolean isIf_add_ok() {
        return if_add_ok;
    }

    public static void setIf_add_ok(boolean if_add_ok) {
        Public_data.if_add_ok = if_add_ok;
    }

    public void add_friend(user u){
        friendlist.add(u);
    }

    public static customer.chat_mess.user getUser() {
        return user;
    }

    public static void setUser(customer.chat_mess.user user) {
        Public_data.user = user;
    }

    public static ArrayList<customer.chat_mess.user> getFriendlist() {
        return friendlist;
    }

    public static void setFriendlist(ArrayList<customer.chat_mess.user> friendlist) {
        Public_data.friendlist = friendlist;

    }




}
