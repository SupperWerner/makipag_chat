package ots.layim.bean.OutputInfoBean;

import ots.layim.myenum.ChatEmit;

import java.io.Serializable;

/**
 * 返回聊天信息的模板
 *
 * ResChatInfo
 * ResPanelInfo
 */
public class ResultChatModel<T> implements Serializable {
    private ChatEmit emit;
    private T data;

    public ResultChatModel() {
    }

    public ChatEmit getEmit() {
        return emit;
    }

    public void setEmit(ChatEmit emit) {
        this.emit = emit;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultChatModel{" +
                "emit=" + emit +
                ", data=" + data +
                '}';
    }
}
