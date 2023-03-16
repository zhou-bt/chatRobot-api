package pro.zbt.chatRobot.api.domain.zsxq.model.req;

import pro.zbt.chatRobot.api.domain.zsxq.model.vo.Comment;

public class Resp_data
{
    private Comment comment;

    public void setComment(Comment comment){
        this.comment = comment;
    }

    public Resp_data(Comment comment) {
        this.comment = comment;
    }

    public Resp_data() {
    }

    public Comment getComment(){
        return this.comment;
    }
}