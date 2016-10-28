package bitcamp.java89.ems;
import java.util.Scanner;
public class Eduapp {

  public static void main(String[] args) {
    Scanner keyScan = new Scanner(System.in);
    System.out.println();
    System.out.printf("비트캠프 관리시스템에 오신걸 환영합니다.\n\n");
    Teacher teacher = new Teacher();


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

    System.out.printf("이름: %s\n", teacher.name);
    System.out.printf("이메일: %s\n", teacher.email);
    System.out.printf("전화번호: %s\n", teacher.tel);
    System.out.printf("전공: %s\n", teacher.major);
    System.out.printf("주요언어: %s\n", teacher.majorLanguage);
    System.out.printf("저서: %b\n", teacher.book);
    System.out.printf("프로젝트명: %d\n", teacher.projectName);
    System.out.printf("Git주소: %s\n", teacher.gitAddress);
    System.out.printf("직장경력: %s\n", teacher.workExperience);
    System.out.printf("강의경력: %s\n", teacher.lectureExperience);
    System.out.printf("나이: %s\n", teacher.age);
    System.out.printf("희망급여: %s\n", teacher.salary);
  }
}
