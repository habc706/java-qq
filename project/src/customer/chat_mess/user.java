package customer.chat_mess;

import java.io.Serializable;

public class user implements Serializable {

    //该对象在网络上传输，需要序列化
    private String name;
    private String usrid;
    private String passwd;
    private  String sign;
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


    public String getAvatarID() {
        return avatarID;
    }

    public void setAvatarID(String avatarID) {
        this.avatarID = avatarID;
    }

    private  String avatarID;
    public String getUsrid() {
        return usrid;
    }


    public void setUsrid(String usrid) {
        this.usrid = usrid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public user(){
        this.name="";
        this.passwd="";
        this.usrid="";
    }

    public user(String name,String usrid, String passwd) {
        this.usrid=usrid;
        this.name = name;
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public String getPasswd() {
        return passwd;
    }


}

