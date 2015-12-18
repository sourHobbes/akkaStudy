/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

/**
 * Author: sdugar
 * Date:   11/5/14
 * Time:   11:15 AM
 */
public class UsingStringBuilders {
   public static void main (String [] args) {
      //Thread-safe
      StringBuffer stringBuffer = new StringBuffer();

      //Non-thread-safe, but generally preferred
      StringBuilder stringBuilder = new StringBuilder();

   }
}
