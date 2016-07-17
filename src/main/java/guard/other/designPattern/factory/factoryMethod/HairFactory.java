package guard.other.designPattern.factory.factoryMethod;

import guard.other.designPattern.factory.factoryMethod.HairInterface;

/**
 * 发型生产工厂
 * @author guard
 * @date 2016年6月17日17:57:46
 */
public class HairFactory {
    
	/**
	 * 根据类型返回发型对象
	 * @author guard
	 * @date 2016年6月17日17:58:48
	 */
	public HairInterface getHairByClassName(String className){
		try {
			HairInterface hairInterface = (HairInterface) Class.forName(className).newInstance();
			
			return hairInterface;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
