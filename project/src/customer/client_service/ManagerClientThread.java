package customer.client_service;


import java.util.HashMap;

//管理客户端连接到服务器的线程
public class ManagerClientThread {
    private  static HashMap<String,ClientConnectServerThread> hm = new HashMap<>();
    //key是用户id，值是线程
    public static void addClientServiceThread(String usrid,ClientConnectServerThread clientConnectServerThread){
        hm.put(usrid,clientConnectServerThread);
    }
    public  static  ClientConnectServerThread getclientthread(String usrid){
        return  hm.get(usrid);
    }


}
