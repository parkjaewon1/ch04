package hib.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hib.dao.PersonDao;
import hib.model.Person;
@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonDao pd;
	public int insert(Person person) { // person 입력할 데이터
		int result = 0;
		// 중복체크 person2는 DB에서 읽어온 데이터
		Person person2 = pd.select(person.getId());
		if (person2 == null) {
			pd.save(person);
			result = 1;
		} else System.out.println("이미 있는 데이터 입니다");
		return result;
	}
	public void select(String id) {
		Person person = pd.select(id);
		if (person == null) System.out.println("없는 아이디 입니다");
		else print(person);
	}
	private void print(Person person) {
		System.out.println("=== 회원 정보 ===");
		System.out.println("아이디 : "+person.getId());
		System.out.println("이름 : "+person.getName());
	}
	public int update(Person person) {
		int result = 0;
		// 중복체크 person2는 DB에서 읽어온 데이터
		Person person2 = pd.select(person.getId());
		if (person2 != null) {
			pd.update(person);
			result = 1;
		} else System.out.println("없는 데이터는 수정할 수 없습니다");
		return result;
	}
	public void list() {
		List<Person> list = pd.list();
		if (list == null || list.size() == 0) System.out.println("데이터 없네");
		else
			for(Person person:list) 
				print(person);
	}
	public int delete(String id) {
		int result = 0;
		// 중복체크 person2는 DB에서 읽어온 데이터
		Person person2 = pd.select(id);
		if (person2 != null) {
			pd.delete(id);
			result = 1;
		} else System.out.println("없는 데이터는 삭제할 수 없습니다");
		return result;
	}
}