/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import org.testng.annotations.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.FromConfig;
import scala.concurrent.Future;
import scala.concurrent.Promise;

/**
 * Author: sdugar
 * Date:   11/7/14
 * Time:   2:13 PM
 */
public class RoutingActorTest {
   //@Test(threadPoolSize = 4, invocationCount = 10)
   @Test
   public void actorTest() throws InterruptedException {
      Config config = ConfigFactory.load("routing-system");

      //System.out.println(config.getConfig("akka.routing-system").getObjectList
      //      ("akka.actor.deployment/my-router"));
      ActorSystem system = ActorSystem.create("MySystem");
      //System.out.println(config.getConfig("akka.routing-system"));
      ActorRef actorRef = system.actorOf(FromConfig.getInstance().props(Props.create
                  (SimpleActor.class)), "my-router");

      actorRef.tell("go1", system.deadLetters());
      actorRef.tell("go2", system.deadLetters());
      actorRef.tell("go3", system.deadLetters());
      actorRef.tell("go4", system.deadLetters());
      actorRef.tell("go5", system.deadLetters());
      actorRef.tell("go6", system.deadLetters());
      actorRef.tell("go7", system.deadLetters());
      actorRef.tell("go8", system.deadLetters());
      actorRef.tell("go9", system.deadLetters());
      actorRef.tell("go10", system.deadLetters());

      Thread.sleep(20000);
      system.shutdown();
      system.awaitTermination();
   }
}
