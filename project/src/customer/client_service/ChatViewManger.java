package customer.client_service;

import customer.chat_view.ChatView;

import java.util.Hashtable;

public class ChatViewManger {
    private static Hashtable<String, ChatView> chatFrames = new Hashtable<>();

    public static void addChatFrame(String fqq, ChatView chat) {
        chatFrames.put(fqq, chat);
    }

    public static ChatView getChatFrame(String fqq) {
        return chatFrames.get(fqq);
    }



    public static ChatView removeChatFrame(String fqq) {
        return chatFrames.remove(fqq);
    }

}
