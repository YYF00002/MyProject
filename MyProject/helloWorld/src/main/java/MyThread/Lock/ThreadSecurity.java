package MyThread.Lock;


/**
 * @Description: 线程安全
 * @Author: YYF
 * @CreateDate: 2019/4/22 18:14
 * @Version: 1.0
 */
public class ThreadSecurity {

    public static void main(String[] args) {
        Account account = new Account("123456", 1000);
        DrawMoneyRunnable drawMoneyRunnable = new DrawMoneyRunnable(account, 700);
        Thread myThread1 = new Thread(drawMoneyRunnable);
        Thread myThread2 = new Thread(drawMoneyRunnable);
        myThread1.start();
        myThread2.start();


    }


  static   class DrawMoneyRunnable implements Runnable {

        private Account account;
        //提款金额
        private double drawAmount;

        public DrawMoneyRunnable(Account account, double drawAmount) {
            this.account = account;

            this.drawAmount = drawAmount;
        }


        @Override
        public synchronized  void run() {
            if (account.getBalance() >= drawAmount) {  //1
                System.out.println("取钱成功， 取出钱数为：" + drawAmount);
                double balance = account.getBalance() - drawAmount;
                account.setBalance(balance);
                System.out.println("余额为：" + balance);
            }

        }
    }


  static   class Account {
        public Account(String accountNO, double balance) {
            this.accountNO = accountNO;
            this.balance = balance;
        }

        //账号
        private String accountNO;
        //余额
        private double balance;


        public Account(String accountNO) {
            this.accountNO = accountNO;
        }

        public String getAccountNO() {
            return accountNO;
        }

        public void setAccountNO(String accountNO) {
            this.accountNO = accountNO;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }


    }

}
