package guard.other.demo.enumDemo;
/**
 * @Description:用法一：常量
 * 可以创建一个enum类，把它看做一个普通的类。除了它不能继承其他类了(java是单继承，它已经继承了Enum),
        可以添加其他方法，覆盖它本身的方法
   enum要求其成员都是唯一的，enum中不能删除添加元素。
 * @author guard
 * @date 2016年4月26日16:27:20
 */
public enum Color {
	RED, GREEN, BLANK, YELLOW;
}
