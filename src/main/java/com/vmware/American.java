/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

/**
 * Author: sdugar
 * Date:   11/4/14
 * Time:   1:19 PM
 */
public class American implements Person, Comparable<American> {
   private String firstName;
   private String lastName;
   private String socialSecurityNumber;

   public American(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public American(String firstName, String lastName, String socialSecurityNumber) {
      this.socialSecurityNumber = socialSecurityNumber;
      this.firstName = firstName;
      this.lastName = lastName;
   }

   @SuppressWarnings("unused")
   public String getSocialSecurityNumber() {
      return socialSecurityNumber;
   }

   @SuppressWarnings("unused")
   public void setSocialSecurityNumber(String socialSecurityNumber) {
      this.socialSecurityNumber = socialSecurityNumber;
   }

   @Override
   public String getMadamFullName() {
      return null;
   }

   @Override
   public String getFirstName() {
      return "American " + firstName;
   }

   @Override
   public String getLastName() {
      return lastName;
   }

   /*
   @Override
   public String getFullName() {
      return "Bob Barker";
   }
   */

   public static void main(String [] args) {
      American paulRudd = new American("Paul", "Rudd");
      System.out.println(paulRudd.getSirFullName());
   }

   @Override
   public int compareTo(American o) {
      return (this.firstName.compareTo(o.firstName));
   }

   /*
   @Override
   public String toString() {
      return "American{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
            '}';
   }
   */

   @Override
   public String toString() {
      final StringBuilder sb = new StringBuilder("American{")
      .append("firstName='").append(firstName).append('\'')
      .append(", lastName='").append(lastName).append('\'')
      .append(", socialSecurityNumber='").append(socialSecurityNumber).append('\'')
      .append('}');
      return sb.toString();
   }
}
