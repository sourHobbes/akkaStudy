/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import java.time.LocalDateTime;

/**
 * Author: sdugar
 * Date:   11/5/14
 * Time:   1:25 PM
 */
public class UsingThreads {
   public static void main (String [] args) throws InterruptedException {
      /*
      System.out.println(Thread.currentThread().getName());
      MyThread myThread = new MyThread();
      MyThread myThread2 = new MyThread();
      MyThread myThread3 = new MyThread();
      myThread.start();
      myThread2.start();
      myThread3.start();
      myThread.join(); myThread2.join(); myThread3.join();
      //myThread.start();
      System.out.println(Thread.currentThread().getName());
      MyRunnable myRunnable = new MyRunnable();
      Thread thread1 = new Thread(myRunnable);
      Thread thread2 = new Thread(myRunnable);

      thread1.start();
      thread2.start();

      thread1.join(); thread2.join();
      System.out.println("----");
      Thread alexeyThread = new AlexeyThread(myRunnable);
      alexeyThread.start();
      */
      /*
      new Thread(() -> System.out.printf("Running Arun's Thread ." +
            "... Yawnnnnnn!")).start(); //Perilous...no join .. might not complete...
      new Thread(() -> {}); // Java 8 FTW!
      */

      Thread ForeverThread = new Thread( () -> {
         while (true) {
            System.out.println("All work and no play...." +
                  Thread.currentThread().getName() + LocalDateTime.now());
         }
      });


      ForeverThread.setDaemon(true);
      ForeverThread.start();
      System.out.println("Back in " +
            "main=====================================================================");
   }
}
