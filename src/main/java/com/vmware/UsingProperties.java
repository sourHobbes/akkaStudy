/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Author: sdugar
 * Date:   11/3/14
 * Time:   1:36 PM
 */
public class UsingProperties {
   public static void main(String [] args) {
      try (InputStream is = UsingProperties.class.getResourceAsStream("/config.properties");
           InputStreamReader reader = new InputStreamReader(is);
           BufferedReader buff = new BufferedReader(reader)
      ) {
         Properties properties = new Properties();
         if (is.markSupported()) {
            is.mark(0);
         }
         properties.load(is);

         //for (Map.Entry e : properties.entrySet()) {
         //   System.out.format("keyx : %s, valuex : %s\n", e.getKey(), e.getValue());
         //}

         properties.forEach((o, o2) ->
                 System.out.format("keyx : %s, valuex : %s\n", o, o2));

         is.reset();
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