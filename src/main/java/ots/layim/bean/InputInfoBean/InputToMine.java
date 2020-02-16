package ots.layim.bean.InputInfoBean;

import java.io.Serializable;

public class InputToMine implements Serializable {
    private String username;
    private String id;
    private String sgin;
    private boolean status;
    private String avatar;
    private String name;
    private String type;

    public InputToMine() {
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

    public String getSgin() {
        return sgin;
    }

    public void setSgin(String sgin) {
        this.sgin = sgin;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "InputToMine{" +
                "username='" + username + '\'' +
                ", id='" + id + '\'' +
                ", sgin='" + sgin + '\'' +
                ", status=" + status +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
