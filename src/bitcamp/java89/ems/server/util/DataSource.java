package bitcamp.java89.ems.server.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

// ioc컨테이너! 얘도 객체생성해서 관리해!
// 태그의 역할 
@Component
public class DataSource {
  ArrayList<Connection> conPool = new ArrayList<>(); //커넥션 담을 것.

  public DataSource() {
    try {
      Class.forName("com.mysql.jdbc.Driver"); //클래스 로딩 여기서 끝.
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public Connection getConnection() throws Exception {
    if (conPool.size() == 0) {
      System.out.println("DB 커넥션 생성");
      return DriverManager.getConnection("jdbc:mysql://localhost:3306/java89db", 
          "java89", "1111");
    } else {
      return conPool.remove(0); //0 번방에 있는 객체 리턴. 꺼내면서 제거 -> 사이즈가 줄어듦.
    }
  }

  public void returnConnection(Connection con) {
    try {
      // 커넥션을 돌려주기전 아직까지 잘 연결되있는지 확인하는것 죽어있으면 리턴할것. 
      if (!con.isClosed() && con.isValid(5)) { //닫히지 않고/유효하다면
        conPool.add(con); //제대로 된애만 저장.
      }
    } catch (Exception e) {}
  }
}
