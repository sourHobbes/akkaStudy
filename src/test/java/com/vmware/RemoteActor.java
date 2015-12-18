/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import org.testng.annotations.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.FromConfig;

/**
 * Author: sdugar
 * Date:   11/7/14
 * Time:   2:58 PM
 */
public class RemoteActor {
   @Test
   public void remoteActorTest() throws InterruptedException {
      Config config = ConfigFactory.load("routing-system");

      ActorSystem system = ActorSystem.create("MySystem");
      //System.out.println(config.getConfig("akka.routing-system"));
      ActorRef actorRef = system.actorOf(FromConfig.getInstance().props(Props.create
            (SimpleActor.class)), "my-router");
      ActorSelection selection = system.actorSelection(
            "akka.tcp://MySystem@127.0.0.1:10888/user/my-router");
      selection.tell("howdy!", system.deadLetters());
      //while (true) {}

      Thread.sleep(5000);
      system.shutdown();
      system.awaitTermination();

   }
}
