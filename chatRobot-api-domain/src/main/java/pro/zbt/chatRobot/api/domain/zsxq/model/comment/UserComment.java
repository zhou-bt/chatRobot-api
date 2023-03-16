package pro.zbt.chatRobot.api.domain.zsxq.model.comment;
public class UserComment
{
    private Req_data req_data;

    public void setReq_data(Req_data req_data){
        this.req_data = req_data;
    }
    public Req_data getReq_data(){
        return this.req_data;
    }

    public UserComment(Req_data req_data) {
        this.req_data = req_data;
    }

    public UserComment() {
    }
}
