/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Author: sdugar
 * Date:   11/5/14
 * Time:   11:43 AM
 */
public class UsingI18N {
   public static void main (String [] args) {
      ResourceBundle hindiMessages =
            ResourceBundle.getBundle("messages", new Locale("hi", "IN"));
      System.out.println(hindiMessages.getString("Hello") + " " +
            hindiMessages.getString("World"));

      ResourceBundle spanishMessages =
            ResourceBundle.getBundle("messages", Locale.forLanguageTag("es"));
      System.out.println(spanishMessages.getString("Hello") + " " +
            spanishMessages.getString("World"));
   }
}