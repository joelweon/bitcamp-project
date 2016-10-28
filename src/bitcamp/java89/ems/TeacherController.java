package bitcamp.java89.ems;
import java.util.Scanner;
public class TeacherController {
  static Teacher[] teachers = new Teacher[100];
  static int length = 0;
  static Scanner keyScan;

  static void doAdd() {
    while (length < teachers.length) {
      Teacher teacher = new Teacher();
      Scanner keyScan = new Scanner(System.in);

      System.out.print("아이디?(예:qwe) ");
      teacher.id = keyScan.nextLine();

      System.out.print("이름?(예:남경훈) ");
      teacher.name = keyScan.nextLine();

      System.out.print("이메일?(예:bit@bitc.com) ");
      teacher.email = keyScan.nextLine();

      System.out.print("전화번호?(예:000-1234-5678) ");
      teacher.tel = keyScan.nextLine();

      System.out.print("전공?(예:컴퓨터공학) ");
      teacher.major = keyScan.nextLine();

      System.out.print("주요언어?(예:C, JAVA) ");
      teacher.majorLanguage = keyScan.nextLine();

      System.out.print("저서?(예:자바 웹 개발 워크북 소개) ");
      teacher.book = keyScan.nextLine();

      System.out.print("프로젝트명?(예:ABC) ");
      teacher.projectName = keyScan.nextLine();

      System.out.print("Git주소?(예:https://github.com/abcd) ");
      teacher.gitAddress = keyScan.nextLine();

      System.out.print("직장경력?(예:10) ");
      teacher.workExperience = Integer.parseInt(keyScan.nextLine());

      System.out.print("강의경력?(예:3) ");
      teacher.lectureExperience = Integer.parseInt(keyScan.nextLine());

      System.out.print("나이?(예:40) ");
      teacher.age = Integer.parseInt(keyScan.nextLine());

      System.out.print("희망급여?(예:100000) ");
      teacher.salary = Integer.parseInt(keyScan.nextLine());

      teachers[length++] = teacher;
      System.out.print("계속 입력하시겠습니까?(y/n) ");
      if (!keyScan.nextLine().equals("y"))
        break;
    }
  }


  static void doList() {
    Teacher teacher = null;
    for (int i = 0; i < length; i++) {
      teacher = teachers[i];
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

  static void doView() {
    System.out.print("조회할 id입력: ");
    String id = keyScan.nextLine().toLowerCase();
    for (int i = 0; i < length; i++) {
      if (teachers[i].id.toLowerCase().equals(id)) {
        System.out.printf("아이디: %s\n", teachers[i].id);
        System.out.printf("이름: %s\n", teachers[i].name);
        System.out.printf("이메일: %s\n", teachers[i].email);
        System.out.printf("전화번호: %s\n", teachers[i].tel);
        System.out.printf("주요언어: %s\n", teachers[i].majorLanguage);
        System.out.printf("저서: %s\n", teachers[i].book);
        System.out.printf("프로젝트명: %s\n", teachers[i].projectName);
        System.out.printf("Git주소: %s\n", teachers[i].gitAddress);
        System.out.printf("직장경력: %d\n", teachers[i].workExperience);
        System.out.printf("강의경력: %d\n", teachers[i].lectureExperience);
        System.out.printf("나이: %d\n", teachers[i].age);
        System.out.printf("희망급여: %d\n", teachers[i].salary);

        System.out.println("--------------------aa------------");
        break;
      }
    }
  }
}
