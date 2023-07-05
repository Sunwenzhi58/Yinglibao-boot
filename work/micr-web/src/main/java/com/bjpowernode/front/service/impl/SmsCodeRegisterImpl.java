package com.bjpowernode.front.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.front.config.JdwxSmsConfig;
import com.bjpowernode.front.service.SmsService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;



@Service
public class SmsCodeRegisterImpl implements SmsService {

    @Resource
    private JdwxSmsConfig smsConfig;

    @Override
    public boolean sendSms(String phone) {
        boolean send = false;
        // 设置短信内容
        String random  = RandomStringUtils.randomNumeric(4);
        System.out.println("注册验证码的随机数 random="+random);
        //更新content中的  %s   【大富科技】你的验证码是：%s，3分钟内有效，请勿泄露给他人
        String content  = String.format(smsConfig.getContent(), random);

        //使用HttpClient发送 get 请求给第三方。
        CloseableHttpClient client = HttpClients.createDefault();
        //https://way.jd.com/chuangxin/dxjk?mobile=13568813957&content=
        //【创信】你的验证码是：5873，3分钟内有效！&appkey=您申请的APPKEY
        String url = smsConfig.getUrl()+"?mobile="+phone
                        +"&content=" + content
                        +"&appkey="+smsConfig.getAppkey();
        HttpGet get  = new HttpGet(url);

        try{
            CloseableHttpResponse response = client.execute(get);
            if( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK ){
                //得到返回的数据，json
                //String text = EntityUtils.toString(response.getEntity());
                String text="{\n" +
                        "    \"code\": \"10000\",\n" +
                        "    \"charge\": false,\n" +
                        "    \"remain\": 1305,\n" +
                        "    \"msg\": \"查询成功\",\n" +
                        "    \"result\": {\n" +
                        "        \"ReturnStatus\": \"Success\",\n" +
                        "        \"Message\": \"ok\",\n" +
                        "        \"RemainPoint\": 420842,\n" +
                        "        \"TaskID\": 18424321,\n" +
                        "        \"SuccessCounts\": 1\n" +
                        "    }\n" +
                        "}";
                //解析json
                if(StringUtils.isNotBlank(text)){
                    // fastjson
                    JSONObject jsonObject = JSONObject.parseObject(text);
                    if("10000".equals(jsonObject.getString("code"))){ //第三方接口调用成功
                        //读取result中的key：ReturnStatus
                        if("Success".equalsIgnoreCase(
                                jsonObject.getJSONObject("result").getString("ReturnStatus"))){
                            //短信发送成功
                            send  = true;
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return send;
    }
}
