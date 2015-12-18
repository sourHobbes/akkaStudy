/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import static akka.actor.SupervisorStrategy.escalate;
import static akka.actor.SupervisorStrategy.restart;
import static akka.actor.SupervisorStrategy.resume;
import static akka.actor.SupervisorStrategy.stop;

import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.UntypedActor;
import scala.concurrent.duration.Duration;

/**
 * Author: sdugar
 * Date:   11/7/14
 * Time:   10:56 AM
 */
public class ParentActor extends UntypedActor {
   private static SupervisorStrategy strategy =
         new OneForOneStrategy(10, Duration.create("1 minute"),
               /*(Function<Throwable, SupervisorStrategy.Directive>)*/t -> {
                  if (t instanceof ArithmeticException) {
                     return resume();
                  } else if (t instanceof NullPointerException) {
                     return restart();
                  } else if (t instanceof IllegalArgumentException) {
                     return stop();
                  } else {
                     return escalate();
                  }
               });

   @Override
   public void onReceive(Object message) throws Exception {
      if (message instanceof Props) {
         Props props = (Props) message;
         getSender().tell(getContext().actorOf(props), getSelf());
      } else {
         unhandled(message);
      }
   }
}
