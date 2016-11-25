package bitcamp.java89.ems.server;

import java.net.ServerSocket;
import java.util.HashMap;

import bitcamp.java89.ems.server.controller.ContactAddController;
import bitcamp.java89.ems.server.controller.ContactDeleteController;
import bitcamp.java89.ems.server.controller.ContactListController;
import bitcamp.java89.ems.server.controller.ContactUpdateController;
import bitcamp.java89.ems.server.controller.ContactViewController;
import bitcamp.java89.ems.server.controller.TeacherAddController;
import bitcamp.java89.ems.server.controller.TeacherDeleteController;
import bitcamp.java89.ems.server.controller.TeacherListController;
import bitcamp.java89.ems.server.controller.TeacherUpdateController;
import bitcamp.java89.ems.server.controller.TeacherViewController;

public class EduAppServer {
//  클라이언트 요청을 처리할 Command 구현체를 보관한다.
//  HashMap<명령문자열, 요청처리 객체> -> commandMap
  HashMap<String,Command> commandMap = new HashMap<>();

  public EduAppServer() {
//    클라이언트 요청을 처리할 Command 구현체 준비
    commandMap.put("contact/list", new ContactListController());
    commandMap.put("contact/view", new ContactViewController());
    commandMap.put("contact/add", new ContactAddController());
    commandMap.put("contact/delete", new ContactDeleteController());
    commandMap.put("contact/update", new ContactUpdateController());

    commandMap.put("teacher/list", new TeacherListController());
    commandMap.put("teacher/view", new TeacherViewController());
    commandMap.put("teacher/add", new TeacherAddController());
    commandMap.put("teacher/delete", new TeacherDeleteController());
    commandMap.put("teacher/update", new TeacherUpdateController());
  }

  private void service() throws Exception {
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중...");

    while (true) {
      new RequestThread(ss.accept(), commandMap).start();
    }
  }// service()

  public static void main(String[] args) throws Exception {
    EduAppServer eduServer = new EduAppServer();
    eduServer.service();
  }// main()
}
