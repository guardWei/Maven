package guard.other.demo.enumDemo;

/**
 * @Description:用法三：向枚举中添加新方法(构造方法和自定义getName()方法)
 * @author guard
 * @date 2016年4月26日16:32:53
 */
public enum Color2 {
	RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);

	private String name;
	private int index;
 
	//构造器只是在构造枚举值的时候被调用,如RED("红色", 1)
	private Color2(String name, int index) {
		this.name = name;
		this.index = index;
	}

	private String getName(int index) {
		for (Color2 c1 : Color2.values()) {

			System.out.println(c1.getName());

			if (c1.getIndex() == index) {
				return c1.getName();
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public static void main(String[] args) {
		Color2 color1 = Color2.YELLO;
		System.out.println(color1.getName(4));

		System.out.println("--------------");

		for (Color2 c1 : Color2.values()) {
			System.out.println(c1.getName());
		}

		System.out.println("--------------");

		System.out.println(Color2.valueOf("RED"));
	}

}
/*红色
绿色
白色
黄色
黄色
--------------
红色
绿色
白色
黄色
--------------
RED*/