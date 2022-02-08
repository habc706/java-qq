package service.qqservice;


import customer.chat_mess.Message;
import customer.chat_mess.Message_type;
import customer.chat_mess.chat;
import customer.chat_mess.user;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

//和客户端的socket保持通信
public class ServiceConnectThread extends Thread{

    private Socket socket;
    private String  usrid;

    @Override
    public void run() {
        while (true){
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                //根据message类型吹处理
                if(message.getMessage_type().equals(Message_type.MESSAGE_GET_ONLINE_FRIEND)){
                    System.out.println(message.getSender()+"要求获得在线用户列表");
                    String onlineuser = ManagerClientThread.getonlineuser();

                    Message message2 = new Message();
                    message2.setMessage_type(Message_type.MESSAGE_GET_ONLINE_FRIEND);
                //    HashMap<String,String> hashMap = new HashMap<>();

//                    hashMap.put();
                    message2.setContent(onlineuser);
                    message2.setReceiver(message.getSender());
                    //原本的发送信息的客户端变成了接收者
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message2);
                }else if(message.getMessage_type().equals(Message_type.MESSAGE_CLIENT_EXIT)){
                    //把退出消息发送给客户端验证，以便客户端结束线程
                    System.out.println(message.getSender()+"退出对话");
                    Message message1 = new Message();
                    message1.setMessage_type(Message_type.MESSAGE_CLIENT_EXIT);
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message1);

                    ManagerClientThread.removethread(message.getSender());

                    //=========------------------------------移除已登录
                    qqservice.remove_online_people(message.getSender());
                    socket.close();
                    break;  // 退出循环，和这个客户机的连接线程断开
                }else  if(message.getMessage_type().equals(Message_type.MESSAGE_COMM_MES)){
                    //普通的发信息类型,获取目标的socket
                    ServiceConnectThread s1 = ManagerClientThread.getServiceThread(message.getReceiver());
                    Message message2 = new Message();
                    message2.setMessage_type(Message_type.MESSAGE_COMM_MES);

                    message2.setChat_news(message.getChat_news());

                    message2.setReceiver(message.getReceiver());
                    message2.setSender(message.getSender());
                    if( qqservice.if_exists_chats(message.getSender(),message.getReceiver())!=""){
                        qqservice.add_chat_news(message.getSender(),message.getReceiver(),message.getChat_news());
                       // 把消息内容存进缓存中
                    }

                    if(s1!=null){
                        ObjectOutputStream oos = new ObjectOutputStream(
                                s1.socket.getOutputStream());

                        oos.writeObject(message2);

                        System.out.println(message.getSender()+"向"+message.getReceiver()+"发送消息");
                    }else { //如果用户还没有登陆
                        System.out.println("把"+message.getReceiver()+"的离线留言信息放进来数据库");
                    }
                }
                else if(message.getMessage_type().equals(Message_type.MESSAGE_SAY_TO_ALL)){
                    Message message2 = new Message();
                    message2.setMessage_type(Message_type.MESSAGE_SAY_TO_ALL);
                    message2.setSender(message.getSender());

                    message2.setContent(message.getContent());
                    //这里是存储的所有在线用户

                    HashMap<String,ServiceConnectThread> all_online_thread = ManagerClientThread.getall_thread();

                    //应该还要有一个所有用户的集合，在所有用户集合循环中判断是否在线
                    ConcurrentHashMap<String , user> all_people= qqservice.get_all_people();

                    Iterator<String>  iterator=all_people.keySet().iterator();
                    while (iterator.hasNext()){

                        String userid = iterator.next();
                        if(all_online_thread.get(userid)!=null){
                            ObjectOutputStream oos = new ObjectOutputStream(all_online_thread.get(userid).socket.getOutputStream());
                            oos.writeObject(message2);
                        }else {  //-------
                            qqservice.add_offline_mes(userid,message2);//------------------------
                            System.out.println("把"+userid+" 的离线留言信息放进来数据库");

                        }
//
                    }
                    System.out.println(message.getSender()+"向所有人发送了消息");
                }else  if(message.getMessage_type().equals(Message_type.MESSAGE_GET_OFFLINE_MESS)){
                    ObjectOutputStream oos = new ObjectOutputStream(  //向发送请求者传递信息
                            ManagerClientThread.getServiceThread(message.getSender()).socket.getOutputStream());

                    ArrayList<Message> messageArrayList = qqservice.get_offline_mes(message.getSender());
                    String finally_mes = "";
                    for (Message message1 : messageArrayList) {
                        //------这里先把发送者信息传进信息里
                        finally_mes+=message1.getSender()+": ";
                        finally_mes+=message1.getContent()+" \n";

                    }
                    Message message12 = new Message();
                    message12.setContent(finally_mes);
                    //message12.setReceiver(message.getSender());
                    message12.setSender(message.getSender());//---------
                    message12.setMessage_type(Message_type.MESSAGE_COMM_MES);
                    oos.writeObject(message12);
                    System.out.println(message.getSender()+"获取了他的离线留言信息");
                }
                else if(message.getMessage_type().equals(Message_type.MESSAGE_GET_USER)){
                    String sender = message.getSender();
                    Message message1 = new Message();
                    message1.setMessage_type(Message_type.MESSAGE_GET_USER);

                    ConcurrentHashMap<String ,user> allpeolpe = qqservice.get_all_people();
                    user user = allpeolpe.get(sender);
                    message1.setUser(user);
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message1);


                }
                else if(message.getMessage_type().equals(Message_type.MESSAGE_GET_FRIEND_LIST)){
                    String sender = message.getSender();
                    ArrayList<user>  arrayList= qqservice.get_friends(sender);
                    Message message1 = new Message();
                    message1.setMessage_type(Message_type.MESSAGE_GET_FRIEND_LIST);
                    message1.setFriendlist(arrayList);

                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message1);

                }
                else if(message.getMessage_type().equals(Message_type.MESSAGE_ADD_FRIEND)){
                    String friend_id = message.getFriend_id();
                    String sender = message.getSender();

                    user u = qqservice.get_people_by_id(friend_id);

                    if(u!=null)   // 客户端填写的用户存在
                    {
                        //再检查好友是不是已经存在了
                        ArrayList<user> arrayList = qqservice.get_friends(sender);

                        if(arrayList.contains(u)){  //已经是朋友了
                            Message message1 = new Message();
                            message1.setMessage_type(Message_type.MESSAGE_ADD_FRIEND_FAILED);
                            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                            oos.writeObject(message1);
                        }else {
                            qqservice.add_friend(sender,u);
                            //--------朋友那一边的数据也更新
                            qqservice.add_friend(u.getUsrid(),qqservice.get_people_by_id(sender));

                            //
                            Message message1 = new Message();
                            message1.setReceiver(friend_id);
                            //返回添加的朋友的id？


                            message1.setMessage_type(Message_type.MESSAGE_ADD_FRIEND_SUCESS);
//                        ArrayList<user> friends = qqservice.get_friends(sender);
//                        message1.setFriendlist(friends);
                            //增加好友信息列表内容
                            System.out.println("更新了信息内容");
                            //====----------------初始化了朋友消息内容
                            qqservice.add_init_chatnews(sender,friend_id);

                            ServiceConnectThread s1 = ManagerClientThread.getServiceThread(friend_id);
                            if ((s1!=null)){
                                ObjectOutputStream objectOutputStream = new ObjectOutputStream(s1.socket.getOutputStream());
                                objectOutputStream.writeObject(message1);

                            }

                            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                            oos.writeObject(message1);
                        }

                    }
                    else {
                        Message message1 = new Message();
                        message1.setMessage_type(Message_type.MESSAGE_ADD_FRIEND_FAILED);
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        oos.writeObject(message1);

                    }

                }
                //客户端初始化所有消息类型
                else if(message.getMessage_type().equals(Message_type.MESSAGE_GET_CHAT_LISTS)){
                    String sender = message.getSender();
                        //qqservice需要一个根据 ，，一个参数
                        /*
                        * 根据一个参数，返回素有该用户的好友消息队列，，返回值应该hasmap
                        * 因为消息内容时存储在客户端的
                        * */
                    HashMap<String,ArrayList<chat>> chat_list = new HashMap<>();
                 //   chat_list.put(new chat());
                    Iterator iterator = qqservice.getAll_people_news().keySet().iterator();
                    System.out.println("包含了以下消息对：");
                    while (iterator.hasNext()){
                        //
                        String me_and_friend = iterator.next().toString();

                        System.out.println(me_and_friend);
                        if(me_and_friend.contains(sender)){
                            String key =  me_and_friend.replaceAll(sender,"");
                            ArrayList<chat> arrayList = qqservice.get_Chat_lists(sender,key);
                            chat_list.put(key,arrayList);
                            System.out.println("用户"+sender+"与"+key+"的消息");
                        }
                    }
                    Message message1 = new Message();
                    message1.setMessage_type(Message_type.MESSAGE_GET_CHAT_LISTS);
                    message1.setChat_lists(chat_list);
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message1);

                }

                else {
                    //暂时不处理其他情况
                    System.out.println("其他类型的信息，暂不处理");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

    public ServiceConnectThread(Socket socket, String usrid) {
        this.socket = socket;
        this.usrid = usrid;
    }

}
