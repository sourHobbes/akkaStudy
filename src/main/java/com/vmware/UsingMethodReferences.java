/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import static java.util.stream.Collectors.summingInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.google.common.collect.Lists;


/**
 * @author sdugar
 *         11/3/14
 *         4:30 PM
 */
public class UsingMethodReferences {

   public static void main(String[] args) throws InterruptedException {
      List<String> numbersAsStrings = new ArrayList<>(Arrays.asList("1", "2", "3"));

      MyIntegerParser mip = new MyIntegerParser();
      numbersAsStrings.stream().map(Integer::parseInt);
      numbersAsStrings.stream().map(mip::parse).map(s -> s * 10);
      IntStream stream = IntStream.range(1, 10).map(s -> s * 50);

      System.out.println(stream.boxed().collect(Collectors.toCollection(HashSet::new)));

      System.out.println(numbersAsStrings.stream().map(mip::parse).map(s -> s * 15)
            .collect(Collectors.toCollection(HashSet::new)));

      Stream<List<Integer>> streams = Stream.of(
            Lists.newArrayList(1, 2),
            Lists.newArrayList(3, 5, 6),
            Lists.newArrayList(7, 8)
      );

      System.out.println(
            streams.flatMap(lst -> lst.stream()
                  .map(i -> i * 20))
                  .filter(integer -> (integer % 3 == 0))
                  .collect(summingInt(value -> value)));

      streams = Stream.of(
            Lists.newArrayList(1, 2),
            Lists.newArrayList(3, 5, 6),
            Lists.newArrayList(7, 8)
      );

      System.out.println(
            streams.flatMap(lst -> lst.parallelStream().map(i -> {
               System.out.println(Thread.currentThread().getName());
               return i * 20;
            })).filter(integer -> (integer % 3 == 0)).reduce((total, next) -> {
               System.out.println("total: " + total);
               System.out.println("next: " + next);
               return total * next;
            }).get());

      //.map(Object::toString)
      //.collect(Collectors.joining(":", "{", "}")));
      System.out.println(IntStream.range(0, 10).parallel().map(i -> {
         System.out.println(Thread.currentThread().getName());
         return i;
      }).reduce((total, next) -> {
         return total + next;
      }));
   }

   public static class MyIntegerParser {
      public Integer parse(String string) {
         return Integer.parseInt(string);
      }
   }
}