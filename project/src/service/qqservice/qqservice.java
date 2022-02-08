package service.qqservice;

import customer.chat_mess.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

//接受来自客户端的信息，，监听
public class qqservice {
    private ServerSocket ss = null;   //
    private static ConcurrentHashMap<String ,user> vulidusers=new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String,ArrayList<user>> Total_friend_list = new ConcurrentHashMap<>();
    private static ArrayList<String> On_line_users = new ArrayList<>();

    public static ConcurrentHashMap<String, ArrayList<chat>> getAll_people_news() {
        return All_people_news;
    }

    private static ConcurrentHashMap<String ,ArrayList<chat>> All_people_news =new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, ArrayList<Message>> offlin_mes=new ConcurrentHashMap<>();

    //存储的是所有聊天信息记录  两个名字相接作为key？

    public static void remove_online_people(String uid)
    {
        On_line_users.remove(uid);

    }
    public static void add_init_chatnews(String user,String friend){
        ArrayList<chat> arrayList = new ArrayList<>();
        All_people_news.put(user+friend,arrayList);
    }
    //添加新的消息
    public static void add_chat_news(String sender,String receiver,chat chat){
        String fin_total1 = sender+receiver;
        String fin_total2 = receiver + sender;
        ArrayList<chat> c1 = All_people_news.get(fin_total1);
        if(c1 == null){
            ArrayList<chat> c2 = All_people_news.get(fin_total2);
            c2.add(chat);

        }else {
            c1.add(chat);
        }

    }

    //根据两个用户id查询消息列表
    public  static  ArrayList<chat> get_Chat_lists(String user,String friend){
        String fin_total1 = user+friend;
        String fin_total2 = user+friend;
        ArrayList<chat> c1 = All_people_news.get(fin_total1);
        if(c1 == null){
            ArrayList<chat> c2 = All_people_news.get(fin_total2);
            if(c2==null)
            {
                ArrayList<chat> arrayList = new ArrayList<>();
                return arrayList;
            }

        }
            return c1;



    }


    public  static  void add_online_people(String pid){
        On_line_users.add(pid);

    }



    public  static  user  get_people_by_id(String id){
        return vulidusers.get(id);
    }

    public static void add_friend(String user,user friend){
        ArrayList<customer.chat_mess.user> users = Total_friend_list.get(user);
        if(users!=null)
        users.add(friend);
        else
            System.out.println("can not add friend beacuse do not haave such user");
    }


    public static  ArrayList<user> get_friends(String user){

        return Total_friend_list.get(user);
    }



    public static void add_offline_mes(String receiver,Message message){
        offlin_mes.get(receiver).add(message);

    }
    public static ArrayList<Message> get_offline_mes(String usrid){
        return offlin_mes.get(usrid);
    }
    public static ConcurrentHashMap<String ,user> get_all_people(){
        return vulidusers;
    }
    //ConcurrentHashMap可以处理并发,线程安全
    //临时配置的密码       图像范围：tx3948 - tx4058

    //查看消息列表中是否存在这个key，有则返回该值，否则返回""
    public static String if_exists_chats(String user,String friend){
        String s1 = user+friend;
        String s2 = friend+user;
        //
        if(All_people_news.get(s1)!=null)
            return s1;
        if(All_people_news.get(s2)!=null)
            return s2;
        return  "";
    }


