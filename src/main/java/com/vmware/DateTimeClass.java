/* **********************************************************************
 * Copyright 2014 VMware, Inc. All rights reserved. VMware Confidential
 * **********************************************************************/
package com.vmware;

import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Author: sdugar
 * Date:   11/4/14
 * Time:   1:47 PM
 */
public class DateTimeClass {
   public static void main (String [] args) {
      Date date = new Date();
      System.out.println(date.getYear() + 1900);
      System.out.println(System.currentTimeMillis());
      System.out.println(Instant.now());
      Instant instant = Instant.now();
      System.out.println(instant.getEpochSecond());
      System.out.println(instant.getNano());

      Instant newInstant = Instant.now().plus(19, HOURS);
      System.out.println(instant);
      System.out.println(newInstant);
      System.out.println(LocalDateTime.now().get(ChronoField.HOUR_OF_AMPM));
      //LocalDate.parse()

      Map<String, String> map = new HashMap<String, String>();
      ZonedDateTime nowInIndia =
            ZonedDateTime.now(ZoneId.of("Asia/Calcutta"));
      System.out.println(nowInIndia);

      Locale hiLocale = new Locale("hi", "IN");
      System.out.println(hiLocale.getDisplayLanguage());

      TemporalAdjuster fourMinutesFromNow = temporal -> temporal.plus(4, MINUTES);

      System.out.println(LocalTime.of(12, 0, 0).with(fourMinutesFromNow));

      DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL,
                  FormatStyle.FULL).withLocale(hiLocale);

      System.out.println(dateTimeFormatter.format(ZonedDateTime.now()));

      DateTimeFormatter dateTimeFormatter1 =
            DateTimeFormatter.ofPattern("MMMM dd, yyyy '(In Time Zone: 'VV')'");
      ZonedDateTime zonedDateTime = ZonedDateTime.now();

      System.out.println(dateTimeFormatter1.format(zonedDateTime));
   }
}
