package com.wzw.other.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wuzhiwei
 * @team M
 * @owner wuzhiwei
 * @Date 2022/8/26
 */
public class AQSTest {

    private static final Lock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();

    private static int num = 0;

    private static void plus(){
        lock.lock();
        try{
            int index = Integer.parseInt(Thread.currentThread().getName().substring(7));
            if( index % 2 == 0){
                condition.await();
                System.out.println("偶数线程，await，thread="+Thread.currentThread().getName());
            }else{
                condition.signal();
                num++;
                System.out.println("技术线程，signal，thread="+Thread.currentThread().getName()+",num="+num);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(()-> plus(), "thread-"+i).start();
        }

    }
}
