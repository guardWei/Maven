package guard.other.demo.collectionDemo.SetDemo;

import java.util.Date;
import java.util.HashSet;

import guard.hibernate.entity.User;

/**
 * 测试Set接口
 * 
 * @author guard
 * @date 2016年6月8日17:27:54
 */
public class HashSetDemo {

	// 初始化HashSet
	private static HashSet<User> getHashSet() {
		HashSet<User> hashSet = new HashSet<User>();

		User u1 = new User();
		u1.setId(12L);
		u1.setName("张彡");
		u1.setAge(24);
		u1.setBirthday(new Date());
		hashSet.add(u1);

		User u2 = new User();
		u2.setId(10L);
		u2.setName("李四");
		u2.setAge(10);
		u2.setBirthday(new Date());
		hashSet.add(u2);

		User u3 = new User();
		u3.setId(15L);
		u3.setName("王五");
		u3.setAge(22);
		u3.setBirthday(new Date());
		hashSet.add(u3);

		return hashSet;
	}

	
	/**
	 * 虽然Set同List都实现了Collection接口，但是他们的实现方式却大不一样。List基本上都是以Array为基础。但是Set则是在
	 * HashMap的基础上来实现的，这个就是Set和List的根本区别。HashSet的存储方式是把HashMap中的Key作为Set的对应存储项.
	 */
	public static void main(String[] args) {
		HashSet<User> userHashSet = getHashSet();
        /*HashSet不保证集合中元素的顺序,加入的字段顺序跟遍历出的不一样*/
	}

}
