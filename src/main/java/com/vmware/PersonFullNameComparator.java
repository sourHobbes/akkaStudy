/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import java.util.Comparator;

/**
 * Author: sdugar
 * Date:   11/5/14
 * Time:   10:26 AM
 */
public class PersonFullNameComparator implements Comparator<Person> {

   @Override
   public int compare(Person p1, Person p2) {
      return p1.getFullName().compareTo(p2.getFullName());
   }
}
