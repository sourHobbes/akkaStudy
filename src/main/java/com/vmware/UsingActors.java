/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import java.time.LocalTime;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Author: sdugar
 * Date:   11/6/14
 * Time:   3:24 PM
 */
public class UsingActors extends UntypedActor {
   @Override
   public void onReceive(Object message) throws Exception {
      System.out.printf("Messages received: %s, %s" + LocalTime.now() + "\n", message,
            Thread.currentThread().getName());
      //Thread.sleep(10000);
   }

   public static void main (String [] args) {
      System.out.println("Actor System Test");
      ActorSystem system = ActorSystem.create("MySystem");
      //ActorRef ref = system.actorOf(Props.create(UsingActors.class), "charlize_theron");
      //ActorRef ref2 = system.actorOf(Props.create(UsingActors.class), "charlize");
      ActorRef ref3 = system.actorOf(Props.create(AskActor.class), "ask_actor");
      //ActorSelection charlize = system.actorSelection("/user/charlize_theron");
      //ref.tell("Howdy", system.deadLetters());
      ref3.tell("go", system.deadLetters());
      //ref.tell(PoisonPill.getInstance(), system.deadLetters());

      //charlize.tell("I love you", system.deadLetters());
      while (true) { }
      //system.shutdown();
      //system.awaitTermination();
   }
}