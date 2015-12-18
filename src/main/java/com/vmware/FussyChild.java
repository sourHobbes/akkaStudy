/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import akka.actor.UntypedActor;

/**
 * Author: sdugar
 * Date:   11/7/14
 * Time:   11:44 AM
 */
public class FussyChild extends UntypedActor {

   @Override
   public void onReceive(Object message) throws Exception {
      if (message.equals("illegal_argument")) {
         throw new IllegalArgumentException("I want candy");
      } else if (message.equals("arithmetic")) {
         throw new ArithmeticException("I hate math!");
      } else if (message.equals("null")) {
         throw new NullPointerException("I don't know where my sister is");
      } else if (message.equals("class")){
         throw new ClassNotFoundException("I don't want to go to school!");
      } else {
         unhandled(message);
      }
   }
}