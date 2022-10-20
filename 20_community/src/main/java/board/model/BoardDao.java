package board.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("BoardDao")
public class BoardDao {
	private final String namespace = "board.model.Board";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public void insertBoardInfo(BoardBean board) {
		sqlSessionTemplate.insert(namespace+".InsertBoardInfo" ,board);
		
	}

	public int getTotalCount(Map<String, String> map) {
		int totalCount = sqlSessionTemplate.selectOne(namespace+".GetTotalCount", map);
		return totalCount;
	}

	public List<BoardBean> getAllBoard(Paging pageInfo, Map<String, String> map) {
		List<BoardBean> lists = new ArrayList<BoardBean>();
		RowBounds rouBounds= new RowBounds(pageInfo.getOffset(),pageInfo.getLimit());//���� ������ ���� �ٲ�� ����Ʈ�� ��Ŵ...
		lists = sqlSessionTemplate.selectList(namespace+".GetAllBoard", map, rouBounds);
		return lists;
	}

	public void updateReadcount(String num) {
		sqlSessionTemplate.update(namespace+".UpdateReadcount", num);		
	}

	public BoardBean getArticle(String num) {
		BoardBean article = new BoardBean(); //selectOne �ϳ��� ���� ������ �������Ŵϱ� �ϳ�!!
		article = sqlSessionTemplate.selectOne(namespace+".GetArticle", num);
		return article;
	}

	public String bringPasswdArticle(String num) {
		String passwd = sqlSessionTemplate.selectOne(namespace+".BringPasswdArticle", num);
		return passwd;
	}

	public void updateArticle(BoardBean board) {
		sqlSessionTemplate.update(namespace+".UpdateArticle", board);
	}

	public void deleteArticle(String num) {
		sqlSessionTemplate.delete(namespace+".DeleteArticle",num);
	}

	public void insertReply(BoardBean board) {
		sqlSessionTemplate.insert(namespace+".InsertReply", board);
	}

	






}
