package com.imooc.article.stream;


import com.imooc.pojo.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 开启绑定器
 * 绑定通道channel
 */
@Component
@EnableBinding(MyStreamChannel.class)
public class StreamServiceImpl implements StreamService {

    // 注入管道output，用于发送消息
    @Autowired
    private MyStreamChannel myStreamChannel;

    @Override
    public void sendMsg() {
        AppUser user = new AppUser();
        user.setId("1111");
        user.setNickname("lee");

        // 消息通过绑定器发送给mq
        myStreamChannel.output()
                .send(MessageBuilder.withPayload(user).build());
    }
    @Override
    public void eat(String dumpling) {
        myStreamChannel.output()
                .send(MessageBuilder.withPayload(dumpling).build());
    }
}
