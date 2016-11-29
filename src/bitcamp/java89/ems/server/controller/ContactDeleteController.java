package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.AbstractCommand;
import bitcamp.java89.ems.server.dao.ContactDao;

public class ContactDeleteController extends AbstractCommand {
//의존 객체 DAO를 저장할 변수 선언
ContactDao contactDao;

// 
public void setContactDao(ContactDao contactDao) {
  this.contactDao = contactDao;
}
  @Override
  public String getCommandString() {
    return "contact/delete";
  }
  @Override
  protected void doResponse(HashMap<String,String> paramMap, PrintStream out)
      throws Exception {
//  주입받은 ContactDao를 사용할 것이기 때문에
//  더이상 이 메서드에서 ContactDao 객체를 준비하지 않는다.
//    ContactDao contactDao = ContactDao.getInstance();
//  => 단 이 메서드가 호출되기 전에 반드시 ContactDao가 주입되어 있어야한다.
    if (!contactDao.existEmail(paramMap.get("email"))) {
      out.println("존재하지 않는 이메일 입니다.");
      return;
    }

    contactDao.delete(paramMap.get("email"));
    out.println("삭제하였습니다.");
  }
}