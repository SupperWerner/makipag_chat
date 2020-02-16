package ots.layim.bean.InputInfoBean;

import java.io.Serializable;

public class InputMine implements Serializable {
    private String username;
    private String id;
    private boolean mine;
    private String avatar;
    private String content;

    public InputMine() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "InputMine{" +
                "username='" + username + '\'' +
                ", id='" + id + '\'' +
                ", mine=" + mine +
                ", avatar='" + avatar + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
