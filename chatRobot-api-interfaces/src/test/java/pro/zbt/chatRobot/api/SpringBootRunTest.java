package pro.zbt.chatRobot.api;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pro.zbt.chatRobot.api.domain.ai.IOpenAI;
import pro.zbt.chatRobot.api.domain.zsxq.IChatApi;
import pro.zbt.chatRobot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionAggregates;
import pro.zbt.chatRobot.api.domain.zsxq.model.vo.Comment;
import pro.zbt.chatRobot.api.domain.zsxq.model.vo.Topics;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    private Logger logger = LoggerFactory.getLogger(IChatApi.class);

    @Value("${chatRobot-api.groupId}")
    private String groupId;

    @Value("${chatRobot-api.cookie}")
    private String cookie;

    @Resource
    private IChatApi iChatApi;

    @Resource
    private IOpenAI openAI;

    @Test
    public void test_chatApi() throws IOException {
        UnAnsweredQuestionAggregates unAnsweredQuestionAggregates = iChatApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("测试结果 {} ", JSON.toJSONString(unAnsweredQuestionAggregates));

        List<Topics> topics = unAnsweredQuestionAggregates.getResp_data().getTopics();
        for(Topics topic : topics){
            String topic_id = topic.getTopic_id();
            String text = topic.getTalk().getText();
            logger.info("topicId: {}, 问题: {} ", topic_id, text);
            iChatApi.answer(groupId, cookie, topic_id, text);
        }
    }

    @Test
    public void test_openAi() throws IOException{
        String s = openAI.doChatGPT("帮我写个冒泡排序");
        logger.info("测试结果: {}", s);
    }

}