    static {

        user u1 = new user();
        user u2 = new user();
        user u3 = new user();
        u1.setUsrid("小明"); u1.setPasswd("123456"); u1.setAvatarID("tx3948"); u1.setSign("hello world!");
        u2.setUsrid("大明"); u2.setPasswd("123456"); u2.setAvatarID("tx3949"); u2.setSign("hello world!");
        u3.setUsrid("小红"); u3.setPasswd("123456"); u3.setAvatarID("tx3950"); u3.setSign("hello world!");

        ArrayList<user> list1 = new ArrayList<>();ArrayList<user> list2 = new ArrayList<>();
        ArrayList<user> list3 = new ArrayList<>();
        list1.add(u2);list1.add(u3);  list2.add(u1);list2.add(u3); list3.add(u1);list3.add(u2);
        Total_friend_list.put("小明",list1);
        Total_friend_list.put("大明",list2);
        Total_friend_list.put("小红",list3);


        vulidusers.put("小明",u1);
        vulidusers.put("大明",u2);
        vulidusers.put("小红",u3);

        String s1 = "小明大明"; String s2 = "小明小红" ; String s3 = "大明小红";
        ArrayList<chat> arrayList =new ArrayList<>(); ArrayList<chat> arrayList2 =new ArrayList<>(); ArrayList<chat> arrayList3 =new ArrayList<>();

        All_people_news.put(s1,arrayList);
        All_people_news.put(s2,arrayList2);
        All_people_news.put(s3,arrayList3);

    }





    private boolean checkuser(String userid,String passwd){
        user u = vulidusers.get(userid);
        if(On_line_users.contains(userid)){  //如果包含就说明已经登陆了
            return false;
        }

        if(!u.getPasswd().equals(passwd)){
            return false;
        }
        return true;
    }
    private  boolean checkuser2(String usrid){
        user u = vulidusers.get(usrid);
        if(u == null){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        new qqservice();
    }

    public qqservice() {

        try {
            ss = new ServerSocket(8089);
            while (true){
                Socket socket = ss.accept();//在这里被阻塞，不同的用户生成不同的线程，交给那些线程处理信息
                ObjectInputStream objectInputStream =
                        new ObjectInputStream((socket.getInputStream()));

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());


                Object judge =  objectInputStream.readObject();

                //接受对象后从数据库验证是否存在
                Message message = new Message();

                if(judge instanceof user){  //接受的是客户端的正常登录请求
                    user u = (user) judge;

                    if(checkuser(u.getUsrid(),u.getPasswd())){
                        message.
                                setMessage_type(Message_type.MESSAGE_LOGIN_SUCCEED);
//                        ArrayList<user> users = new ArrayList<>();
//                        users.add( vulidusers.get(u.getUsrid()) );
//                        message.setUserlist(users);

                        oos.writeObject(message);
                        //登录成功
                        //线程把持 当前一个用户的socket
                        add_online_people(u.getUsrid());
                        ServiceConnectThread serviceConnectThread = new ServiceConnectThread(socket, u.getUsrid());
                        serviceConnectThread.start();

                        ManagerClientThread.addthread(u.getUsrid(),serviceConnectThread);

                        System.out.println(u.getUsrid()+"登录成功");
                    }else {//登陆失败
                        message.setMessage_type(Message_type.MESSAGE_LOGIN_FAILED);
                        System.out.println("用户"+u.getUsrid()+"验证失败");
                        oos.writeObject(message);
                        socket.close();
                    }

                }
                else if(judge instanceof Register_mess){  // 接受的是客户端的注册请求
                    System.out.println("接受到了register对象");
                    Register_mess register_mess = (Register_mess) judge;
                    //这里处理的是把新用户添加进数据库
                    if(checkuser2(register_mess.getUsrid())){
                        //这里是还没有创建
                        String usrid = register_mess.getUsrid();
                        String pwd = register_mess.getPasswd();
                        user u = new user(usrid,usrid,pwd);
                        u.setSign(register_mess.getSign());
                        u.setAvatarID(register_mess.getAvatarID());
                        vulidusers.put(usrid,u);
                        ArrayList<user> arrayList = new ArrayList<>();
                        Total_friend_list.put(usrid,arrayList);

                        Message  message1 = new Message();
                        message1.setMessage_type(Message_type.MESSAGE_REGISTER_SUCESS);
                        oos.writeObject(message1);

                    }else {  // 如果已经创建了的话
                        Message  message1 = new Message();
                        message1.setMessage_type(Message_type.MESSAGE_REGISTER_FAILED);
                        oos.writeObject(message1);
                    }

                    System.out.println(register_mess.getUsrid()+"ok");


                }
                else {

                    System.out.println("some error occur!");
                }



            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            try {
                if (ss != null) {
                    ss.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
