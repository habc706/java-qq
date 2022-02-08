package customer.client_service;

import customer.chat_mess.Message;
import customer.chat_mess.Message_type;
import customer.chat_mess.chat;
import customer.chat_mess.user;
import customer.public_view.Public_data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

//登陆验证和注册等,通过check()方法传递参数后生成user类，再传递给服务端，服务端接受该类并验证
public class user_login {
    //消息对象
    private user u = new user();
    //
    private Socket socket;
    //根据id和密码检测返回是否成功登录,顺便把user对象初始化了
    //验证登录是否成功，true false
    public boolean check(String userid,String pwd){
        boolean islogin = false;
        u.setUsrid(userid);
        u.setPasswd(pwd);
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"),8089);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u); //对象写进user类

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message ms =(Message) ois.readObject();
            //ms是来自服务端的消息类
            if(ms.getMessage_type().equals(Message_type.MESSAGE_LOGIN_SUCCEED)){
                //登录成功，启用线程把握socket
                ClientConnectServerThread ccst = new ClientConnectServerThread(socket);

                ccst.start();
                ManagerClientThread.addClientServiceThread(userid,ccst);
                islogin = true;
            }else {
                //登陆失败的话，
                socket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            //无法连接服务器的情况

        }

        return islogin;
    }

    public void getuser(String usrid){
        Message message = new Message();
        message.setMessage_type(Message_type.MESSAGE_GET_USER);
        message.setSender(usrid);
        try {
            //得到当前,（根据usrid 去hashmap中取得线程）集合->线程->socket->输出流
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManagerClientThread.getclientthread(u.getUsrid()).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public  void onlinFriendlist(){
        Message message = new Message();
        message.setMessage_type(Message_type.MESSAGE_GET_ONLINE_FRIEND);
        message.setSender(u.getUsrid()); //发送者信息

        try {
            //得到当前,（根据usrid 去hashmap中取得线程）集合->线程->socket->输出流
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManagerClientThread.getclientthread(u.getUsrid()).getSocket().getOutputStream());
            oos.writeObject(message);

            //


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    //向服务端发送下线信息
    public void logout(){
        Message message =new Message();
        message.setMessage_type(Message_type.MESSAGE_CLIENT_EXIT);
        message.setSender(u.getUsrid());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);
            System.out.println("客户端退出系统");
            System.exit(0);  //
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getU(){
        Message message = new Message();
        message.setSender(u.getUsrid());
        message.setMessage_type(Message_type.MESSAGE_GET_USER);

        try {
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManagerClientThread.getclientthread(u.getUsrid()).getSocket().getOutputStream());
            oos.writeObject(message);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void update_friend_list(String usrid){
        Message message = new Message();
        message.setMessage_type(Message_type.MESSAGE_GET_FRIEND_LIST);
        message.setSender(usrid);
        try {
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManagerClientThread.getclientthread(u.getUsrid()).getSocket().getOutputStream());
            oos.writeObject(message);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void add_friend(String friend_id)
    {
        Message message = new Message();
        message.setMessage_type(Message_type.MESSAGE_ADD_FRIEND);
        message.setSender(u.getUsrid());
        message.setFriend_id(friend_id);
        try {
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManagerClientThread.getclientthread(u.getUsrid()).getSocket().getOutputStream());
            oos.writeObject(message);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //发送一个普通的消息
    public void sent_mes(String receiver, chat chat_news){
        Message message = new Message();
        message.setMessage_type(Message_type.MESSAGE_COMM_MES);
        message.setSender(u.getUsrid());
        message.setReceiver(receiver);
        Date date = new Date();
        DateFormat longDateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String format = longDateFormat.format(date);
        chat_news.setSendTime(format);
//这里是更新本地的消息数据----------------------============================
        System.out.println("向"+receiver+"发送消息");

        HashMap<String, ArrayList<chat>> chat_list = new HashMap<>();
        Iterator iterator = Public_data.get_chatnews(receiver).iterator();
        while (iterator.hasNext()){
            //
            String me_and_friend = iterator.next().toString();
            System.out.println(Public_data.get_chatnews(me_and_friend));
            //if(me_and_friend.contains(receiver)){
                System.out.println(me_and_friend);
//                String key =  me_and_friend.replaceAll(sender,"");
//                ArrayList<chat> arrayList = qqservice.get_Chat_lists(sender,key);
//                chat_list.put(key,arrayList);

        }
        Public_data.get_chatnews(receiver).add(chat_news);

        message.setChat_news(chat_news);
        try {
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManagerClientThread.getclientthread(u.getUsrid()).getSocket().getOutputStream());
            oos.writeObject(message);


        } catch (IOException e) {
            e.printStackTrace();
        }




    }
//发送请求所有消息======================
    public void get_chat_list(){
        Message message = new Message();
        message.setSender(u.getUsrid());

        message.setMessage_type(Message_type.MESSAGE_GET_CHAT_LISTS);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void say_to_all(String content){
        Message message = new Message();
        message.setMessage_type(Message_type.MESSAGE_SAY_TO_ALL);
        message.setContent(content);
        message.setSender(u.getUsrid());
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void get_offline_mes(){
        Message message = new Message();
        message.setSender(u.getUsrid());

        message.setMessage_type(Message_type.MESSAGE_GET_OFFLINE_MESS);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(message);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
