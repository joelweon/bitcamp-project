package bitcamp.java89.ems;
import java.util.Scanner;
public class TeacherController {
  private LinkedList<Teacher> list;
  static Scanner keyScan;

  public TeacherController(Scanner keyScan) {
    list = new LinkedList<Teacher>();
    this.keyScan = keyScan;
  }

  public void service() {
    loop:
    while (true) {
      System.out.print("강사관리> ");
      String command = keyScan.nextLine().toLowerCase();
      try {
        switch (command) {
          case "add": this.doAdd(); break;
          case "list": this.doList(); break;
          case "view": this.doView(); break;
          case "delete": this.doDelete(); break;
          case "update": this.doUpdate(); break;
          case "main":
            break loop;
          default:
            System.out.println("지원하지 않는 명령어입니다.");
        }
      } catch (IndexOutOfBoundsException e) {
        System.out.println("인덱스가 유효하지 않습니다.");
      } catch (Exception e) {
        System.out.println("실행 중 오류가 발생했습니다.");
      }
    } //while
  } //service 

  private void doAdd() {
    while (true) {
      Teacher teacher = new Teacher();

      System.out.print("아이디?(예:qwe) ");
      teacher.id = this.keyScan.nextLine();

      System.out.print("이름?(예:홍길동) ");
      teacher.name = this.keyScan.nextLine();

      System.out.print("이메일?(예:bit@bitc.com) ");
      teacher.email = this.keyScan.nextLine();

      System.out.print("전화번호?(예:000-1234-5678) ");
      teacher.tel = this.keyScan.nextLine();

      System.out.print("전공?(예:컴퓨터공학) ");
      teacher.major = this.keyScan.nextLine();

      System.out.print("주요언어?(예:C, JAVA) ");
      teacher.majorLanguage = this.keyScan.nextLine();

      System.out.print("저서?(예:자바 웹 개발 워크북) ");
      teacher.book = this.keyScan.nextLine();

      System.out.print("프로젝트명?(예:ABC) ");
      teacher.projectName = this.keyScan.nextLine();

      System.out.print("Git주소?(예:https://github.com/abcd ");
      teacher.gitAddress = this.keyScan.nextLine();
      while (true) {
        try {
          System.out.print("직장경력?(예:10) ");
          teacher.workExperience = Integer.parseInt(this.keyScan.nextLine());

          System.out.print("강의경력?(예:3) ");
          teacher.lectureExperience = Integer.parseInt(keyScan.nextLine());

          System.out.print("나이?(예:40) ");
          teacher.age = Integer.parseInt(this.keyScan.nextLine());

          System.out.print("희망급여?(예:1000000) ");
          teacher.salary = Integer.parseInt(this.keyScan.nextLine());
          break;
        } catch (Exception e) {
          System.out.println("정수를 입력하세요.");
        }
      }

      list.add(teacher);

       System.out.print("계속 입력하시겠습니까?(y/n) ");
        if (!keyScan.nextLine().equals("y"))
          break;    
    } //while
  }  //add method

  private void doList() {
    for (int i = 0; i < list.size(); i++) {
      Teacher teacher = list.get(i);
      System.out.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s,%d,%d,%d,%d\n",
        teacher.id,
        teacher.name,
        teacher.email,
        teacher.tel,
        teacher.major,
        teacher.majorLanguage,
        teacher.book,
        teacher.projectName,
        teacher.gitAddress,
        teacher.workExperience,
        teacher.lectureExperience,
        teacher.age,
        teacher.salary);
      System.out.println("-----------------------------------");
    }
  }

  private void doView() {
    System.out.print("강사의 인덱스? ");
    int index = Integer.parseInt(this.keyScan.nextLine());
    Teacher teacher = list.get(index);

    System.out.println("-----------------------------------");
    System.out.printf("아이디: %s\n", teacher.id);
    System.out.printf("이름: %s\n", teacher.name);
    System.out.printf("이메일: %s\n", teacher.email);
    System.out.printf("전화번호: %s\n", teacher.tel);
    System.out.printf("주요언어: %s\n", teacher.majorLanguage);
    System.out.printf("저서: %s\n", teacher.book);
    System.out.printf("프로젝트명: %s\n", teacher.projectName);
    System.out.printf("Git주소: %s\n", teacher.gitAddress);
    System.out.printf("직장경력: %d\n", teacher.workExperience);
    System.out.printf("강의경력: %d\n", teacher.lectureExperience);
    System.out.printf("나이: %d\n", teacher.age);
    System.out.printf("희망급여: %d\n", teacher.salary);
    System.out.println("-----------------------------------");
  }

  private void doDelete() {
    System.out.print("삭제할 강사의 인덱스? ");
    int index = Integer.parseInt(keyScan.nextLine());
    Teacher deletedTeacher = list.remove(index);
    System.out.printf("%s 강사 정보를 삭제하였습니다.\n", deletedTeacher.id);
  }

  private void doUpdate() {
    System.out.print("변경할 강사의 인덱스 입력: ");
    int index = Integer.parseInt(this.keyScan.nextLine());
    Teacher oldTeacher = list.get(index);

    Teacher teacher = new Teacher();

    System.out.printf("이름(%s)? ", oldTeacher.name);
    teacher.name = this.keyScan.nextLine();

    System.out.printf("이메일(%s)? ", oldTeacher.email);
    teacher.email = this.keyScan.nextLine();

    System.out.printf("전화번호(%s)? ", oldTeacher.tel);
    teacher.tel = this.keyScan.nextLine();

    System.out.printf("전공(%s)? ", oldTeacher.major);
    teacher.major = this.keyScan.nextLine();

    System.out.printf("주요언어(%s)? ", oldTeacher.majorLanguage);
    teacher.majorLanguage = this.keyScan.nextLine();

    System.out.printf("저서(%s)? ", oldTeacher.book);
    teacher.book = this.keyScan.nextLine();

    System.out.printf("프로젝트명(%s)? ", oldTeacher.projectName);
    teacher.projectName = this.keyScan.nextLine();

    System.out.printf("Git주소(%s)? ", oldTeacher.gitAddress);
    teacher.gitAddress = this.keyScan.nextLine();
    while (true) {
      try {
        System.out.printf("직장경력(%d)? ", oldTeacher.workExperience);
        teacher.workExperience = Integer.parseInt(this.keyScan.nextLine());

        System.out.printf("강의경력(%d)? ", oldTeacher.lectureExperience);
        teacher.lectureExperience = Integer.parseInt(keyScan.nextLine());

        System.out.printf("나이(%d)? ", oldTeacher.age);
        teacher.age = Integer.parseInt(this.keyScan.nextLine());

        System.out.printf("희망급여(%d)? ", oldTeacher.salary);
        teacher.salary = Integer.parseInt(this.keyScan.nextLine());
        break;
      } catch (Exception e) {
        System.out.println("정수를 입력하세요.");
      }
    }

    System.out.println("저장하시겠습니까?(y/n)");

    if (keyScan.nextLine().toLowerCase().equals("y")) {
      teacher.id = oldTeacher.id;
      list.set(index, teacher);
      System.out.println("저장하였습니다.");
    } else {
      System.out.println("변경을 취소하였습니다.");
    }
  } //doUpdate

}  //class
