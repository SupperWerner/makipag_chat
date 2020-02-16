package ots.layim.bean.InitBean;

import ots.layim.bean.Mine;

import java.io.Serializable;
import java.util.List;

/**
 * LayIM初始化模板
 */
public class InitDataVO implements Serializable {
    private Mine mine;
    private List<InitFriend> friend;
    private List<InitGroup> group;

    public InitDataVO() {
    }

    public Mine getMine() {
        return mine;
    }

    public void setMine(Mine mine) {
        this.mine = mine;
    }

    public List<InitFriend> getFriend() {
        return friend;
    }

    public void setFriend(List<InitFriend> friend) {
        this.friend = friend;
    }

    public List<InitGroup> getGroup() {
        return group;
    }

    public void setGroup(List<InitGroup> group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "InitDataVO{" +
                "mine=" + mine +
                ", friend=" + friend +
                ", group=" + group +
                '}';
    }
}
