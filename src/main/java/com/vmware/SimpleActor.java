package com.vmware;

import static akka.dispatch.Futures.future;
import static akka.dispatch.Futures.successful;

import java.util.concurrent.Callable;

import akka.actor.ActorSystem;
import akka.actor.UntypedActor;
import akka.dispatch.Mapper;
import akka.dispatch.OnComplete;
import akka.dispatch.OnFailure;
import akka.dispatch.OnSuccess;
import akka.dispatch.Recover;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;

public class SimpleActor extends UntypedActor {

    public SimpleActor() {
        System.out.println("Created Actor");
    }

    public final static class PrintResult<T> extends OnSuccess<T> {
        @Override
        public final void onSuccess(T t) {
            if (t == null) {
                System.out.println("Task failed");
            }
            System.out.println(t);
        }
    }

    public final static class PrintFailure extends OnFailure {
        @Override
        public void onFailure(Throwable failure) throws Throwable {
            System.out.println(failure.toString());
        }
    }

    public final static class PrintOnComplete<T> extends OnComplete<T> {

        @Override
        public void onComplete(Throwable failure, T success) throws Throwable {
            if (failure != null) {
                PrintFailure pf = new PrintFailure();
                pf.onFailure(failure);
            } else {
                PrintResult<T> result = new PrintResult<>();
                result.onSuccess(success);
            }
        }
    }


    @Override
    public void onReceive(Object message) throws Exception {
        System.out.format("Message Received in %s: %s\n", Thread.currentThread().getName(), message);
    }

    public static void main(String[] args) throws InterruptedException {

        /*
        ActorSystem system = ActorSystem.create("MySystem");
        ActorRef ref = system.actorOf(Props.create(SimpleActor.class), "charlize");
        ref.tell("Howdy", system.deadLetters());
        ref.tell("Howdy", system.deadLetters());
        ref.tell("Howdy", system.deadLetters());
        ref.tell("Howdy", system.deadLetters());

        ActorSelection charlize = system.actorSelection("/user/charlize");
        charlize.tell("I love you", system.deadLetters());

        system.shutdown();
        system.awaitTermination();
        */
        ActorSystem system = ActorSystem.create("MySystem");
        final ExecutionContext ec = system.dispatcher();

        Future<String> future = future(new Callable<String>() {
            public String call() {
                throw new IllegalArgumentException();
                /*
                try {
                    //Integer divideByZero = 10 / 0;
                    return "full";
                } catch (Exception ex) {
                    throw ex;
                }
                */
            }
        }, ec).map(new Mapper<String, String>() {
            @Override
            public String apply(String t) {
                return "delta";
                //throw new IllegalArgumentException();
            }
        }, ec).recoverWith(new Recover<Future<String>>() {
            public Future<String> recover(Throwable problem) throws Throwable {
                if ((problem instanceof ArithmeticException)) {
                    return future(new Callable<String>() {
                        public String call() {
                            return "full";
                        }
                    }, ec);
                } else
                    return successful(null);
            }
        }, ec);
        future.onComplete(new PrintOnComplete<String>(), system.dispatcher());
        /*.transform(new Mapper<Integer, Integer>() {
            public Integer apply(Integer t) {
                return 15;
            }
        }, new Mapper<Throwable, Throwable>() {
            @Override
            public Throwable apply(Throwable parameter) {
                return parameter;
            }
        }, ec);*//*.flatMap(new
        Mapper<Integer,
        Future<Integer>>() {
            public Future<Integer> apply(final Integer s) {
                return future(new Callable<Integer>() {
                    public Integer call() {
                        if (s != null) {
                            return s + 55;
                        } else {
                            return null;
                        }
                    }
                }, ec);
            }
        }, ec);*/
        /*.recover(new Recover<Integer>() {
            public Integer recover(Throwable problem) throws Throwable {
                if (problem instanceof ArithmeticException)
                    return 0;
                else
                    throw problem;
            }
        }, ec);*/
        /*recoverWith(new Recover<Future<Integer>>() {
            public Future<Integer> recover(Throwable problem) throws Throwable {
                if (problem instanceof ArithmeticException) {
                    return future(new Callable<Integer>() {
                        public Integer call() {
                            return 0;
                        }
                    }, ec);
                } else
                    throw problem;
            }
        }, ec);
        *//*
        Iterable<String> listStrings = Arrays.asList("a", "b", "c");

        Future<Iterable<String>> futureResult = traverse(listStrings,
              new Function<String, Future<String>>() {
                  public Future<String> apply(final String r) {
                      return future(new Callable<String>() {
                          public String call() {
                              if (r.equals("a")) {
                                  throw new IllegalArgumentException();
                              }
                              return r.toUpperCase();
                          }
                      }, ec).recoverWith(new Recover<Future<String>>() {
                          public Future<String> recover(Throwable problem) throws Throwable {
                              if (problem instanceof IllegalArgumentException) {
                                  return future(new Callable<String>() {
                                      public String call() {
                                          return null;
                                      }
                                  }, ec);
                              } else
                                  throw problem;
                          }
                      }, ec);
                  }
              }, ec);*/

        //future.onSuccess(new PrintResult<Integer>(), system.dispatcher());
        /*
        future.onFailure(new PrintFailure(), system.dispatcher());
        */
        /*
        future.onComplete(new PrintOnComplete<Integer>() , system.dispatcher());
        */
    }
    private enum TestEnum {
        TEST1(40),
        TEST2(50);

        int weight;

        private TestEnum(int weight) {
            this.weight = weight;
        }
    }

    /*
    private Future<VcdTask> addVAppNetwork(final Map<String, VcdOrgVdcNetwork> networks) {
        Future<Iterable<VcdTask>> networkAdditionFutures = traverse(networks.entrySet(),
              new Function<Map.Entry<String, VcdOrgVdcNetwork>, Future<VcdTask>>() {
                  @Override
                  public Future<VcdTask> apply(final Map.Entry<String, VcdOrgVdcNetwork> param)
                        throws Exception {
                      TaskFuture<VcdTask> task = emitter.submit(new VcdCall<VcdTask>() {
                          @Override
                          public VcdTask call() {
                              return vApp.addOrgNetwork(param.getValue());
                          }
                      });
                      return task.getUnderlyingFuture();
                  }
              }, getContext());

        Future<Iterable<VcdTask>> networkAdditionsSeq = sequence(networkAdditions, getContext());
        Future<VcdTask> networkAdditionsResult = networkAdditionFutures.map(
              new Mapper<Iterable<VcdTask>, VcdTask>() {
                  @Override
                  public VcdTask apply(Iterable<VcdTask> allResetNetworks) {
                      for (VcdTask task : allResetNetworks) {
                          logger.info("AddOrgNetwork task " + task.getDetails() + " invoked");
                      }
                      return allResetNetworks.iterator().next();// Just return first one
                  }
              }, getContext());

        return networkAdditionsResult;
    }
    */
    /*
    Future<T> f ;
    f.recoverWith()
    */
}
