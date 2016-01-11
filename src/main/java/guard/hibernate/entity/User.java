package guard.hibernate.entity;

import java.util.Date;

/**
 * 学生用户实体类
 * @author guard
 * @version 2016年1月8日15:45:37
 */

public class User implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Integer age;
	private Date birthday;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String name, Integer age, Date birthday) {
		this.name = name;
		this.age = age;
		this.birthday = birthday;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}