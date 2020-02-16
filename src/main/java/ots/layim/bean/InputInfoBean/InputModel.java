package ots.layim.bean.InputInfoBean;

import java.io.Serializable;

public class InputModel implements Serializable{
    private String type;
    private InputData data;

    public InputModel() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public InputData getData() {
        return data;
    }

    public void setData(InputData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "InputModel{" +
                "type='" + type + '\'' +
                ", data=" + data +
                '}';
    }
}
