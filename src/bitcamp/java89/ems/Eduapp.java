package bitcamp.java89.ems;
import java.util.Scanner;
public class EduApp {

  static Scanner keyScan = new Scanner(System.in);

  public static void main(String[] args) {
      TeacherController teacherController = new TeacherController(keyScan);

    System.out.println();
    System.out.printf("비트캠프 관리시스템에 오신걸 환영합니다.\n\n");

    loop:
    while (true) {
      System.out.print("명령> ");
      String command = keyScan.nextLine().toLowerCase();

      switch (command) {
        case "add": teacherController.doAdd(); break;
        case "list": teacherController.doList(); break;
        case "view": teacherController.doView(); break;
        case "delete": teacherController.doDelete(); break;
        case "update": teacherController.doUpdate(); break;
        case "quit":
          System.out.println("Good Bye!");
          break loop;
        default:
          System.out.println("지원하지 않는 명령어입니다.");
      }
    }

  }

}
