package service.qqservice;

import java.util.HashMap;
import java.util.Iterator;

//用于管理和客户端通讯的·线程
public class ManagerClientThread {
    private static HashMap<String,ServiceConnectThread> hm = new HashMap<>();


    public  static void  addthread(String usrid,ServiceConnectThread serviceConnectThread){
        hm.put(usrid,serviceConnectThread);
    }
    public  static ServiceConnectThread getServiceThread(String usrid){
        return hm.get(usrid);
    }
    public  static String getonlineuser(){
        //
        String onlinelist = "";
        Iterator<String> iterator = hm.keySet().iterator();
        while (iterator.hasNext()){
            onlinelist+=iterator.next().toString()+" ";

        }
        return onlinelist;
    }
    public static void removethread(String usrid){
        hm.remove(usrid);
    }
    public static HashMap getall_thread(){
        return hm;
    }
}
