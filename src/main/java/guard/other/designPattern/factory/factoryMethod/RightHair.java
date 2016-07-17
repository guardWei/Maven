package guard.other.designPattern.factory.factoryMethod;

import guard.other.designPattern.factory.factoryMethod.HairInterface;

/**
 * 发型接口实现类实现右偏分发型
 * @author guard
 * @date 2016年6月17日17:54:03
 */
public class RightHair implements HairInterface {

	@Override
	public void drawHair() {
        System.out.println("----右偏分发型----");
	}

}
