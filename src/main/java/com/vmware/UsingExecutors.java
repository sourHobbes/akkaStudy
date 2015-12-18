/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Nullable;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * Author: sdugar
 * Date:   11/6/14
 * Time:   1:12 PM
 */
public class UsingExecutors {
   public static void main (String [] args) throws ExecutionException,
         InterruptedException {
      ExecutorService executorService = Executors.newWorkStealingPool(10);
      ListeningExecutorService les =
            MoreExecutors.listeningDecorator(executorService);
      //final CountDownLatch latch = new CountDownLatch(1);

      Callable<Integer> callableIo = new Callable<Integer>() {
         @Override
         public Integer call() throws Exception {
            Thread.sleep(10000);
            System.out.println("Thread id is " + Thread.currentThread().getId());
            return 10 + 100;
         }
      };

      Callable<Integer> callableCo = new Callable<Integer>() {
         @Override
         public Integer call() throws Exception {
            System.out.println("Thread id is " + Thread.currentThread().getId());
            return 10 + 150;
         }
      };

      /*
      Callable<List<Integer>> addList = new Callable<List<Integer>>() {
         @Override
         public List<Integer> call() throws Exception {
            return null;
         }
      };
      */

      ListenableFuture<Integer> lf = les.submit(callableIo);
      ListenableFuture<Integer> lf2 = les.submit(callableCo);

      Futures.addCallback(lf, new FutureCallback<Integer>() {
         @Override
         public void onSuccess(Integer result) {
            try {
               Thread.sleep(10000);

            } catch (InterruptedException e) {
               e.printStackTrace();
               //latch.countDown();
            }
            System.out.println("Hooray the answer is " + result);
            //latch.countDown();
         }
         @Override
         public void onFailure(@Nullable Throwable throwable) {
            System.out.println("Boom!");
            throwable.printStackTrace();
         }
      });

      List<ListenableFuture<Integer>> listFutures = new ArrayList<>();
      listFutures.add(lf);
      listFutures.add(lf2);
      ListenableFuture<List<Integer>> lfList = Futures.allAsList(listFutures);
      System.out.println(lfList.get());
      /*
      lfList.addListener(new Runnable() {
         @Override
         public void run() {
            System.out.println("executed");
         }
      }, executorService);

      lfList.get();
      ListenableFuture<Integer> listReduce =
            Futures.transform(lfList, (Function<List<Integer>, Integer>) input -> {
               Integer result = 0;
               for (Integer i : input) {
                  result += i;
               }
               return result;
            });

      System.out.println(listReduce.get());
      */
      //lf2.get(); lf.get(); latch.await();
      //lf.get();
      /*
      les.shutdown();
      while (!les.isTerminated()) {
         Thread.sleep(1);
      }
      */
      //les.awaitTermination(1, TimeUnit.MILLISECONDS);

      /*
      List<ListenableFuture<Integer>> f = new ArrayList<>();

      f.add(les.submit(callableIo));
      f.add(les.submit(callableIo));
      f.add(les.submit(callableIo));
      f.add(les.submit(callableIo));

      //Thread.sleep(400);
      f.add(les.submit(callableCo));
      f.add(les.submit(callableCo));
      f.add(les.submit(callableCo));
      f.add(les.submit(callableCo));

      //ExecutorCompletionService
      f.parallelStream().map(x -> {
         try {
            return x.get();
         } catch (Exception e) {
            return null;
         }
      }).forEach(System.out::println);

     */
   }
}