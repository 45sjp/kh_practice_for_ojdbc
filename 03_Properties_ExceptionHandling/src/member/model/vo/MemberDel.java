package member.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class MemberDel extends Member {
	
	private Date delDate;
	
	public MemberDel() {
		super();
	}
	
	public MemberDel(Date delDate) {
		super();
		this.delDate = delDate;
	}

	public MemberDel(String id, String name, String gender, Date birthday, String email, String address,
			Timestamp regDate) {
		super(id, name, gender, birthday, email, address, regDate);
	}
	
	public MemberDel(String id, String name, String gender, Date birthday, String email, String address,
			Timestamp regDate, Date delDate) {
		super(id, name, gender, birthday, email, address, regDate);
		this.delDate = delDate;
	}

	public Date getDelDate() {
		return delDate;
	}

	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}

	@Override
	public String toString() {
		return "MemberDel [delDate=" + delDate + ", getId()=" + getId() + ", getName()=" + getName() + ", getGender()="
				+ getGender() + ", getBirthday()=" + getBirthday() + ", getEmail()=" + getEmail() + ", getAddress()="
				+ getAddress() + ", getRegDate()=" + getRegDate() + "]";
	}

}
