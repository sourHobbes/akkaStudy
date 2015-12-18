/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Author: sdugar
 * Date:   11/5/14
 * Time:   9:58 AM
 */
public class UsingSort {
   public static void main (String [] args) {
      List<String> items = Arrays.asList("Iowa", "Cali", "Alabama", "Texas",
            "New Mexico", "Georgia");

      System.out.println("----");
      Collections.sort(items);
      items.forEach(System.out::println);
      System.out.println("----");
      System.out.println(items.getClass().getCanonicalName());
      System.out.println("----");

      List<American> americans = Arrays.asList(
            new American("Martha", "Stewart", "123-55-3032"),
            new American("Kim", "Kardashian", "422-49-9399"),
            new American("Donald", "Trump", "199-09-1092"),
            new American("Charlie", "Sheen", "399-92-0049")
      );

      Collections.sort(americans);
      System.out.println(americans);
      System.out.println("----");
      Collections.sort(americans, new PersonFullNameComparator());
      System.out.println(americans);
      System.out.println("----");
      americans.sort(new PersonFullNameComparator());
      System.out.println(americans);
      System.out.println("----");
      americans.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
      americans.sort(null);
      System.out.println(americans);
      System.out.println("----");
   }
}
