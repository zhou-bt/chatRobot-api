package pro.zbt.chatRobot.api;

import okhttp3.*;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.core5.util.Timeout;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.interfaces.DSAKey;
import java.util.concurrent.TimeUnit;

public class ApiTest {

    @Test
    public void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=all&count=1");
//        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28885518425541/topics?scope=unanswered_questions&count=20");
        get.addHeader("cookie","zsxq_access_token=80AC15EE-ED94-16C9-D540-107B5ADDBDF5_614137150D625F14; zsxqsessionid=90401d2bf86d6c78629a5cfbf611c88a; abtest_env=product; UM_distinctid=186ddf56b7d850-01f83599caf89-26031951-1fa400-186ddf56b7e7a5; sensorsdata2015jssdkcross={\"distinct_id\":\"186ddf570f4355-0b582485716d368-26031951-2073600-186ddf570f5878\",\"first_id\":\"\",\"props\":{},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg2ZGRmNTcwZjQzNTUtMGI1ODI0ODU3MTZkMzY4LTI2MDMxOTUxLTIwNzM2MDAtMTg2ZGRmNTcwZjU4NzgifQ==\",\"history_login_id\":{\"name\":\"\",\"value\":\"\"},\"$device_id\":\"186ddf570f4355-0b582485716d368-26031951-2073600-186ddf570f5878\"}; sajssdk_2015_cross_new_user=1");
        get.addHeader("Content-Type","application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

    @Test
    public void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

//        https://api.zsxq.com/v2/topics/214814218541841/comments
        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/214814218541841/comments");
        post.addHeader("cookie","zsxq_access_token=80AC15EE-ED94-16C9-D540-107B5ADDBDF5_614137150D625F14; zsxqsessionid=90401d2bf86d6c78629a5cfbf611c88a; abtest_env=product; UM_distinctid=186ddf56b7d850-01f83599caf89-26031951-1fa400-186ddf56b7e7a5; sensorsdata2015jssdkcross={\"distinct_id\":\"186ddf570f4355-0b582485716d368-26031951-2073600-186ddf570f5878\",\"first_id\":\"\",\"props\":{},\"identities\":\"eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTg2ZGRmNTcwZjQzNTUtMGI1ODI0ODU3MTZkMzY4LTI2MDMxOTUxLTIwNzM2MDAtMTg2ZGRmNTcwZjU4NzgifQ==\",\"history_login_id\":{\"name\":\"\",\"value\":\"\"},\"$device_id\":\"186ddf570f4355-0b582485716d368-26031951-2073600-186ddf570f5878\"}; sajssdk_2015_cross_new_user=1");
        post.addHeader("Content-Type","application/json;charset=utf8");

        String paramJson = "{\"req_data\":{\"text\":\"在这里\\n\",\"image_ids\":[],\"mentioned_user_ids\":[]}}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    public void test_chatGPT() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/chat/completions");
        post.addHeader("Content-Type","application/json");
//        post.addHeader("Authorization"," Bearer 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");
        post.addHeader("Authorization"," Bearer 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");
        String paramJson = " \"model\": \"gpt-3.5-turbo\",\n" +
                "    \"messages\": [{\"role\": \"user\", \"你好啊\": \"Hello!\"}]";


        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }


    @Test
    public void test_chatGPT2() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.openai.com/v1/completions");
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer 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");

        String paramJson = "{\"model\": \"text-davinci-003\", \"prompt\": \"帮我写一个java冒泡排序\", \"temperature\": 0, \"max_tokens\": 1024}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }
}
