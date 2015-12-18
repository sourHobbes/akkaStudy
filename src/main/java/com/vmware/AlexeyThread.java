/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

/**
 * Author: sdugar
 * Date:   11/5/14
 * Time:   1:52 PM
 */
public class AlexeyThread extends Thread {
   public AlexeyThread(Runnable target) {
      super(target);
   }

   @Override
   public void run() {
      System.out.printf("Running " + this.getClass().getName() + " " + Thread
            .currentThread().getName() + "\n");
      super.run();
   }
}
