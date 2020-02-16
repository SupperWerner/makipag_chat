package ots.layim.bean.InitBean;

import ots.layim.bean.Mine;

import java.io.Serializable;
import java.util.List;

/**
 * 初始化所需好友列表数据
 */
public class InitFriend implements Serializable {
    private String groupname;
    private String id; // 分组id
    private List<Mine> list;  // 分组下的好友列表

    public InitFriend() {
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

    public List<Mine> getList() {
        return list;
    }

    public void setList(List<Mine> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "InitFriend{" +
                "groupname='" + groupname + '\'' +
                ", id='" + id + '\'' +
                ", list=" + list +
                '}';
    }
}
