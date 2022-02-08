package test;

import service.qqservice.qqservice;

import java.util.Iterator;

public class Te {
    public static void main(String[] args) {
//        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
//        concurrentHashMap.put("1","小明");
//        concurrentHashMap.put("11","小明1");
//        Iterator iterator = concurrentHashMap.keySet().iterator();
//        while (iterator.hasNext()){
//            String me_and_friend = iterator.next().toString();
//            System.out.println(me_and_friend);
//        }
        String s = "小明";
 //       System.out.println(s.replaceAll("小明","")+"====>"+s);

        Iterator iterator = qqservice.getAll_people_news().keySet().iterator();
        while (iterator.hasNext()){
            //
            String me_and_friend = iterator.next().toString();
            System.out.println(me_and_friend);
//            if(me_and_friend.contains(s)){
//                String key =  me_and_friend.replaceAll(s,"");
//                System.out.println(key);
//
//                //  ArrayList<chat> arrayList = qqservice.get_Chat_lists(sender,key);
//                //chat_list.put(key,arrayList);
//            }
        }
    }
}
