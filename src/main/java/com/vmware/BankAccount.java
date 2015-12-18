/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

/**
 * Author: sdugar
 * Date:   11/5/14
 * Time:   3:21 PM
 */
public class BankAccount {
   private int pennies = 0;

   @SuppressWarnings("unused")
   private Object myLock;

   public synchronized void deposit(long pennies) throws InterruptedException {
      //synchronized (this) {
         this.pennies += pennies;
         notifyAll();
         /*
         //If synchronized on class lock this new thread will cause a deadlock...
         //wont deadlock when synchronizing on this instance...
         BankAccount account1 = new BankAccount();

         //Wont block without joining, most likely the other thread is ignored
         Thread t5 = new Thread(() -> account1.withdrawal(0));
         t5.start();
         t5.join();
         */
      //}
   }

   public synchronized long withdrawal(long pennies) {
      //synchronized (this) {
         while (this.pennies < pennies) {
            try {
               System.out.println(Thread.currentThread().getName() + " is going to " +
                     "wait balance is: " + this.pennies);
               //notify();
               wait();
               System.out.println(Thread.currentThread().getName() + " is going to " +
                     "wake balance is: " + this.pennies);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         }
         //if (this.pennies >= pennies) {
            this.pennies -= pennies;
            return pennies;
         //}
      //}
      //throw new IllegalArgumentException("Insufficient funds!");
   }

   public synchronized void printBalance() {
      //synchronized (this) {
         System.out.println("Balance is : " + pennies + "(pennies)");
      //}
   }

   /*public static synchronized void*/

   public static void main (String [] args) throws InterruptedException {
      BankAccount account = new BankAccount();
      Thread d1 = null;
      Thread d2 = null;
      Thread w1 = new Thread(() -> {
         account.withdrawal(1300);
      }, "w500");
      d1 = new Thread(() ->  {
         try {
            account.deposit(1000);
         } catch (Exception e) {
            System.out.println("caught exception");
         }
      }, "d1000");
      d2 = new Thread(() -> {
         try {
            account.deposit(300);
         } catch (Exception e) {

         }
      }, "d300");
      Thread w2 = new Thread(() -> account.withdrawal(700), "w700");
      Thread w3 = new Thread(() -> account.withdrawal(100), "w100");
      Thread d3 = new Thread(() -> {
         try {
            account.deposit(1000);
         } catch (Exception e) {

         }
      }, "d100");
      //This will not work cuz only thread start is getting sync'd
      //synchronized (account) { d1.start(); }
      //account.printBalance();
      //synchronized (account) { w1.start(); }
      //account.printBalance();
      //synchronized (account) { d2.start(); }
      //d1.join();d2.join();w1.join();
      //account.printBalance();
      //account.printBalance();
      w1.start();

      Thread.sleep(500);
      w2.start();
      d1.start();
      w3.start();
      d2.start();
      d3.start();
      d1.join(); d2.join(); w1.join(); w2.join(); w3.join();
      account.printBalance();
   }
}
