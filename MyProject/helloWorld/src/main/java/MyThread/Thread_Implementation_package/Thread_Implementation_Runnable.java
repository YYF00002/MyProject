package MyThread.Thread_Implementation_package;

/**
* @Description:    线程实现方式  实现Runnable接口
* @Author:         YYF
* @CreateDate:     2019/4/1 15:50
* @Version:        1.0
*/
public class Thread_Implementation_Runnable implements Runnable{

    public static void main(String[] args) {
        Thread_Implementation_Runnable item = new Thread_Implementation_Runnable();
        Thread thread = new Thread(item);
        thread.start();

    }


    @Override
    public void run() {
        System.out.println("子线程ID："+Thread.currentThread().getId());
    }
}
