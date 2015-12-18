/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import org.testng.annotations.Test;

/**
 * Author: sdugar
 * Date:   11/6/14
 * Time:   9:43 AM
 */
public class MySingletonTest {
   @Test(threadPoolSize = 3, invocationCount = 3)
   public void testMySingletonSimpleCase() {
      //MySingleton myInstance = MySingleton.getInstance();
      //System.out.println(myInstance.hashCode());
      HolderSingleton holder = HolderSingleton.SingletonHolder.getInstance();
      System.out.println(holder.hashCode());
   }
}