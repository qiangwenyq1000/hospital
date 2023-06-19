package cn.com.hospital.config;


import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@Slf4j
public class MessageEventHandler {
    @Autowired
    private SocketIOServer socketIoServer;



    public static  String ALL_BQ_CODE="all_bq";

    public static ConcurrentMap<String, List<SocketIOClient>> socketIOClientMap = new ConcurrentHashMap<>();

    /**
     * 客户端连接的时候触发
     *
     * @param client
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {
        String bqCode = client.getHandshakeData().getSingleUrlParam("bqCode");
        //存储SocketIOClient，用于发送消息
        if(StringUtils.isEmpty(bqCode)){
            bqCode=ALL_BQ_CODE;
        }
        List<SocketIOClient> list=new ArrayList<SocketIOClient>();
        if(socketIOClientMap.containsKey(bqCode)){
            list=socketIOClientMap.get(bqCode);
            if(list==null || list.size()<=0){
                list=new ArrayList<SocketIOClient>();
                list.add(client);
            }else {
                if(!list.contains(client)){
                    list.add(client);
                }
            }
        }else {
            list.add(client);
        }
        socketIOClientMap.put(bqCode, list);
        //回发消息
        client.sendEvent("message", "onConnect back");
        log.info("客户端:" + client.getSessionId() + "已连接,mac=" + bqCode);
    }

    /**
     * 客户端关闭连接时触发
     *
     * @param client
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        String bqCode = client.getHandshakeData().getSingleUrlParam("bqCode");
        if(StringUtils.isEmpty(bqCode)){
            bqCode=ALL_BQ_CODE;
        }
        List<SocketIOClient> list=socketIOClientMap.get(bqCode);
        if (list !=null && list.contains(client)){
            list.remove(client);
        }
        log.info("客户端:" + client.getSessionId() + "断开连接");
    }

    /**
     * 客户端事件
     *
     * @param client  　客户端信息
     * @param request 请求信息
     */
    @OnEvent(value = "push_event")
    public void onEvent(SocketIOClient client, AckRequest request, String message) {
        log.info("发来消息：" + message);
        //回发消息
        client.sendEvent("push_event", message);
        //广播消息
      //  sendBroadcast();
    }

    /**
     * 广播消息
     */
    public static void sendBroadcast(String message,String eventType) {
        List<SocketIOClient> sendedList=new ArrayList<>();
        for (List<SocketIOClient> list : socketIOClientMap.values()) {
            for (SocketIOClient client:list){
                if (client.isChannelOpen()&& !sendedList.contains(client)) {
                    sendedList.add(client);
                    client.sendEvent(eventType, message);
                }
            }
        }
        sendedList.clear();
    }

    /**
     * 广播消息
     */
    public static void sendToBq(String bqCode,String message,String eventType) {
        if (bqCode.equals(ALL_BQ_CODE)){
            sendBroadcast(message,eventType);
        }else {
            List<SocketIOClient> sendedList=new ArrayList<>();
            List<SocketIOClient> allList=socketIOClientMap.get(ALL_BQ_CODE);
            if(allList !=null && allList.size()>0){
                for (SocketIOClient client:allList){
                    if (client.isChannelOpen()&& !sendedList.contains(client)) {
                        sendedList.add(client);
                        client.sendEvent(eventType, message);
                    }
                }
            }
            List<SocketIOClient> bqList=socketIOClientMap.get(bqCode);
            if(bqList !=null && bqList.size()>0){
                for (SocketIOClient client:bqList){
                    if (client.isChannelOpen()&& !sendedList.contains(client)) {
                        sendedList.add(client);
                        client.sendEvent(eventType, message);
                    }
                }
            }
            sendedList.clear();
        }

    }


}
