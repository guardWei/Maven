package guard.other.demo.collectionDemo.ListDemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ListIterator;

import guard.hibernate.entity.User;;

/**
 * 测试List接口
 * @author guard
 * @date 2016年6月8日17:08:25
 */
public class ArrayListDemo {

	// 初始化ArrayList
	private static ArrayList<User> getArrayList() {
		ArrayList<User> arrayList = new ArrayList<User>();

		User u1 = new User();
		u1.setId(12L);
		u1.setName("张彡");
		u1.setAge(24);
		u1.setBirthday(new Date());
		arrayList.add(u1);

		User u2 = new User();
		u2.setId(10L);
		u2.setName("李四");
		u2.setAge(10);
		u2.setBirthday(new Date());
		arrayList.add(u2);

		User u3 = new User();
		u3.setId(15L);
		u3.setName("王五");
		u3.setAge(22);
		u3.setBirthday(new Date());
		arrayList.add(u3);

		return arrayList;
	}

	public static void main(String[] args) {
		ArrayList<User> userArrayList = getArrayList();

		/*--------------------------主要用法1：转换成Array---------------------------*/
		Object[] objectArray = userArrayList.toArray();// 无参的toArray()方法返回是Object[]不能强转
		System.out.println(objectArray);// [Ljava.lang.Object;@16c9867

		/*
		 * 指定数组大小，不需要强转，下面为简写
		 * User[] userArray = userArrayList.toArray(new User[userArrayList.size()]);
		 */
		User[] userArray = new User[userArrayList.size()];
		userArrayList.toArray(userArray);
		System.out.println(userArray);

		/*--------------------------主要用法2：迭代---------------------------*/
		
		/*
		 * Iterator和ListIterator主要区别有：
		 * 一、ListIterator有add()方法，可以向List中添加对象，而Iterator不能。
		 * 二、ListIterator和Iterator都有hasNext()和next()方法，可以实现顺序向后遍历。
		 * 但是ListIterator有hasPrevious()和previous()方法，可以实现逆向（顺序向前）遍历。
		 * Iterator就不可以。
		 * 三、ListIterator可以定位当前的索引位置，nextIndex()和previousIndex()可以实现。Iterator
		 * 没有此功能。
		 * 四、都可实现删除对象，但是ListIterator可以实现对象的修改，set()方法可以实现。Iterator仅能遍历，不能修改。
		 * 因为ListIterator的这些功能，可以实现对LinkedList等List数据结构的操作。
		 * 
		 * 附：
		 * Iterator模式是用于遍历集合类的标准访问方法。它可以把访问逻辑从不同类型的集合类中抽象出来，从而避免向客户端暴露集合的内部结构。
		 * 例如，如果没有使用Iterator，遍历一个数组的方法是使用索引： for(int i=0; i<array.size(); i++) {
		 * ... get(i) ... }
		 * 客户端都必须事先知道集合的内部结构，访问代码和集合本身是紧耦合，无法将访问逻辑从集合类和客户端代码中分离出来，每一种集合对应一种遍历方法，
		 * 客户端代码无法复用。 更恐怖的是，如果以后需要把ArrayList更换为LinkedList，则原来的客户端代码必须全部重写。
		 * 为解决以上问题，Iterator模式总是用同一种逻辑来遍历集合： for(Iterator it = c.iterater();
		 * it.hasNext(); ) { ... }
		 * 奥秘在于客户端自身不维护遍历集合的"指针"，所有的内部状态（如当前元素位置，是否有下一个元素）都由Iterator来维护，
		 * 而这个Iterator由集合类通过工厂方法生成，因此，它知道如何遍历整个集合。
		 * 客户端从不直接和集合类打交道，它总是控制Iterator，向它发送"向前"，"向后"，"取当前元素"的命令，就可以间接遍历整个集合。
		 *
		 */
		
		Iterator<User> userIterator = userArrayList.iterator(); // 获得一个迭代器
		while (userIterator.hasNext()) {
			User user = userIterator.next(); // 得到下一个元素
			System.out.println(user);
		}
		
		for(Iterator<User> iterator = userArrayList.iterator();iterator.hasNext();){
			User user = userIterator.next(); // 得到下一个元素
			System.out.println(user);
		}
		
		/*-------ListIterator-------*/
		ListIterator<User> userListIterator = userArrayList.listIterator();
		while(userListIterator.hasPrevious()){
			User user = userListIterator.previous();// 得到前一个元素
			System.out.println(user);
		}
		
		
		
		/*----------注意---------- */
		// 初始化时指定大小只是指定了底层数组elementData大小，不指定默认为10。size还是0
		ArrayList<String> u = new ArrayList<String>(100);
		System.out.println(u.size());// 0
		// 调用ensureCapacity()方法相当于在添加前直接把底层数组elementData重新copy一个长度指定的大小的新数组，可以提高效率，size依然没变化
		u.ensureCapacity(200);
		System.out.println(u.size());// 0
		/* 只有在添加元素 的时候size才会增加 */
		
		
		/**
		 * 使用for循环与使用迭代器iterator的对比 
		 * 效率上的各有优缺点:采用ArrayList对随机访问比较快，而for循环中的get()方法，采用的即是随机访问的方法，因此在ArrayList里，for循环较快
		 *    采用LinkedList则是顺序访问比较快，iterator中的next()方法，采用的即是顺序访问的方法，因此在LinkedList里，
		 * 使用iterator较快 从数据结构角度分析,for循环适合访问顺序结构,可以根据下标快速获取指定元素.而Iterator
		 * 适合访问链式结构,因为迭代器是通过next()和Pre()来定位的.可以访问没有顺序的集合. 
		 *    而使用 Iterator的好处在于可以使用相同方式去遍历集合中元素，而不用考虑集合类的内部实现（只要它实现了 java.lang.Iterable
		 * 接口），如果使用 Iterator 来遍历集合中元素，一旦不再使用 List 转而使用 Set
		 * 来组织数据，那遍历元素的代码不用做任何修改，如果使用 for
		 * 来遍历，那所有遍历此集合的算法都得做相应调整,因为List有序,Set无序,结构不同,他们的访问算法也不一样.
		 */
	}

}
