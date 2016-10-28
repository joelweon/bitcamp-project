package bitcamp.java89.ems;
import java.util.Scanner;
public class Eduapp {

  public static void main(String[] args) {
    System.out.println();
    System.out.printf("비트캠프 관리시스템에 오신걸 환영합니다.\n\n");

    Teacher[] teachers = new Teacher[100];
    int length = 0;
    Scanner keyScan = new Scanner(System.in);
    while (length < teachers.length) {
      Teacher teacher = new Teacher();

      System.out.print("이름?(예:홍길동) ");
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

      System.out.print("계속 입력하시겠습니까?(y/n)");
      if (!keyScan.nextLine().equals("y"))
        break;
    }

    printTeacherList(teachers, length);
  }
  static void printTeacherList(Teacher[] teachers, int length) {
    for (int i = 0; i < length; i++) {
      Teacher teacher = teachers[i];
      System.out.printf("%s,%s,%s,%s,%s,%s,%s,%s,%d,%d,%d,%d\n",
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
    }
  }

}
