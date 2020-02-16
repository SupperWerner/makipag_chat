package ots.layim.myenum;

public enum  ChatEmit {

    CHAT_MESSAGE("chatMessage"),
    CHAT_MODEL("chatModel");
    private final String emit;

    private ChatEmit(String str){
        this.emit = str;
    }
    public String getEmitName(){
        return emit;
    }
}
