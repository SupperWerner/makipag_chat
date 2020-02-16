package ots.layim.myenum;

public enum ChatType {
    FRIEND("friend"),
    GROUP("group");
    private final String type;

    private ChatType(String str){
        this.type = str;
    }
    public String getTypeName(){
        return type;
    }
}
