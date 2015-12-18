/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import static akka.pattern.Patterns.ask;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;
import akka.dispatch.OnFailure;
import akka.dispatch.OnSuccess;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.Function1;
import scala.concurrent.Future;
import scala.runtime.BoxedUnit;

/**
 * Author: sdugar
 * Date:   11/6/14
 * Time:   4:44 PM
 */
public class ReporterActor extends UntypedActor {
   /*
   @Override
   public void onReceive(Object message) throws Exception {
      if (message.equals("go")) {
         ActorSelection selection = context().actorSelection("/user/charlize_theron");
         scala.concurrent.Future<Object> future = ask(selection, "How do you feel " +
               "today", 100);
         future.onSuccess(new OnSuccess<Object>() {
            @Override
            public void onSuccess(Object result) throws Throwable {

            }
         }, context().dispatcher());
      }
   }
   */

   LoggingAdapter log = Logging.getLogger(context().system(), this);

   @Override
   public void onReceive(Object message) throws Exception {
      if (message.equals("go")) {
         ActorSelection selection =
               context().actorSelection("/user/askactor");
         Future<Object> future = ask(selection,
               "How do you feel today?", 2000);

         log.debug("If we see this under 5 sec. it does not block " + Thread
               .currentThread().getName());
         future.onSuccess(new OnSuccess<Object>() {
            @Override
            public void onSuccess(Object result) throws Throwable {
               log.info("Received Result: {}", result);
            }
         }, context().dispatcher());
         future.onFailure(new OnFailure() {
            @Override
            public void onFailure(Throwable failure) throws Throwable {
               log.error("Sending of message failed with error {}", failure.fillInStackTrace());
            }
         }, context().dispatcher());
               //, context().dispatcher());
      } else {
         unhandled(message);
      }
   }
   /*
   public static void main (String [] args) {
      ReporterActor ra = new ReporterActor();
   }
   */
}
