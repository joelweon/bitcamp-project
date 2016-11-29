package bitcamp.java89.ems.server;

import java.net.ServerSocket;
import java.util.HashMap;

import bitcamp.java89.ems.server.context.ApplicationContext;

public class EduAppServer {
//  Ioc 컨테이너
  HashMap<String,Object> commandMap = new HashMap<>();
  ApplicationContext appContext;

  public EduAppServer() {
    appContext = new ApplicationContext(new String[] {
        "bitcamp.java89.ems.server.controller",
        "bitcamp.java89.ems.server.dao"});
  }

  private void service() throws Exception {
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중...");

    while (true) {
      new RequestThread(ss.accept(), appContext).start();
    }
  }// service()

  public static void main(String[] args) throws Exception {
    EduAppServer eduServer = new EduAppServer();
    eduServer.service();
  }// main()
}
