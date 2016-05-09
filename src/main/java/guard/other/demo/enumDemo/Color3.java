package guard.other.demo.enumDemo;

/**
 * @Description:用法四：覆盖枚举的方法 覆盖toString()方法
 *   它不能继承其他类了,java是单继承，它已经继承了Enum
 * @author guard
 * @date 2016年4月26日16:34:39
 */
public class Color3 {
	public enum Color {
		RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);

		private Color(String name, int index) {
			this.name = name;
			this.index = index;
		}

		private String name;
		private int index;

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

		@Override
		public String toString() {
			return this.index + "_" + this.name;
		}
	}

	public static void main(String[] args) {
		System.out.println(Color.GREEN.toString());
	}
}
/*2_绿色*/