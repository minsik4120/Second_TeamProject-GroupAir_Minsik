package org.spring.groupAir.chat;

import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@ServerEndpoint(value = "/chat")
public class ChatConfig {

    private static Set<Session> employeeInfo =
            Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    private void onOpen(Session session) {
        System.out.println("세션 시작: " + session.toString());

        // 세션 정보가 없다면
        if (!employeeInfo.contains(session)) {

            // 세션 설정
            employeeInfo.add(session);

            System.out.println("세션 열음: " + session);
        } else {
            System.out.println("세션 없음!");
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) throws Exception {

        System.out.println("메세지 정보: " + message);
        for (Session sMessage : employeeInfo) {
            System.out.println("받아온 메세지: " + sMessage);

            // 전송자 URL
            sMessage.getBasicRemote().sendText(message);
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("세션 종료: " + session);
        employeeInfo.remove(session); // 세션 삭제
    }

    @OnError
    public void error(Throwable throwable) {
        System.out.println("채팅 에러!!!: ");
        throwable.printStackTrace();
    }

}
