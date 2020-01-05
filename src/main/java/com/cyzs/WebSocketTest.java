package com.cyzs;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @Description:
 * @Author xh
 * @create 2020-01-05 15:31
 */
@ServerEndpoint(value = "/ws1")
public class WebSocketTest {

    @OnOpen
    public void onOpen( Session session) {
        System.out.println("新连接");
    }

    /**
     * 连接关闭调用的方法
     * @param session 用户Session
     */
    @OnClose
    public void onClose( Session session) {

    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage( String message, Session session) throws IOException {
        System.out.println("收到客户端消息");

    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("出错了");
    }


}
