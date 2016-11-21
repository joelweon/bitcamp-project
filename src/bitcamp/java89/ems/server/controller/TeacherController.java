//package bitcamp.java89.ems.server.controller;
//
//import java.util.Scanner;
//import java.util.ArrayList;
//import java.io.FileOutputStream;
//import java.io.DataOutputStream;
//import java.io.FileInputStream;
//import java.io.DataInputStream;
//import java.io.EOFException;
//// import java.io.IOException;
//
//public class TeacherController {
//  public String filename = "teacher.data";
//  private ArrayList<Teacher> list;
//  private boolean changed;
//  static Scanner keyScan;
//
//  public TeacherController(Scanner keyScan) {
//    list = new ArrayList<Teacher>();
//    this.keyScan = keyScan;
//
//    this.load();
//  }
//
//  public boolean isChanged() {
//    return changed;
//  }
//
//  private void load() {
//    FileInputStream in0 = null;  //로컬 변수는 초기화 되지 않은 상태에서 사용할 수 없다.
//    DataInputStream in = null;   //static,instance는 자동 초기화 된다.
//    try {
//      in0 = new FileInputStream(this.filename);
//      in = new DataInputStream(in0);
//    
//      while (true) {
//        Teacher teacher = new Teacher();
//        teacher.id = in.readUTF();
//        teacher.name = in.readUTF();
//        teacher.email = in.readUTF();
//        teacher.tel = in.readUTF();
//        teacher.major = in.readUTF();
//        teacher.majorLanguage = in.readUTF();
//        teacher.book = in.readUTF();
//        teacher.projectName = in.readUTF();
//        teacher.gitAddress = in.readUTF();
//        teacher.workExperience = in.readInt();
//        teacher.lectureExperience = in.readInt();
//        teacher.age = in.readInt();
//        teacher.salary = in.readInt();
//        this.list.add(teacher);
//      }
//    } catch (EOFException e) {
//      //파일을 모두 읽었다.
//    } catch (Exception e) {
//      System.out.println("강사 데이터 로딩 중 오류 발생!");
//    } finally {
//      try {
//        in.close();
//        in0.close();
//      } catch (Exception e) {
//        //close 하다가 예외 발생하면 무시한다.
//      }
//    }
//  }
//
//  public void save() throws Exception {
//    FileOutputStream out0 = new FileOutputStream(this.filename);
//    DataOutputStream out = new DataOutputStream(out0);
//
//    for (Teacher teacher : this.list) {
//      out.writeUTF(teacher.id);
//      out.writeUTF(teacher.name);
//      out.writeUTF(teacher.email);
//      out.writeUTF(teacher.tel);
//      out.writeUTF(teacher.major);
//      out.writeUTF(teacher.majorLanguage);
//      out.writeUTF(teacher.book);
//      out.writeUTF(teacher.projectName);
//      out.writeUTF(teacher.gitAddress);
//      out.writeInt(teacher.workExperience);
//      out.writeInt(teacher.lectureExperience);
//      out.writeInt(teacher.age);
//      out.writeInt(teacher.salary);
//    }
//    changed = false;
//    out.close();
//    out0.close();
//  }
//
//  public void service() throws Exception {
//    loop:
//    while (true) {
//      System.out.print("강사관리> ");
//      String command = keyScan.nextLine().toLowerCase();
//      try {
//        switch (command) {
//          case "add": this.doAdd(); break;
//          case "list": this.doList(); break;
//          case "view": this.doView(); break;
//          case "delete": this.doDelete(); break;
//          case "update": this.doUpdate(); break;
//          case "main":
//            break loop;
//          default:
//            System.out.println("지원하지 않는 명령어입니다.");
//        }
//      } catch (IndexOutOfBoundsException e) {
//        System.out.println("인덱스가 유효하지 않습니다.");
//      } catch (Exception e) {
//        System.out.println("실행 중 오류가 발생했습니다.");
//      }
//    } //while
//  } //service 
//
//  private void doAdd() {
//    while (true) {
//      Teacher teacher = new Teacher();
//
//      System.out.print("아이디?(예:qwe) ");
//      teacher.id = this.keyScan.nextLine();
//
//      System.out.print("이름?(예:홍길동) ");
//      teacher.name = this.keyScan.nextLine();
//
//      System.out.print("이메일?(예:bit@bitc.com) ");
//      teacher.email = this.keyScan.nextLine();
//
//      System.out.print("전화번호?(예:000-1234-5678) ");
//      teacher.tel = this.keyScan.nextLine();
//
//      System.out.print("전공?(예:컴퓨터공학) ");
//      teacher.major = this.keyScan.nextLine();
//
//      System.out.print("주요언어?(예:C, JAVA) ");
//      teacher.majorLanguage = this.keyScan.nextLine();
//
//      System.out.print("저서?(예:자바 웹 개발 워크북) ");
//      teacher.book = this.keyScan.nextLine();
//
//      System.out.print("프로젝트명?(예:ABC) ");
//      teacher.projectName = this.keyScan.nextLine();
//
//      System.out.print("Git주소?(예:https://github.com/abcd ");
//      teacher.gitAddress = this.keyScan.nextLine();
//      while (true) {
//        try {
//          System.out.print("직장경력?(예:10) ");
//          teacher.workExperience = Integer.parseInt(this.keyScan.nextLine());
//
//          System.out.print("강의경력?(예:3) ");
//          teacher.lectureExperience = Integer.parseInt(keyScan.nextLine());
//
//          System.out.print("나이?(예:40) ");
//          teacher.age = Integer.parseInt(this.keyScan.nextLine());
//
//          System.out.print("희망급여?(예:1000000) ");
//          teacher.salary = Integer.parseInt(this.keyScan.nextLine());
//          break;
//        } catch (Exception e) {
//          System.out.println("정수를 입력하세요.");
//        }
//      }
//
//      list.add(teacher);
//
//       System.out.print("계속 입력하시겠습니까?(y/n) ");
//        if (!keyScan.nextLine().equals("y"))
//          break;    
//    } //while
//    changed = true;
//  }  //add method
//
//  private void doList() {
//    for (Teacher teacher : list) {
//      System.out.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s,%d,%d,%d,%d\n",
//        teacher.id,
//        teacher.name,
//        teacher.email,
//        teacher.tel,
//        teacher.major,
//        teacher.majorLanguage,
//        teacher.book,
//        teacher.projectName,
//        teacher.gitAddress,
//        teacher.workExperience,
//        teacher.lectureExperience,
//        teacher.age,
//        teacher.salary);
//      System.out.println("-----------------------------------");
//    }
//  }
//
//  private void doView() {
//    System.out.print("강사의 인덱스? ");
//    int index = Integer.parseInt(this.keyScan.nextLine());
//    Teacher teacher = list.get(index);
//
//    System.out.println("-----------------------------------");
//    System.out.printf("아이디: %s\n", teacher.id);
//    System.out.printf("이름: %s\n", teacher.name);
//    System.out.printf("이메일: %s\n", teacher.email);
//    System.out.printf("전화번호: %s\n", teacher.tel);
//    System.out.printf("주요언어: %s\n", teacher.majorLanguage);
//    System.out.printf("저서: %s\n", teacher.book);
//    System.out.printf("프로젝트명: %s\n", teacher.projectName);
//    System.out.printf("Git주소: %s\n", teacher.gitAddress);
//    System.out.printf("직장경력: %d\n", teacher.workExperience);
//    System.out.printf("강의경력: %d\n", teacher.lectureExperience);
//    System.out.printf("나이: %d\n", teacher.age);
//    System.out.printf("희망급여: %d\n", teacher.salary);
//    System.out.println("-----------------------------------");
//  }
//
//  private void doDelete() {
//    System.out.print("삭제할 강사의 인덱스? ");
//    int index = Integer.parseInt(keyScan.nextLine());
//    Teacher deletedTeacher = list.remove(index);
//    changed = true;
//    System.out.printf("%s 강사 정보를 삭제하였습니다.\n", deletedTeacher.id);
//  }
//
//  private void doUpdate() {
//    System.out.print("변경할 강사의 인덱스 입력: ");
//    int index = Integer.parseInt(this.keyScan.nextLine());
//    Teacher oldTeacher = list.get(index);
//
//    Teacher teacher = new Teacher();
//
//    System.out.printf("이름(%s)? ", oldTeacher.name);
//    teacher.name = this.keyScan.nextLine();
//
//    System.out.printf("이메일(%s)? ", oldTeacher.email);
//    teacher.email = this.keyScan.nextLine();
//
//    System.out.printf("전화번호(%s)? ", oldTeacher.tel);
//    teacher.tel = this.keyScan.nextLine();
//
//    System.out.printf("전공(%s)? ", oldTeacher.major);
//    teacher.major = this.keyScan.nextLine();
//
//    System.out.printf("주요언어(%s)? ", oldTeacher.majorLanguage);
//    teacher.majorLanguage = this.keyScan.nextLine();
//
//    System.out.printf("저서(%s)? ", oldTeacher.book);
//    teacher.book = this.keyScan.nextLine();
//
//    System.out.printf("프로젝트명(%s)? ", oldTeacher.projectName);
//    teacher.projectName = this.keyScan.nextLine();
//
//    System.out.printf("Git주소(%s)? ", oldTeacher.gitAddress);
//    teacher.gitAddress = this.keyScan.nextLine();
//    while (true) {
//      try {
//        System.out.printf("직장경력(%d)? ", oldTeacher.workExperience);
//        teacher.workExperience = Integer.parseInt(this.keyScan.nextLine());
//
//        System.out.printf("강의경력(%d)? ", oldTeacher.lectureExperience);
//        teacher.lectureExperience = Integer.parseInt(keyScan.nextLine());
//
//        System.out.printf("나이(%d)? ", oldTeacher.age);
//        teacher.age = Integer.parseInt(this.keyScan.nextLine());
//
//        System.out.printf("희망급여(%d)? ", oldTeacher.salary);
//        teacher.salary = Integer.parseInt(this.keyScan.nextLine());
//        break;
//      } catch (Exception e) {
//        System.out.println("정수를 입력하세요.");
//      }
//    }
//
//    System.out.println("저장하시겠습니까?(y/n)");
//
//    if (keyScan.nextLine().toLowerCase().equals("y")) {
//      teacher.id = oldTeacher.id;
//      list.set(index, teacher);
//      System.out.println("저장하였습니다.");
//      changed = true;
//    } else {
//      System.out.println("변경을 취소하였습니다.");
//    }
//  } //doUpdate
//
//}  //class
