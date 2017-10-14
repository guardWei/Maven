package guard.other.demo.DistributedLock.BaseOnZookeeper;


/**
 * 订单编号生成器
 *
 * @author guard
 * @date 2017年09月10日18:28:45
 */
public class OrderCodeGenerator {
    private static int count = 0;

    public static String getOrderCode() {
        return "orderId" + ++count;
    }
}
