/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Author: sdugar
 * Date:   11/3/14
 * Time:   4:10 PM
 */
public class UsingUrls {
   public static void main (String [] args) {
      try (InputStream is = new URL("http://www.vmware.com").openStream();
           InputStreamReader reader = new InputStreamReader(is);
           BufferedReader buff = new BufferedReader(reader);
      ) {
         int result = Integer.parseInt("5e");
         //int result = 1_000_000;
         String input;
         while ((input = buff.readLine()) != null) {
            System.out.println(input);
         }
      } catch (IOException | NumberFormatException ex) {
         ex.printStackTrace();
      }
   }
}
