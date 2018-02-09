package cn.itcast.invoice.auth.emp.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.itcast.invoice.auth.dep.vo.DepModel;
import cn.itcast.invoice.auth.role.vo.RoleModel;
import cn.itcast.invoice.util.format.FormatUtil;

public class EmpModel implements Serializable{
	public static final Integer EMP_GENDER_OF_MAN = 1;
	public static final Integer EMP_GENDER_OF_WOMAN = 0;
	
	public static final String EMP_GENDER_OF_MAN_VIEW = "Ã§â€�Â·";
	public static final String EMP_GENDER_OF_WOMAN_VIEW = "Ã¥Â¥Â³";
	
	public static Map<Integer, String> genderMap = new HashMap<Integer, String>();
	
	static{
		genderMap.put(EMP_GENDER_OF_MAN, EMP_GENDER_OF_MAN_VIEW);
		genderMap.put(EMP_GENDER_OF_WOMAN, EMP_GENDER_OF_WOMAN_VIEW);
	}
	
	private Long uuid;
	//Ã§â„¢Â»Ã©â„¢â€ Ã§â€�Â¨Ã¦Ë†Â·Ã¥ï¿½ï¿½
	private String userName;
	//Ã¥Â¯â€ Ã§Â ï¿½
	private String pwd;
	//Ã§Å“Å¸Ã¥Â®Å¾Ã¥Â§â€œÃ¥ï¿½ï¿½
	private String name;
	
	private String email;
	private String tele;
	private String address;
	private String lastLoginIp;
	
	private Long birthday;
	private Long lastLoginTime;

	private Integer gender;
	private Integer loginTimes;
	
	//Ã¨Â§â€ Ã¥â€ºÂ¾Ã¥â‚¬Â¼
	private String birthdayView;
	private String lastLoginTimeView;
	private String genderView;
	
	//Ã¥â€¦Â³Ã§Â³Â»
	private DepModel dm;
	private Set<RoleModel> roles;
	
	//Ã¦ï¿½Æ’Ã©â„¢ï¿½Ã¦Â Â¡Ã©ÂªÅ’Ã¨Â¾â€¦Ã¥Å Â©Ã¥Â­â€”Ã¦Â®Âµ
	private String resValue;
	
	public String getResValue() {
		return resValue;
	}

	private void setResValue(String resValue) {
		this.resValue = resValue;
	}

	public Set<RoleModel> getRoles() {
		return roles;
	}

	private void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}

	public Long getUuid() {
		return uuid;
	}

	/**
	 * 
	 * @param check insert 0 to get birthdayView, 1 to get genderView, 2 to get lastLoginTimeView
	 * @return a string with the desidered value
	 */
	public String getView(int check) {
		String toReturn = null;
		if (check == 0) {
			toReturn= birthdayView;
		}
		if (check == 1) {
			toReturn= genderView;
		}
		if (check == 2) {
			toReturn =lastLoginTimeView;
		}
			
		return toReturn;
	}
	

	private void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getUserName() {
		return userName;
	}

	private void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	private void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * 
	 * @param check insert 0 to get name, 1 to get email, 2 to get tele, 3 to get address
	 * @return a string with the desidered value
	 */
	public String getPersonalInformation(int check) {
		String toReturn = null;
		if (check == 0) {
			toReturn= name;
		} else
		if (check == 1) {
			toReturn= email;
		} else
		if (check == 2) {
			toReturn =tele;
		}else
		if (check == 3) {
			toReturn =address;
		} else {
			toReturn = "The Value is not correct, please read documentation!";
		}
			
		return toReturn;
	}

	private void setName(String name) {
		this.name = name;
	}


	private void setEmail(String email) {
		this.email = email;
	}

	private void setTele(String tele) {
		this.tele = tele;
	}

	private void setAddress(String address) {
		this.address = address;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	private void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Long getBirthday() {
		return birthday;
	}

	private void setBirthday(Long birthday) {
		this.birthday = birthday;
		this.birthdayView = FormatUtil.formatDate(birthday);
	}

	public Long getLastLoginTime() {
		return lastLoginTime;
	}

	private void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
		this.lastLoginTimeView = FormatUtil.formatDate(lastLoginTime);
	}

	public Integer getGender() {
		return gender;
	}

	private void setGender(Integer gender) {
		this.gender = gender;
		this.genderView = genderMap.get(gender);
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	private void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public DepModel getDm() {
		return dm;
	}

	private void setDm(DepModel dm) {
		this.dm = dm;
	}
	
}