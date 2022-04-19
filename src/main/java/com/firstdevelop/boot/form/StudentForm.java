package com.firstdevelop.boot.form;




public class StudentForm {
	@Override
	public String toString() {
		return "StudentForm [id=" + id + ", name=" + name + ", age=" + age + ", score=" + score + ", address=" + address
				+ ", stuClass=" + stuClass + "]";
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the stuClass
	 */
	public String getStuClass() {
		return stuClass;
	}
	/**
	 * @param stuClass the stuClass to set
	 */
	public void setStuClass(String stuClass) {
		this.stuClass = stuClass;
	}
	private int id;
	private String name;
	private int age;
	private double score; // ëoê∏ìxè¨êî
	private String address;
	private String stuClass;
	/**
	 * @param id
	 * @param name
	 * @param age
	 * @param score
	 * @param address
	 */
}