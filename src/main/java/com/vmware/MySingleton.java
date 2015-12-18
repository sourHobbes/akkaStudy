/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

/**
 * Author: sdugar
 * Date:   11/6/14
 * Time:   9:40 AM
 */
public class MySingleton {
   private static volatile MySingleton instance = null;

   private MySingleton () { }

   public static MySingleton getInstance() {
      if (instance == null) {
         synchronized (MySingleton.class) {
            if (instance == null) {
               instance = new MySingleton();
            }
         }
      }
      return instance;
   }
}
