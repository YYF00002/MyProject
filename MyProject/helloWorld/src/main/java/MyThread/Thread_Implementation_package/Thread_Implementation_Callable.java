package MyThread.Thread_Implementation_package;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
* @Description:    线程实现方式  实现Runnable接口
* @Author:         YYF
* @CreateDate:     2019/4/1 15:50
* @Version:        1.0
*/
public class Thread_Implementation_Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建MyCallable对象
        MyCallable myCallable = new MyCallable();
        //使用FutureTask来包装MyCallable对象
        FutureTask<Integer> ft = new FutureTask<>(myCallable);

        for (int i = 0; i <100 ; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if(i==30){
                //FutureTask对象作为Thread对象的target创建新的线程
                Thread thread = new Thread(ft);
                //线程进入到就绪状态
                thread.start();
            }

        }
        System.out.println("主线程for循环执行完毕..");
        //取得新创建的新线程中的call()方法返回的结果
        //ft.get()方法获取子线程call()方法的返回值时，当子线程此方法还未执行完毕，ft.get()方法会一直阻塞，直到call()方法执行完毕才能取到返回值。
        Integer sum = ft.get();
        System.out.println("sum = " + sum);

    }

static class  MyCallable implements Callable<Integer>{
      private   Integer i=0;


    // 与run()方法不同的是，call()方法具有返回值
    @Override
    public Integer call() throws Exception {
        int sum=0;
        for (; i < 100; i++) {
           System.out.println(Thread.currentThread().getName() + " " + i);
            sum += i;
        }
        return sum;
    }
}

}
