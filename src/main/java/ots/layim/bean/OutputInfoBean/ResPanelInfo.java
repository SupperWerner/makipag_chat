package ots.layim.bean.OutputInfoBean;

import ots.layim.myenum.ChatType;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 *
 */
public class ResPanelInfo implements Serializable {
    private boolean system; //系统消息
    private String id;  //聊天窗口ID
    private ChatType type;    //聊天窗口类型
    private String content; // 提示内容
    private String[] ids;

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public ResPanelInfo() {
    }

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ChatType getType() {
        return type;
    }

    public void setType(ChatType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ResPanelInfo{" +
                "system=" + system +
                ", id='" + id + '\'' +
                ", type=" + type +
                ", content='" + content + '\'' +
                ", ids=" + Arrays.toString(ids) +
                '}';
    }
}
