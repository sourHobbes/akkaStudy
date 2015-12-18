/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import org.testng.annotations.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Author: sdugar
 * Date:   11/7/14
 * Time:   9:46 AM
 */
public class AskActorTest {

   @Test(threadPoolSize = 1, invocationCount = 1)
   public void actorTest() throws InterruptedException {
      ActorSystem system = ActorSystem.create("MySystem");
      ActorRef reporterActorRef = system.actorOf(Props.create(ReporterActor.class));
      system.actorOf(Props.create(AskActor.class), "askactor");
      reporterActorRef.tell("go", reporterActorRef);
      Thread.sleep(20000);
      system.shutdown();
      system.awaitTermination();
   }
}
