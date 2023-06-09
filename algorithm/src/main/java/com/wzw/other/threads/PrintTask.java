package com.wzw.other.threads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程顺序打印1到100，
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2022/2/28
 */
public class PrintTask implements Runnable{


    private static final ReentrantLock LOCK = new ReentrantLock();

    private static final Condition C = LOCK.newCondition();

    /**
     * 计数
     */
    private static int count = 0;

    private int max;

    /**
     * 当前线程号码
     */
    private int num;


    public PrintTask(int num, int max){
        this.num = num;
        this.max = max;
    }

    /**
     * 多线程顺序打印1到100
     */
    @Override
    public void run() {
        //思路，比如3个线程一起，对3取余，0只打印3的整数倍，1只打印对3取余为1的数，2只打印对3取余为2的数
        // 不打印时加锁等待，打印完了cout++，并释放所有的锁
        while(true){

            LOCK.lock();
            try{
                while(count%3 != num){
                    if(count > max){
                        break;
                    }
                    try {
                        C.await();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                if(count > max){
                    break;
                }
                System.out.println("thread-" + this.num + ":" + count);
                count++;
                C.signalAll();
            }finally {
                LOCK.unlock();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread( new PrintTask(0, 100));
        Thread t1 = new Thread( new PrintTask(1, 100));
        Thread t2 = new Thread( new PrintTask(2, 100));
        t0.start();
        t1.start();
        t2.start();
        while (true){
            Thread.sleep(100);
        }
    }
}
