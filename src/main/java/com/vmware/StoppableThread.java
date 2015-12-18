/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Author: sdugar
 * Date:   11/5/14
 * Time:   2:45 PM
 */
public class StoppableThread extends Thread {
   private boolean keepGoing;
   private AtomicBoolean shouldBeRunning = new AtomicBoolean(true);

   public void run() {
      while(!StoppableThread.interrupted()) {
         try {
            Thread.sleep(5000);
            //System.out.println("Whoa");
         } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Cause: " + e.getCause());
            System.out.println("Message: " + e.getCause());
            this.interrupt();
         }
      }
   }

   public AtomicBoolean getShouldBeRunning() {
      return shouldBeRunning;
   }

   public void setShouldBeRunning(AtomicBoolean shouldBeRunning) {
      this.shouldBeRunning = shouldBeRunning;
   }

   public static void main (String [] args) throws Throwable {
      StoppableThread t1 = new StoppableThread();
      LocalTime time1 = LocalTime.now();
      t1.start();
      Thread.sleep(5000);
      t1.finalize();

      System.out.println(time1);
      System.out.println(LocalTime.now());
   }
}
