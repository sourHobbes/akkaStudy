/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Author: sdugar
 * Date:   11/3/14
 * Time:   1:36 PM
 */
public class UsingReaders {
   public static void main(String[] args) {
      try (InputStream is = UsingReaders.class.getResourceAsStream("/config.properties");
           InputStreamReader reader = new InputStreamReader(is);
           BufferedReader buff = new BufferedReader(reader);
      ) {
         String input;
         while ((input = buff.readLine()) != null) {
            String[] items = input.split("=");
            System.out.format("key : %s, value : %s\n", items[0], items[1]);
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}