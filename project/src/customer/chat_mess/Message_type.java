package customer.chat_mess;

public interface Message_type {

    String MESSAGE_LOGIN_SUCCEED = "1";
    String MESSAGE_LOGIN_FAILED = "2";
    String MESSAGE_COMM_MES = "3";//普通的信息包
    String MESSAGE_GET_ONLINE_FRIEND="4";//返回在线用户列表
    String MESSAGE_ALL_FRIEND="5"; //返回用户列表
    String MESSAGE_CLIENT_EXIT="6";//客户端请求退出
    String MESSAGE_SEND_FALIED="7";
    String MESSAGE_SAY_TO_ALL="8";
    String MESSAGE_GET_OFFLINE_MESS="9";
    String MESSAGE_GET_FRIEND_LIST="10"; // 用户请求自己的好友列表
    String MESSAGE_REGISTER="11";  //用户注册
    String MESSAGE_REGISTER_SUCESS="12";
    String MESSAGE_REGISTER_FAILED="13";
    String MESSAGE_GET_USER="14";
    String MESSAGE_ADD_FRIEND="15";//客户端发起的添加好友请求
    String MESSAGE_ADD_FRIEND_FAILED="16"; //服务端发送到添加失败消息
    String MESSAGE_ADD_FRIEND_SUCESS="17";
    String MESSAGE_GET_CHAT_LISTS = "18"; //初始化获取一个用户所有关联的好友信息类型

}
