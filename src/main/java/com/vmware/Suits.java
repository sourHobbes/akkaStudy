package com.vmware;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Author: sdugar
 * Date:   11/4/14
 * Time:   6:54 PM
 */
public enum Suits {
   @SuppressWarnings("unused")
   HEARTS,
   @SuppressWarnings("unused")
   SPADES,
   @SuppressWarnings("unused")
   CLUBS,
   @SuppressWarnings("unused")
   DIAMONDS;

   @SuppressWarnings("unused")
   private int unused;

   public static void main (String [] args)
         throws IllegalAccessException, InstantiationException {
      Arrays.stream(Suits.values()).map(Suits::ordinal).forEach(System.out::println);
      //Suits newSuit = Suits.class.newInstance();
      //System.out.println(newSuit);
      InstantiationException ie = new InstantiationException();
      Arrays.stream(ie.getClass().getFields()).forEach(System.out::println);
      String allValues = Arrays.stream(Suits.values()).map(Enum::toString)
            .collect(
                  Collectors.joining(",", "[", "]")
            );
      /*
      if (!l.isEmpty())
         for (Object e : ie.getClass().getFields()) {
            System.out.println(e);
         }
      else
         System.out.println("The fields are empty");
      */
      System.out.println(allValues);
   }
}
