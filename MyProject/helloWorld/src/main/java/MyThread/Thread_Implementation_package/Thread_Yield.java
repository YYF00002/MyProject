package MyThread.Thread_Implementation_package;

/**
 * @Description: 线程让步
 * @Author: YYF
 * @CreateDate: 2019/4/17 10:35
 * @Version: 1.0
 */
public class Thread_Yield {

    /**
     * yield()方法还与线程优先级有关，当某个线程调用yiled()方法从运行状态转换到就绪状态后，CPU从就绪状态线程队列中只会选择与该线程优先级相同或优先级更高的线程去执行。
     * @param args
     */

    public static void main(String[] args) {
        Thread myThread1 = new MyThread1();
        Thread myThread2 = new MyThread2();
        myThread1.setPriority(Thread.MAX_PRIORITY);
        myThread2.setPriority(Thread.MIN_PRIORITY);
        for (int i = 0; i < 50; i++) {
            System.out.println("main thread i = " + i);
            if (i == 20) {
                myThread1.start();
                myThread2.start();
                Thread.yield();
            }
        }

    }


    static class MyThread1 extends Thread {
        public void run() {
            for (int i = 0; i < 20; i++) {
                System.out.println("myThread 1 --  i = " + i);
            }
        }
    }

    static class MyThread2 extends Thread {
        public void run() {
            for (int i = 0; i < 20; i++) {
                System.out.println("myThread 2 --  i = " + i);
            }
        }
    }
}
