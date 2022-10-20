package member.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class MemberBean {

	private int num ; //넘버																																														
	
	@NotBlank(message="이름을 입력해주세요")
	private String name ; // --닉네임	
	
	@NotBlank(message="아이디를 입력해주세요")
	@Pattern(regexp = "[a-zA-Z0-9]{4,10}", message="아이디는 영문,숫자만 가능하며 4~10자리까지 가능합니다.")
	private String id ; //primary key -- 아이디
	
	@NotBlank(message="비밀번호를 입력해주세요")
	@Pattern(regexp = "[a-zA-Z0-9]{4,10}", message="비밀번호는 영문,숫자만 가능하며 4~10자리까지 가능합니다.")
	private String password ; //--비밀번호	
	
	@NotBlank(message="email을 입력해주세요")	
	private String email ; // --이메일	
	
	@Pattern(regexp = "^[0-9]+$", message="숫자로 입력해야합니다")
	private String hp; // --핸드폰1							
	
	private String zipcode1 ; // --주소1																																														
	private String zipcode2 ; // --주소2																																														
	private String zipcode3 ; // --주소3																																														
	private String zipcode4 ; // --주소4																																														
	private int mpoint ; // default 0 -- 포인트	
	
	
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
