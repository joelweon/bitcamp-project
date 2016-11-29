package bitcamp.java89.ems.server.controller;

import java.io.PrintStream;
import java.util.HashMap;

import bitcamp.java89.ems.server.annotation.Component;
import bitcamp.java89.ems.server.annotation.RequestMapping;
import bitcamp.java89.ems.server.dao.ContactDao;
import bitcamp.java89.ems.server.vo.Contact;


@Component(value="contact/update")
public class ContactUpdateController {
  
  //의존 객체 DAO를 저장할 변수 선언
  ContactDao contactDao;
  
  // 
  public void setContactDao(ContactDao contactDao) {
    this.contactDao = contactDao;
  }
  
  @RequestMapping
  public void update(HashMap<String,String> paramMap, PrintStream out)
      throws Exception {
//  주입받은 ContactDao를 사용할 것이기 때문에
//  더이상 이 메서드에서 ContactDao 객체를 준비하지 않는다.
//    ContactDao contactDao = ContactDao.getInstance();
//  => 단 이 메서드가 호출되기 전에 반드시 ContactDao가 주입되어 있어야한다.
    
    if (!contactDao.existEmail(paramMap.get("email"))) {
      out.println("해당 이메일이 없습니다.");
      return;
    }
    
    Contact contact = new Contact();
    contact.setEmail(paramMap.get("email"));
    contact.setName(paramMap.get("name"));
    contact.setPosition(paramMap.get("position"));
    contact.setTel(paramMap.get("tel"));
    
    contactDao.update(contact);
    out.println("변경 완료했습니다.");
  }
}