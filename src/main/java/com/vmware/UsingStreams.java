/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Author: sdugar
 * Date:   11/3/14
 * Time:   11:12 AM
 */
public class UsingStreams {
   public static void main(String[] args) {
      String userHome = System.getProperty("user.home");
      FileInputStream fileInputStream = null;
      FileOutputStream fileOutputStream = null;
      System.out.println(userHome);

      try {
         fileInputStream = new FileInputStream(userHome + "/advanced_file.txt");
         fileOutputStream = new FileOutputStream(
                 userHome + "/advanced_file_" + LocalDateTime.now() + ".txt");
         int by;
         while ((by = fileInputStream.read()) != -1) {
            fileOutputStream.write(by);
         }
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if (fileInputStream != null) {
            try {
               fileInputStream.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
         if (fileOutputStream != null) {
            try {
               fileOutputStream.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }
      }

      try (FileInputStream fileInputStream2 = new FileInputStream(userHome +
              "/advanced_file.txt");
           FileOutputStream fileOutputStream2 = new FileOutputStream(
              userHome + "/advanced_file_" + LocalDateTime.now() + ".txt");
      ) {
         int by;
         while ((by = fileInputStream2.read()) != -1) {
            fileOutputStream2.write(by);
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
