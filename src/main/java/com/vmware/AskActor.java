package com.vmware;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class AskActor extends UntypedActor {
   LoggingAdapter log = Logging.getLogger(context().system(), this);
   Config regularConfig = ConfigFactory.load();

   @Override
    public void onReceive(Object message) throws Exception {
        log.debug(regularConfig.getString("akka.my-message"));
        log.debug("---------------------" + getSender().path().toString());
        if (message.equals("How do you feel today?")) {
           log.debug("Got the message {}", message);
           Thread.sleep(1000);
           getSender().tell("Fine", getSelf());
        } else {
           unhandled(message);
        }
    }
}
