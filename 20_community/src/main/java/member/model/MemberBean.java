package member.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class MemberBean {

	private int num ; //�ѹ�																																														
	
	@NotBlank(message="�̸��� �Է����ּ���")
	private String name ; // --�г���	
	
	@NotBlank(message="���̵� �Է����ּ���")
	@Pattern(regexp = "[a-zA-Z0-9]{4,10}", message="���̵�� ����,���ڸ� �����ϸ� 4~10�ڸ����� �����մϴ�.")
	private String id ; //primary key -- ���̵�
	
	@NotBlank(message="��й�ȣ�� �Է����ּ���")
	@Pattern(regexp = "[a-zA-Z0-9]{4,10}", message="��й�ȣ�� ����,���ڸ� �����ϸ� 4~10�ڸ����� �����մϴ�.")
	private String password ; //--��й�ȣ	
	
	@NotBlank(message="email�� �Է����ּ���")	
	private String email ; // --�̸���	
	
	@Pattern(regexp = "^[0-9]+$", message="���ڷ� �Է��ؾ��մϴ�")
	private String hp; // --�ڵ���1							
	
	private String zipcode1 ; // --�ּ�1																																														
	private String zipcode2 ; // --�ּ�2																																														
	private String zipcode3 ; // --�ּ�3																																														
	private String zipcode4 ; // --�ּ�4																																														
	private int mpoint ; // default 0 -- ����Ʈ	
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getZipcode1() {
		return zipcode1;
	}
	public void setZipcode1(String zipcode1) {
		this.zipcode1 = zipcode1;
	}
	public String getZipcode2() {
		return zipcode2;
	}
	public void setZipcode2(String zipcode2) {
		this.zipcode2 = zipcode2;
	}
	public String getZipcode3() {
		return zipcode3;
	}
	public void setZipcode3(String zipcode3) {
		this.zipcode3 = zipcode3;
	}
	public String getZipcode4() {
		return zipcode4;
	}
	public void setZipcode4(String zipcode4) {
		this.zipcode4 = zipcode4;
	}
	public int getMpoint() {
		return mpoint;
	}
	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}
	
	
	
}
