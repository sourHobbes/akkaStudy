/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

/**
 * Author: sdugar
 * Date:   11/5/14
 * Time:   1:46 PM
 */
public class MyRunnable implements Runnable {
   @Override
   public void run() {
      System.out.format("Running on MyRunnable thread %s\n",
            Thread.currentThread().getName());
   }
}
