package guard.hibernate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 选课表
 * @author guard
 * @version 2016年1月11日14:56:15
 */
@Entity
@Table(name = "user_course")
public class UserCourse implements java.io.Serializable {

	// Fields
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String userId;
	private String courseId;

	// Constructors

	/** default constructor */
	public UserCourse() {
	}

	/** minimal constructor */
	public UserCourse(String id) {
		this.id = id;
	}

	/** full constructor */
	public UserCourse(String id, String userId, String courseId) {
		this.id = id;
		this.userId = userId;
		this.courseId = courseId;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 20)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "user_id", length = 20)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "course_id", length = 20)
	public String getCourseId() {
		return this.courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

}