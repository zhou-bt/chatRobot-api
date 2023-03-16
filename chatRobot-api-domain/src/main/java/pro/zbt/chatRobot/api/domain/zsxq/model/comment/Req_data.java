package pro.zbt.chatRobot.api.domain.zsxq.model.comment;

import java.util.ArrayList;
import java.util.List;
public class Req_data
{
    private String text;

    private List<String> image_ids;

    private List<String> mentioned_user_ids;

    public Req_data() {
    }

    public Req_data(String text, List<String> image_ids, List<String> mentioned_user_ids) {
        this.text = text;
        this.image_ids = image_ids;
        this.mentioned_user_ids = mentioned_user_ids;
    }

    public Req_data(String text) {
        this.text = text;
    }

    public void setText(String text){
        this.text = text;
    }
    public String getText(){
        return this.text;
    }
    public void setImage_ids(List<String> image_ids){
        this.image_ids = image_ids;
    }
    public List<String> getImage_ids(){
        return this.image_ids;
    }
    public void setMentioned_user_ids(List<String> mentioned_user_ids){
        this.mentioned_user_ids = mentioned_user_ids;
    }
    public List<String> getMentioned_user_ids(){
        return this.mentioned_user_ids;
    }
}