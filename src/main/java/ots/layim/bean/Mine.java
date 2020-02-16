package ots.layim.bean;

import java.io.Serializable;

/**
 * 用户信息
 */
public class Mine implements Serializable {
    private String username;
    private String id;
    private String sign;
    private boolean status;
    private String avatar;

    public Mine() {
    }

    @Override
    public String toString() {
        return "Mine{" +
                "username='" + username + '\'' +
                ", id='" + id + '\'' +
                ", sign='" + sign + '\'' +
                ", status=" + status +
                ", avatar='" + avatar + '\'' +
                '}';
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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
}
