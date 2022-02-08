package customer.chat_mess;


import java.io.Serializable;

//这个类用来显示每一条聊天记录
public class chat implements Serializable {
    private String content;//信息内容
    private String senderQQ;//发送者
    private String receiveQQ;//接收者
    private String sendTime;//发送时间

    public chat(String content, String senderQQ, String receiveQQ, String sendTime) {
        this.content = content;
        this.senderQQ = senderQQ;
        this.receiveQQ = receiveQQ;
        this.sendTime = sendTime;
    }

    public  chat(){

    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderQQ() {
        return senderQQ;
    }

    public void setSenderQQ(String senderQQ) {
        this.senderQQ = senderQQ;
    }

    public String getReceiveQQ() {
        return receiveQQ;
    }

    public void setReceiveQQ(String receiveQQ) {
        this.receiveQQ = receiveQQ;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
