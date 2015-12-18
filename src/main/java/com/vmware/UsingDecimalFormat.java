/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import java.text.DecimalFormat;

/**
 * Author: sdugar
 * Date:   11/5/14
 * Time:   11:25 AM
 */
public class UsingDecimalFormat {
   public static void main (String [] args) {
      int i = 1_000;
      DecimalFormat decimalFormat = new DecimalFormat("00000000");
      DecimalFormat decimalFormatPerMille = new DecimalFormat("\u2030#######");
      System.out.println(decimalFormat.format(i));
      System.out.println(decimalFormatPerMille.format(.0050));

      Integer i1 = i;
      Integer i2 = i;

      System.out.println(i1.equals(i2));
   }
}
