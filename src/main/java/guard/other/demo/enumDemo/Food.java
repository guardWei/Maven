package guard.other.demo.enumDemo;

/**
 * @Description:用法六：使用接口组织枚举
 *  无法从enum继承子类，如果需要扩展enum中的元素，在一个接口的内部，创建实现该接口的枚举，以此将元素进行分组。达到将枚举元素进行分组。
 * @author guard
 * @date 2016年4月26日16:42:32
 */
public interface Food {
	enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, LATTE, CAPPUCCINO
    }
	enum Dessert implements Food {
        FRUIT, CAKE, GELATO
    }
}
