package member.model;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("MemberDao")
public class MemberDao {

	private final String namespace = "member.model.Member";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public void insertMember(MemberBean member) {
		member.getName();
		member.getHp();
		System.out.println("member.getName();"+member.getName());
		System.out.println("member.getHp();"+member.getHp());
		System.out.println("member;"+member.getId());
		System.out.println("member;"+member.getPassword());
		System.out.println("member;"+member.getEmail());
		System.out.println("member;"+member.getZipcode1());
		sqlSessionTemplate.insert(namespace+".InsertMember", member);
	}

	public List<MemberBean> getMemberList() {
		List<MemberBean> list = new ArrayList<MemberBean>();
		list = sqlSessionTemplate.selectList(namespace+".GetMemberList");
		return list;
	}

	public MemberBean getMember(String id) {
		MemberBean login = sqlSessionTemplate.selectOne(namespace+".GetMember", id);
		return login;
	}

	public int idcheck(String inputid) {
		int cnt = -1;
		cnt = sqlSessionTemplate.selectOne(namespace+".Idcheck", inputid);
		return cnt;
	}
	
	
	
	
}
