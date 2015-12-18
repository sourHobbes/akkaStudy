/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

/**
 * Author: sdugar
 * Date:   11/5/14
 * Time:   1:26 PM
 */
public class MyThread extends Thread {
   @Override
   public void run() {
      System.out.printf("This is running on a thread %s\n",
            Thread.currentThread().getName());

   }
}
