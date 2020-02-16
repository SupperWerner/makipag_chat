package ots.layim.bean.InputInfoBean;

import java.io.Serializable;

public class InputData implements Serializable{
    private InputMine mine;
    private InputToMine to;

    public InputData() {
    }

    public InputMine getMine() {
        return mine;
    }

    public void setMine(InputMine mine) {
        this.mine = mine;
    }

    public InputToMine getTo() {
        return to;
    }

    public void setTo(InputToMine to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "InputData{" +
                "mine=" + mine +
                ", to=" + to +
                '}';
    }
}
