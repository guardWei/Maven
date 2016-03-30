package guard.other.demo.comparatorDemo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 集合类的Collections.sort()使用示例的学生实体类
 * 
 * @author guard
 * @date 2016年3月30日16:19:11
 */
public class Student implements Comparable<Object> {
	private Long id;
	private String name;
	private Integer age;
	private Date birthday;

	public Student() {
		this(0L, "默认名字", 0, new Date()); 
	}

	public Student(Long id, String name, Integer age, Date birthday) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.birthday = birthday;
	}

	
	@Override//重写继承自父类Object的方法，满足Student类信息描述的要求 
	public String toString() {
		String showStr = id + "\t" + name + "\t" +age+"岁"; // 定义显示类信息的字符串
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy年MM月dd日");
		showStr += "\t" + formatDate.format(birthday); // 格式化时间
		return showStr; // 返回类信息字符串
	}

	@Override
	public int compareTo(Object o) {//Comparable接口中的方法  
		Student s = (Student)o;
		return (int) (this.id-s.id);//按student的id比较大小，用于默认排序  
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
		this.name = name;
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
