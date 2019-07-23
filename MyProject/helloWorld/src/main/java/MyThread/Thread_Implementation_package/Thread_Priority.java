package MyThread.Thread_Implementation_package;

/**
 * @Description: 线程优先级
 * @Author: YYF
 * @CreateDate: 2019/4/17 10:35
 * @Version: 1.0
 */
public class Thread_Priority {

    /**
     * main线程默认具有普通优先级。
     *
     * MAX_PRIORITY:10
     *
     * MIN_PRIORITY:1
     *
     * NORM_PRIORITY:5
     *
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("主线程的优先等级："+new Thread().getPriority());
        MyThread thread = new MyThread();
        for (int i = 0; i < 40; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                thread.setPriority(Thread.MAX_PRIORITY);
                thread.start();
            }
        }
    }


    static class MyThread extends Thread {
        public void run() {
            System.out.println("子线程的优先等级："+new Thread().getPriority());
            for (int i = 0; i < 50; i++) {
                System.out.println("i = " + i);
            }
        }

    }
}
