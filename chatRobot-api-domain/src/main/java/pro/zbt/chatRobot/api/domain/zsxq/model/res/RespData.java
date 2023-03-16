package pro.zbt.chatRobot.api.domain.zsxq.model.res;

import pro.zbt.chatRobot.api.domain.zsxq.model.vo.Topics;

import java.util.List;


/**
 * 结果数据
 */
public class RespData {

    private List<Topics> topics;

    public List<Topics> getTopics() {
        return this.topics;
    }

    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }
}
