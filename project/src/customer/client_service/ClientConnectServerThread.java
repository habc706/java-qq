package customer.client_service;

import customer.chat_mess.Message;
import customer.chat_mess.Message_type;
import customer.chat_mess.chat;
import customer.chat_mess.user;
import customer.public_view.Public_data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ClientConnectServerThread extends Thread{

    private Socket socket;
    public ClientConnectServerThread(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {

        while (true){

                //客户端线程，读取从服务器端发送的消息
                try {
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    Message ms = (Message) ois.readObject(); //如果没有信息会一直阻塞在这里

                    //根据这个message类型选择,客户端获取在线用户列表
                    if(ms.getMessage_type().equals(Message_type.MESSAGE_GET_ONLINE_FRIEND)){
                        String[] onlineusers = ms.getContent().split(" ");
                        //onlineusers就是返回的内容
                        System.out.println("在线的用户有：");
                        for (String onlineuser : onlineusers) {
                            System.out.print(onlineuser+" ");
                        }

                    }else if(ms.getMessage_type().equals(Message_type.MESSAGE_COMM_MES)){

                        String sender = ms.getSender();

                        System.out.println(sender+"发送了消息");
                        Public_data.get_chatnews(sender).add(ms.getChat_news());
                        //数据更新
                        //========================================================================
                        //再添加一个打对话开窗口时会执行的函数，让对话窗口自动刷新？判断是否已经打开某个窗口

                    }else if(ms.getMessage_type().equals(Message_type.MESSAGE_GET_USER)){
                        Public_data.setUser(ms.getUser());
                        System.out.println("成功获取用户详细信息");

                    }
//                else if(ms.getMessage_type().equals(Message_type.MESSAGE_SEND_FALIED)){
//                    System.out.println("用户未登陆，发送失败，请稍后重试");
//                }
                    else if(ms.getMessage_type().equals(Message_type.MESSAGE_SAY_TO_ALL)){
                        System.out.println(ms.getSender()+"向所有人说："+ms.getContent()+"\n");
                    }else  if(ms.getMessage_type().equals(Message_type.MESSAGE_GET_OFFLINE_MESS)){
                        String result = ms.getContent();
                        System.out.println(result);
                    }else if(ms.getMessage_type().equals(Message_type.MESSAGE_CLIENT_EXIT)){
                        System.out.println("线程关闭");
                        break;
                    }else if(ms.getMessage_type().equals(Message_type.MESSAGE_GET_FRIEND_LIST)){
                        ArrayList<user> arrayList = ms.getFriendlist();
                        Public_data.setFriendlist(arrayList);
                        System.out.println("更新了好友列表");
                    }
                    else if(ms.getMessage_type().equals(Message_type.MESSAGE_ADD_FRIEND_SUCESS)){
                        System.out.println("添加好友成功");
                        //调用一个函数提示添加成功
                        Public_data.setIf_add_ok(true);
                        String friend_id=ms.getReceiver();
                        //增加与朋友的信息队列
                        ArrayList<chat> chats = new ArrayList<>();
                        Public_data.Total_chat_lists.put(friend_id,chats);

                        //掉用重画函数


                    }
                    else  if(ms.getMessage_type().equals(Message_type.MESSAGE_ADD_FRIEND_FAILED)){
                        System.out.println("添加好友失败");
                        //调用一个函数提示添加失败
                        Public_data.setIf_add_ok(false);
                    }
                    else if(ms.getMessage_type().equals(Message_type.MESSAGE_GET_CHAT_LISTS)){
                        Public_data.setTotal_chat_lists(ms.getChat_lists());
                        //初始化消息内容
                        System.out.println("更新信息列表");
                        HashMap<String, ArrayList<chat>> chat_lists = ms.getChat_lists();
                        Iterator iterator = chat_lists.keySet().iterator();
                        while (iterator.hasNext()){
                            System.out.println(iterator.next());
                        }
                    }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
        }

    }

    public Socket getSocket() {
        return socket;
    }



    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
