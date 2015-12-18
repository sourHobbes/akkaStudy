/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

/**
 * Author: sdugar
 * Date:   11/3/14
 * Time:   3:39 PM
 */

@FunctionalInterface
public interface DoubleConsumer<T, U> {
   public void accept(T t, U u);
   default public void accept(T t, Integer n) {
      return;
   }
}