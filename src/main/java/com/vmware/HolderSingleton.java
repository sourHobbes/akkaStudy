/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

/**
 * Author: sdugar
 * Date:   11/6/14
 * Time:   10:22 AM
 */
public class HolderSingleton {
   
   static {
      System.out.println("holder singleton init");
   }

   static class SingletonHolder {
      static {
         System.out.println("Singleton holder created");
      }

      static HolderSingleton instance = new HolderSingleton();

      private SingletonHolder () {
         System.out.println("Created holder singleton");
      }

      public static HolderSingleton getInstance() {
         System.out.println("get instance");
         return SingletonHolder.instance;
      }
   }
}
