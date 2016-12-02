package bitcamp.java89.ems.server;

import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import bitcamp.java89.ems.server.context.ApplicationContext;
import bitcamp.java89.ems.server.context.RequestHandlerMapping;

public class EduAppServer {
//  Ioc 컨테이너
  ApplicationContext appContext;
  RequestHandlerMapping handlerMapping;

  public EduAppServer() {
    appContext = new ApplicationContext(new String[]{"bitcamp.java89.ems.server"});
    
//    객체를 조사하여 @RequestMapping이 붙은 메서드를 따로 관리한다.
    handlerMapping = new RequestHandlerMapping(appContext.getAllBeans());
    //모든 객체의 생성자를 넘겨준다.
  }

  private void service() throws Exception {
    ServerSocket ss = new ServerSocket(8888);
    System.out.println("서버 실행 중...");

    while (true) {
      new RequestThread(ss.accept(), handlerMapping).start();
    }
  }// service()

  public static void main(String[] args) throws Exception {
    EduAppServer eduServer = new EduAppServer();
    eduServer.service();
  }// main()
}
