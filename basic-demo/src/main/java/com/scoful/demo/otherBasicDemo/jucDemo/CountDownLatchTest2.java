package com.scoful.demo.otherBasicDemo.jucDemo;

import java.util.concurrent.*;

/**
 * CountDownLatch模拟2
 * <p>
 * <p>
 * 建立一个线程池(具体配置根据具体业务，具体机器配置)，
 * 进行并发的执行我们的任务(生成用户信息，菜品信息等)，
 * 最后利用await方法阻塞等待结果成功返回。
 *
 * @author scoful
 * @date 2021/11/6 20:24
 */
public class CountDownLatchTest2 {
    private static final int CORE_POOL_SIZE = 4;
    private static final int MAX_POOL_SIZE = 8;
    private static final long KEEP_ALIVE_TIME = 5L;
    private final static int QUEUE_SIZE = 1600;

    protected final static ExecutorService THREAD_POOL = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
            KEEP_ALIVE_TIME, TimeUnit.SECONDS, new LinkedBlockingQueue<>(QUEUE_SIZE));

    public static void main(String[] args) throws InterruptedException {
        // 新建一个为5的计数器
        CountDownLatch countDownLatch = new CountDownLatch(5);
        OrderInfo orderInfo = new OrderInfo();
        THREAD_POOL.execute(() -> {
            System.out.println("当前任务Customer,线程名字为:" + Thread.currentThread()
                                                             .getName());
            orderInfo.setCustomerInfo(new CustomerInfo());
            countDownLatch.countDown();
        });
        THREAD_POOL.execute(() -> {
            System.out.println("当前任务Discount,线程名字为:" + Thread.currentThread()
                                                             .getName());
            orderInfo.setDiscountInfo(new DiscountInfo());
            countDownLatch.countDown();
        });
        THREAD_POOL.execute(() -> {
            System.out.println("当前任务Food,线程名字为:" + Thread.currentThread()
                                                         .getName());
            orderInfo.setFoodListInfo(new FoodListInfo());
            countDownLatch.countDown();
        });
        THREAD_POOL.execute(() -> {
            System.out.println("当前任务Tenant,线程名字为:" + Thread.currentThread()
                                                           .getName());
            orderInfo.setTenantInfo(new TenantInfo());
            countDownLatch.countDown();
        });
        THREAD_POOL.execute(() -> {
            System.out.println("当前任务OtherInfo,线程名字为:" + Thread.currentThread()
                                                              .getName());
            orderInfo.setOtherInfo(new OtherInfo());
            countDownLatch.countDown();
        });
        countDownLatch.await(1, TimeUnit.SECONDS);
        System.out.println("主线程：" + Thread.currentThread()
                                          .getName());
    }

}

class OrderInfo {
    private CustomerInfo customerInfo;
    private DiscountInfo discountInfo;
    private FoodListInfo foodListInfo;
    private OtherInfo otherInfo;
    private TenantInfo tenantInfo;

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public DiscountInfo getDiscountInfo() {
        return discountInfo;
    }

    public void setDiscountInfo(DiscountInfo discountInfo) {
        this.discountInfo = discountInfo;
    }

    public FoodListInfo getFoodListInfo() {
        return foodListInfo;
    }

    public void setFoodListInfo(FoodListInfo foodListInfo) {
        this.foodListInfo = foodListInfo;
    }

    public OtherInfo getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(OtherInfo otherInfo) {
        this.otherInfo = otherInfo;
    }

    public TenantInfo getTenantInfo() {
        return tenantInfo;
    }

    public void setTenantInfo(TenantInfo tenantInfo) {
        this.tenantInfo = tenantInfo;
    }
}

class CustomerInfo {

}

class DiscountInfo {

}

class FoodListInfo {

}

class TenantInfo {

}

class OtherInfo {

}