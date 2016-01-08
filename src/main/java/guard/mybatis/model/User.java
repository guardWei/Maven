package guard.mybatis.model;

import java.util.Date;
import java.util.List;

public class User {
	private List<Course> uCourses;
	
    private Long id;

    private String name;

    private Integer age;

    private Date birthday;

	public List<Course> getuCourses() {
		return uCourses;
	}

	public void setuCourses(List<Course> uCourses) {
		this.uCourses = uCourses;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}