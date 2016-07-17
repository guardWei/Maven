package guard.other.designPattern.factory.factoryMethod;

import guard.other.designPattern.factory.factoryMethod.HairInterface;

/**
 * 客户端测试类
 * @author guard
 * @date 2016年6月17日18:02:01
 */
public class ClientTest {

	public static void main(String[] args) {
		
		HairFactory hairFactory = new HairFactory();
		
		
        HairInterface leftHair = hairFactory.getHairByClassName("guard.other.designPattern.factory.factoryMethod.LeftHair");
        leftHair.drawHair();//----左偏分发型----
        
        HairInterface rightHair = hairFactory.getHairByClassName("guard.other.designPattern.factory.factoryMethod.RightHair");
        rightHair.drawHair();//----右偏分发型----
	}

}
