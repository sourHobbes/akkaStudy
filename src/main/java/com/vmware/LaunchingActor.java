/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import static akka.pattern.Patterns.ask;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.dispatch.Futures;
import akka.dispatch.OnSuccess;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.Function1;
import scala.concurrent.Future;
import scala.concurrent.Promise;

/**
 * Author: sdugar
 * Date:   11/7/14
 * Time:   11:50 AM
 */
public class LaunchingActor extends UntypedActor {
   LoggingAdapter log = Logging.getLogger(context().system(), this);

   @Override
   public void onReceive(Object message) throws Exception {
      if (message.equals("go")) {
         ActorSelection selection =
               context().actorSelection("/user/parentactor");
         Future<Object> future = ask(selection,
               Props.create(FussyChild.class), 15000);

         future.onSuccess(new OnSuccess<Object>() {
            @Override
            public void onSuccess(Object result) throws Throwable {
               if (result instanceof ActorRef) {
                  ActorRef child = (ActorRef) result;
                  child.tell("class", child);

               }
            }
         }, context().dispatcher());

         Promise<Object> promise = Futures.promise();
         promise.completeWith(future);

         Promise<Object> promise2 = Futures.promise();
         //promise.completeWith((Future<Object>) promise.future());
         //Future<Object> future1 = promise.future();
         //promise.completeWith(future);
         //promise.success("Completed");
         while (!promise.isCompleted()) {
            Thread.sleep(1000);
            System.out.println("Promise not complete");
            promise2.success("success");
         }
         //System.out.println(promise.future().toString());
      } else {
         unhandled(message);
      }
   }
}
