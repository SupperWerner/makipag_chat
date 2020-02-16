package ots.layim.bean.InitBean;

import java.io.Serializable;

/**
 * 初始化群组
 */
public class InitGroup implements Serializable {
    private String groupname;
    private String id;
    private String avatar;

    public InitGroup() {
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupname='" + groupname + '\'' +
                ", id='" + id + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
