package com.freeter.modules.websScoket;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.freeter.common.util.JedisUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

@ServerEndpoint("/api/answerServer/{memberId}")
@Component
public class WebSocketServer {

    static Log log=LogFactory.get(WebSocketServer.class);
    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收userId*/
    private String memberId="";
    private static ExecutorService fixedThreadPool=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final JedisUtils jedis=new JedisUtils("116.62.204.50" ,6379,"awH3mHmkNJB87Rxm" );
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("memberId") String memberId) {
        this.session = session;
        this.memberId=memberId;

        if(webSocketMap.containsKey(memberId)){
            webSocketMap.remove(memberId);
            webSocketMap.put(memberId,this);
            //加入set中
        }else{
            webSocketMap.put(memberId,this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        log.info("用户连接:"+memberId+",当前在线人数为:" + getOnlineCount());

        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("用户:"+memberId+",网络异常!!!!!!");
        }
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(memberId)){
            webSocketMap.remove(memberId);
            subOnlineCount();
        }
        log.info("用户退出:"+memberId+",当前在线人数为:" + getOnlineCount());
    }
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户消息:"+memberId+",报文:"+message);
        if(StringUtils.isNotBlank(message)){
                fixedThreadPool.execute(new OnMessageThread(webSocketMap,message,memberId,jedis));
        }
        //可以群发消息
        //消息保存到数据库、redis
        /*if(StringUtils.isNotBlank(message)){
            try {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人(防止串改)
                jsonObject.put("fromMemberId",this.memberId);
                String toMemberId=jsonObject.getString("toMemberId");
                jsonObject.getInteger("start");//
                //传送给对应toUserId用户的websocket
                if(StringUtils.isNotBlank(toMemberId)&&webSocketMap.containsKey(toMemberId)){
                    webSocketMap.get(toMemberId).sendMessage(jsonObject.toJSONString());
                }else{
                    log.error("请求的userId:"+toMemberId+"不在该服务器上");
                    //否则不在这个服务器上，发送到mysql或者redis
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }*/
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:"+this.memberId+",原因:"+error.getMessage());
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 发送自定义消息
     * */
    public static void sendInfo(String message,@PathParam("memberId") String memberId) throws IOException {
        log.info("发送消息到:"+memberId+"，报文:"+message);
        if(StringUtils.isNotBlank(memberId)&&webSocketMap.containsKey(memberId)){
            webSocketMap.get(memberId).sendMessage(message);
        }else{
            log.error("用户"+memberId+",不在线！");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}

