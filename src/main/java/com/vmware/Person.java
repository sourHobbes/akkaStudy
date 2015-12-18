package com.vmware;

/**
 * Author: sdugar
 * Date:   11/4/14
 * Time:   1:17 PM
 */
public interface Person {
   public String getFirstName();

   public String getLastName();

   default String getFullName() {
      return getFirstName() + " " + getLastName();
   }

   default String getSirFullName() {
      return "Sir " + getFullName();
   }

   default String getMadamFullName() {
      return "Madam " + getFullName();
   }
}
