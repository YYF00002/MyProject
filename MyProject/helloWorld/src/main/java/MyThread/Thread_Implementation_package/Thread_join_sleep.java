package MyThread.Thread_Implementation_package;
/**
* @Description:    join   sleep
* @Author:         YYF
* @CreateDate:     2019/4/17 10:35
* @Version:        1.0
*/
public class Thread_join_sleep {

    /**
     * join —— 让一个线程等待另一个线程完成才继续执行。
     * 如A线程线程执行体中调用B线程的join()方法，则A线程被阻塞，知道B线程执行完为止，A才能得以继续执行。
     * @param args
     */
//    public static void main(String[] args) {
//        MyRunnable myRunnable = new MyRunnable();
//        Thread thread = new Thread(myRunnable);
//
//        for (int i = 0; i < 40; i++) {
//            System.out.println(Thread.currentThread().getName() + " " + i);
//            if (i == 30) {
//                thread.start();
//                try {
//                    thread.join(); // main线程需要等待thread线程执行完后才能继续执行
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }


    /**
     * sleep —— 让当前的正在执行的线程暂停指定的时间，并进入阻塞状态
     *
     * @param args
     */
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);

        for (int i = 0; i < 40; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                thread.start();
                try {
                    Thread.sleep(2); // 使得thread必然能够马上得以执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 40; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
        }
    }
}
