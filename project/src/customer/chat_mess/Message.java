package customer.chat_mess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

//客户端和服务器通讯是的消息对象
public class Message implements Serializable {
    private String content;//内容
    private String sender;
    private  String receiver;
    private String time;
    private String message_type;
    private HashMap<String,String> register_Map;
    private ArrayList<user> friendlist;
    private user user;
    private String friend_id;
    private chat chat_news;  //传单个记录的时候使用
    private HashMap<String,ArrayList<chat>> chat_lists; //获取聊天记录的时候使用

    public HashMap<String, ArrayList<chat>> getChat_lists() {
        return chat_lists;
    }

    //客户端调用
    public void setChat_lists(HashMap<String, ArrayList<chat>> chat_lists) {
        this.chat_lists = chat_lists;
    }



    public chat getChat_news() {
        return chat_news;
    }

    public void setChat_news(chat chat_news) {
        this.chat_news = chat_news;
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
    }

    public customer.chat_mess.user getUser() {
        return user;
    }

    public void setUser(customer.chat_mess.user user) {
        this.user = user;
    }

    public ArrayList<user> getFriendlist() {
        return friendlist;
    }

    public HashMap<String, String> getRegister_Map() {
        return register_Map;
    }

    public void setRegister_Map(HashMap<String, String> register_Map) {
        this.register_Map = register_Map;
    }

    public void setFriendlist(ArrayList<user> friendlist) {
        this.friendlist = friendlist;
    }



    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }




    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
