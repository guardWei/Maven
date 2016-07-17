package guard.other.designPattern.factory.factoryMethod;

import guard.other.designPattern.factory.factoryMethod.HairInterface;

/**
 * 发型接口实现类实现左偏分发型
 * @author guard
 * @date 2016年6月17日17:52:28
 */
public class LeftHair implements HairInterface {

	@Override
	public void drawHair() {
		System.out.println("----左偏分发型----");
	}

}
