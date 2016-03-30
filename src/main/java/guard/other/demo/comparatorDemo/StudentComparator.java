package guard.other.demo.comparatorDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 测试比较器的类
 * @author guard
 * @date 2016年3月30日17:31:49
 */
public class StudentComparator {

	public static void main(String[] args) {
		Student s1 = new Student(20L, "张三", 22, new Date(1998, 8, 8));

		Student s2 = new Student(10L, "李四", 32, new Date());

		Student s3 = new Student(40L, "王五", 12, new Date(1996, 8, 8));

		Student s4 = new Student(8L, "小六", 22, new Date());

		List<Student> studentList = new ArrayList<Student>();

		studentList.add(s1);
		studentList.add(s2);
		studentList.add(s3);
		studentList.add(s4);

		System.out.println("原数组序列中的元素:");
		printStudentInfo(studentList);

		System.out.println("默认排序后数组序列中的元素:");
		Collections.sort(studentList); // 使用默认的排序比较器
		printStudentInfo(studentList);

		System.out.println("使用年龄比较器排序后数组序列中的元素:");
		Collections.sort(studentList, new AgeComparator());// 使用年龄比较器
		printStudentInfo(studentList);

		System.out.println("使用生日比较器排序后数组序列中的元素:");
		Collections.sort(studentList, new BirthdayComparator());// 使用生日比较器
		printStudentInfo(studentList);
	}

	// 自定义方法：分行打印输出list中的元素
	public static void printStudentInfo(List<Student> studentList) {
		Iterator<Student> iterator = studentList.iterator();//// 得到迭代器，用于遍历list中的所有元素
		while (iterator.hasNext()) {// 如果迭代器中有元素，则返回true
			System.out.println("\t" + iterator.next());// 显示该元素
		}
	}

	// 自定义比较器：按年龄排序
	static class AgeComparator implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {// 实现接口中的方法
			Student s1 = (Student) o1;
			Student s2 = (Student) o2;
			return s1.getAge().compareTo(s2.getAge());
		}

	}

	// 自定义比较器：按生日排序
	static class BirthdayComparator implements Comparator<Object> {

		@Override
		public int compare(Object o1, Object o2) {// 实现接口中的方法
			Student s1 = (Student) o1;
			Student s2 = (Student) o2;
			return s1.getBirthday().compareTo(s2.getBirthday());
		}

	}
}

/*输出结果：
原数组序列中的元素:
	20	张三	22岁	3898年09月08日
	10	李四	32岁	2016年03月30日
	40	王五	12岁	3896年09月08日
	8	小六	22岁	2016年03月30日
默认排序后数组序列中的元素:
	8	小六	22岁	2016年03月30日
	10	李四	32岁	2016年03月30日
	20	张三	22岁	3898年09月08日
	40	王五	12岁	3896年09月08日
使用年龄比较器排序后数组序列中的元素:
	40	王五	12岁	3896年09月08日
	8	小六	22岁	2016年03月30日
	20	张三	22岁	3898年09月08日
	10	李四	32岁	2016年03月30日
使用生日比较器排序后数组序列中的元素:
	8	小六	22岁	2016年03月30日
	10	李四	32岁	2016年03月30日
	40	王五	12岁	3896年09月08日
	20	张三	22岁	3898年09月08日

*/
