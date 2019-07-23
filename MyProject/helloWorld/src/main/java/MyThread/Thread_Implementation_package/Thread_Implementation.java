package MyThread.Thread_Implementation_package;

/**
* @Description:    线程实现方式  继承Thread
* @Author:         YYF
* @CreateDate:     2019/4/1 15:50
* @Version:        1.0
*/
public class Thread_Implementation extends Thread{

    /**
     * 通过start()方法去启动线程。
     * 注意，不是调用run()方法启动线程，run方法中只是定义需要执行的任务
     * 如果调用run方法，即相当于在主线程中执行run方法，跟普通的方法调用没有任何区别，此时并不会创建一个新的线程来执行定义的任务。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("主线程ID:"+Thread.currentThread().getId());
        Thread_Implementation thread_implementation = new Thread_Implementation("thread1");
        thread_implementation.start();
        Thread_Implementation thread_implementation2 = new Thread_Implementation("thread2");
        thread_implementation2.run();

    }

    /**
     * 继承Thread类
     * 在java.lang包中定义, 继承Thread类必须重写run()方法
     */

    private  String name ;
    private static int num = 0;

    public Thread_Implementation(String name){
        this.name=name;
    }

    @Override
    public void run() {
        System.out.println("子线程："+name+"启动，线程ID："+Thread.currentThread().getId());
    }




}
