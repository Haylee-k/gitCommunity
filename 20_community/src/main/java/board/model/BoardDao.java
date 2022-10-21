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
		RowBounds rouBounds= new RowBounds(pageInfo.getOffset(),pageInfo.getLimit());//여기 순서가 서로 바뀌면 리스트가 엉킴...
		lists = sqlSessionTemplate.selectList(namespace+".GetAllBoard", map, rouBounds);
		return lists;
	}

	public void updateReadcount(String num) {
		sqlSessionTemplate.update(namespace+".UpdateReadcount", num);		
	}

	public BoardBean getArticle(String num) {
		BoardBean article = new BoardBean(); //selectOne 하나의 글의 정보를 가져갈거니까 하나!!
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

	//댓글
	public void commentInsert(CommentBean comment) {
		sqlSessionTemplate.insert(namespace+".CommentInsert", comment);
		
	}

	public List<CommentBean> commentList(int bdnum) {
		List<CommentBean> lists = sqlSessionTemplate.selectList(namespace+".CommentList" , bdnum);
		return lists;
	}


	public void commentUpdate(CommentBean comment) {
		System.out.println("commentUpdate2 :");
/*		System.out.println(comment.getBdnum());
		System.out.println(comment.getRenum());
		System.out.println(comment.getWriter());
		System.out.println(comment.getContent());
		System.out.println(comment.getReg_date());*/
		sqlSessionTemplate.update(namespace+".CommentUpdate", comment);		
	}

	public CommentBean commentGetOneInfo1(String renum) {

		CommentBean comment = sqlSessionTemplate.selectOne(namespace+".CommentGetOneInfo1", renum);
		
		System.out.println("일단 수정 renum dao:"+renum);
/*		System.out.println(comment.getBdnum());
		System.out.println(comment.getRenum());
		System.out.println(comment.getWriter());
		System.out.println(comment.getContent());
		System.out.println(comment.getReg_date());*/
		return comment;
	}

	public void commentDelete(String renum) {
		sqlSessionTemplate.delete(namespace+".CommentDelete", renum);		
	}



	






}
