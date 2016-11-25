package bitcamp.java89.ems.server;

import java.io.File;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

public class EduAppServer {
//  클라이언트 요청을 처리할 Command 구현체를 보관한다.
//  HashMap<명령문자열, 요청처리 객체> -> commandMap
  HashMap<String,Command> commandMap = new HashMap<>();

  public EduAppServer() {
//    bin 폴더를 뒤져서 AbstractCommand의 서브 클래스를 찾아낸다.
    ArrayList<Class> classList = new ArrayList<>();
    ReflectionUtil.getCommandClasses(new File("./bin"), classList);
    
    for (Class c : classList) {
      try {
//      클라이언트 요청을 처리할 Command 구현체 준비
        AbstractCommand command = (AbstractCommand)c.newInstance();
        commandMap.put(command.getCommandString(),  command);
      } catch (Exception e) {}
    }
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
