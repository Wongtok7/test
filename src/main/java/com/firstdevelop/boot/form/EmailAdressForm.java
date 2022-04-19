package com.firstdevelop.boot.form;

public class EmailAdressForm {
	/**
	 * メールアドレス
	 */
	private String emailAdress;
	/**
	 * 会社名
	 */
	
	private String comName;
	/**
	 * 連絡人
	 */
	private String person;
	/**
	 * @return the emailAdress
	 */
	public String getEmailAdress() {
		return emailAdress;
	}
	/**
	 * @param emailAdress the emailAdress to set
	 */
	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	/**
	 * @return the comName
	 */
	public String getComName() {
		return comName;
	}

	/**
	 * @param comName the comName to set
	 */
	public void setComName(String comName) {
		this.comName = comName;
	}

	/**
	 * @return the person
	 */
	public String getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(String person) {
		this.person = person;
	}
}
