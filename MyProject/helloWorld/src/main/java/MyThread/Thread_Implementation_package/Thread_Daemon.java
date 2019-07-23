package MyThread.Thread_Implementation_package;
/**
* @Description:    Daemon Thread  后台线程
* @Author:         YYF
* @CreateDate:     2019/4/17 10:35
* @Version:        1.0
*/
public class Thread_Daemon {

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
                thread.setDaemon(true);
                thread.start();
            }
        }
    }


    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 40; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
