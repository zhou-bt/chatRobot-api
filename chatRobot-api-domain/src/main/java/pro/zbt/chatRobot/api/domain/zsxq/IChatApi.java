package pro.zbt.chatRobot.api.domain.zsxq;

import pro.zbt.chatRobot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionAggregates;
import pro.zbt.chatRobot.api.domain.zsxq.model.vo.Comment;

import java.io.IOException;



public interface IChatApi {

    //返回一个未回答的数据信息
    UnAnsweredQuestionAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException;

    boolean answer(String groupId, String cookie, String topic_id, String text) throws IOException;

    //    boolean answer(String groupId, String cookie, String topic_id, Comment comment, boolean succeeded) throws IOException;
}
