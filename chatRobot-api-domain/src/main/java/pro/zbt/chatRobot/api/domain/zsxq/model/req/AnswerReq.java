package pro.zbt.chatRobot.api.domain.zsxq.model.req;


import pro.zbt.chatRobot.api.domain.zsxq.model.vo.Comment;

/**
 * 请求问答接口信息
 */
public class AnswerReq {

    private boolean succeeded;

    private Resp_data resp_data;

    public AnswerReq() {
    }

    public AnswerReq(Resp_data resp_data) {
        this.resp_data = resp_data;
    }

    public AnswerReq(Resp_data resp_data, boolean succeeded) {
        this.succeeded = succeeded;
        this.resp_data = resp_data;
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public Resp_data getResp_data() {
        return resp_data;
    }

    public void setResp_data(Resp_data resp_data) {
        this.resp_data = resp_data;
    }
}
