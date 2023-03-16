package pro.zbt.chatRobot.api.domain.zsxq.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.zbt.chatRobot.api.domain.zsxq.IChatApi;
import pro.zbt.chatRobot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionAggregates;
import pro.zbt.chatRobot.api.domain.zsxq.model.comment.Req_data;
import pro.zbt.chatRobot.api.domain.zsxq.model.comment.UserComment;
import pro.zbt.chatRobot.api.domain.zsxq.model.req.AnswerReq;
import pro.zbt.chatRobot.api.domain.zsxq.model.req.Resp_data;
import pro.zbt.chatRobot.api.domain.zsxq.model.res.AnswerRes;

import java.io.IOException;

@Service
public class ChatApi implements IChatApi {

    private Logger logger = LoggerFactory.getLogger(IChatApi.class);

    @Override
    public UnAnsweredQuestionAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/"+groupId+"/topics?scope=all&count=1");

        get.addHeader("cookie",cookie);
        get.addHeader("Content-Type","application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("爬取问题数据。 groupId: {} jsonStr: {}", groupId, jsonStr);
            return JSON.parseObject(jsonStr, UnAnsweredQuestionAggregates.class);
        }else{
            throw new RuntimeException("queryUnAnsweredQuestionsTopicId Err Code is" + response.getStatusLine().getStatusCode());
        }
    }

    @Override
    public boolean answer(String groupId, String cookie, String topic_id, String text) throws IOException {
//    public boolean answer(String groupId, String cookie, String topic_id, Comment comment, boolean succeeded) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

//        https://api.zsxq.com/v2/topics/214814218541841/comments
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/"+topic_id+"/comments");
        post.addHeader("cookie",cookie);
        post.addHeader("Content-Type","application/json;charset=utf8");

        //表明请求是从浏览器发送的，防止风控
        post.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36");

//        String paramJson = "{\"req_data\":{\"text\":\""+text+"\\n\",\"image_ids\":[],\"mentioned_user_ids\":[]}}";
//        {"succeeded":true,"resp_data":{"comment":{"comment_id":211854228814211,"create_time":"2023-03-16T09:42:17.325+0800","owner":{"user_id":185541218215522,"name":"zoboto","avatar_url":"https:\/\/images.zsxq.com\/Fk3Ll4HbNvJX8KC__r7Z8Zu2HRkB?e=1682870399&token=kIxbL07-8jAj8w1n4s9zv64FuZZNEATmlU_Vm6zD:hU_j5AwF6QVmCRhQRDWOYPCk8_4=","location":"\u91cd\u5e86"},"text":"\u597d\u597d\u5b66\u4e60","likes_count":0,"rewards_count":0,"sticky":false}}}

        UserComment comment = new UserComment(new Req_data(text));
        String paramJson = JSONObject.toJSONString(comment);


        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("回答问题结果。 groupId: {} topicId: {} jsonStr: {}", groupId, topic_id, jsonStr);
            AnswerRes answerRes = JSON.parseObject(jsonStr, AnswerRes.class);
            return answerRes.isSucceeded();
        }else{
            throw new RuntimeException("answer Err Code is" + response.getStatusLine().getStatusCode());
        }
    }
}
