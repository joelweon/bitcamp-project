/* RequistThread(사용자)와 TeacherController(피사용자) 사이의 호출 규칙 정의
*/

package bitcamp.java89.ems.server;

import java.io.PrintStream;
import java.util.HashMap;

public interface Command {
  void service(HashMap<String,String> paramMap, PrintStream out);
}


/* 인터페이스를 구현한 클래스를 만드느냐?
 * 아니면 호출하는 입장에서 규칙에 따라 호출할 것인가?
 * 
 * 인터페이스는 규칙.
 * 호출하는 자와 호출당하는 자.
 * 
 * 어떤 컨트롤러가 만들어질지 모르지만 리퀘는 그런거 고려안하고(클래스이름 언급x) 그저 꺼내서 씀.
*/
