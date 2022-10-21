package board.model;

import java.sql.Timestamp;

public class CommentBean {
	
	private int renum; 
	private int bdnum; 
	private String writer; 
	private Timestamp reg_date; 
	private int ref; 
	private String content;
	
	
	public int getRenum() {
		return renum;
	}
	public void setRenum(int renum) {
		this.renum = renum;
	}
	public int getBdnum() {
		return bdnum;
	}
	public void setBdnum(int bdnum) {
		this.bdnum = bdnum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	} 
	
	
	
}
