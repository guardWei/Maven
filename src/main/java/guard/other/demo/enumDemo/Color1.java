package guard.other.demo.enumDemo;

enum Signal {
	GREEN, YELLOW, RED;
}

/**
 * @Description:用法二：switch
 * @author guard
 * @date 2016年4月26日16:30:03
 */
public class Color1 {
	public static void main(String[] args) {
		Signal color = Signal.RED;
		switch (color) {
		case GREEN:
			color = Signal.RED;
			break;
		case YELLOW:
			color = Signal.GREEN;
			break;
		case RED:
			color = Signal.YELLOW;
			break;
		default:
			System.out.println("default");
			break;
		}
		System.out.println(color);
	}
}
/*YELLOW*/
