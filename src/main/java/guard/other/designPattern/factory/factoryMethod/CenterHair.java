package guard.other.designPattern.factory.factoryMethod;

import guard.other.designPattern.factory.factoryMethod.HairInterface;

/**
 * 发型接口实现类实现中分发型
 * @author guard
 * @date 2016年6月17日17:55:37
 */
public class CenterHair implements HairInterface {

	@Override
	public void drawHair() {
		System.out.println("----中分发型----");
	}

}
