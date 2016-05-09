package guard.other.demo.enumDemo.implInterface;

/**
 * @Description:用法五：实现接口 所有的枚举都继承自java.lang.Enum类。由于Java 不支持多继承，所以枚举对象不能再继承其他类。
 * @author guard
 * @date 2016年4月26日16:36:13
 */
public enum Color5 implements BehaviourInterface {
	RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);

	private Color5(String name, int index) {
		this.name = name;
		this.index = index;
	}

	private String name;
	private int index;

	@Override
	public void print() {
		System.out.println(this.index + "_" + this.name);
	}

	@Override
	public String getInfo() {
		return this.name;
	}

	public static void main(String[] args) {
		Color5.YELLO.print();
	}
}
/*
 * 4_黄色
*/
