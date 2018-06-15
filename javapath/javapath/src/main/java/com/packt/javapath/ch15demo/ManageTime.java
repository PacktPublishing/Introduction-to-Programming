package com.packt.javapath.ch15demo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class ManageTime {

    public static void main(String... args){

        localDate();
        localTime();
        localDateTime();
        period();
    }

    private static void period(){
        LocalDateTime ldt1 =  LocalDateTime.parse("2020-02-23T20:23:12");
        LocalDateTime ldt2 = ldt1.plus(Period.ofYears(1));
        System.out.println(ldt2);  //prints: 2021-02-23T20:23:12
        LocalDateTime ldt3 = ldt1.minus(Period.ofYears(1));
        System.out.println(ldt2);  //prints: 2019-02-23T20:23:12
        ldt1.plus(Period.ofMonths(2));
        ldt1.minus(Period.ofMonths(2));
        ldt1.plus(Period.ofWeeks(2));
        ldt1.minus(Period.ofWeeks(2));

        ldt1.plus(Period.ofDays(2));
        ldt1.minus(Period.ofDays(2));

        ldt1.plus(Duration.ofHours(2));
        ldt1.minus(Duration.ofHours(2));
        ldt1.plus(Duration.ofMinutes(2));
        ldt1.minus(Duration.ofMinutes(2));
        ldt1.plus(Duration.ofMillis(2));
        ldt1.minus(Duration.ofMillis(2));

        LocalDate ld1 =  LocalDate.parse("2020-02-23");
        LocalDate ld2 =  LocalDate.parse("2020-03-25");

        Period period = Period.between(ld1, ld2);
        System.out.println(period.getDays());    //prints: 2
        System.out.println(period.getMonths());  //prints: 1
        System.out.println(period.getYears());   //prints: 0
        System.out.println(period.toTotalMonths());    //prints: 1

        period = Period.between(ld2, ld1);
        System.out.println(period.getDays());     //prints: -2

        LocalTime lt1 =  LocalTime.parse("10:23:12");
        LocalTime lt2 =  LocalTime.parse("20:23:14");
        Duration duration = Duration.between(lt1, lt2);
        System.out.println(duration.toDays());     //prints: 0
        System.out.println(duration.toHours());    //prints: 10
        System.out.println(duration.toMinutes());  //prints: 600
        System.out.println(duration.toSeconds());  //prints: 36002
        System.out.println(duration.getSeconds()); //prints: 36002
        System.out.println(duration.toNanos());    //prints: 36002000000000
        System.out.println(duration.getNano());    //prints: 0
    }

    private static void localDateTime(){

        System.out.println(LocalDateTime.now());        //prints: 2018-04-14T21:59:00.142804

        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        System.out.println(LocalDateTime.now(zoneId));  //prints: 2018-04-15T12:59:00.146038

        LocalDateTime ldt1 =  LocalDateTime.parse("2020-02-23T20:23:12");
        System.out.println(ldt1);                       //prints: 2020-02-23T20:23:12

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime ldt2 =  LocalDateTime.parse("23/02/2020 20:23:12", formatter);
        System.out.println(ldt2);                     //prints: 2020-02-23T20:23:12

        LocalDateTime ldt3 =  LocalDateTime.of(2020, 2, 23, 20, 23, 12);
        System.out.println(ldt3);                     //prints: 2020-02-23T20:23:12

        LocalDateTime ldt4 =  LocalDateTime.of(2020, Month.FEBRUARY, 23, 20, 23, 12);
        System.out.println(ldt4);                     //prints: 2020-02-23T20:23:12

        LocalDate ld = LocalDate.of(2020, 2, 23);
        LocalTime lt =  LocalTime.of(20, 23, 12);

        LocalDateTime ldt5 = LocalDateTime.of(ld, lt);
        System.out.println(ldt5);                     //prints: 2020-02-23T20:23:12

        System.out.println(ldt5.getYear());          //prints: 2020
        System.out.println(ldt5.getMonth());         //prints: FEBRUARY
        System.out.println(ldt5.getMonthValue());    //prints: 2
        System.out.println(ldt5.getDayOfMonth());    //prints: 23
        System.out.println(ldt5.getDayOfWeek());     //prints: SUNDAY

        System.out.println(ldt5.getHour());          //prints: 20
        System.out.println(ldt5.getMinute());        //prints: 23
        System.out.println(ldt5.getSecond());        //prints: 12
        System.out.println(ldt5.getNano());          //prints: 0

        System.out.println(ldt5.withYear(2021));     //prints: 2021-02-23T20:23:12
        System.out.println(ldt5.withMonth(5));       //prints: 2020-05-23T20:23:12
        System.out.println(ldt5.withDayOfMonth(5));  //prints: 2020-02-05T20:23:12
        System.out.println(ldt5.withDayOfYear(53));  //prints: 2020-02-22T20:23:12

        System.out.println(ldt5.withHour(3));        //prints: 2020-02-22T03:23:12
        System.out.println(ldt5.withMinute(10));     //prints: 2020-02-22T20:10:12
        System.out.println(ldt5.withSecond(15));     //prints: 2020-02-22T20:23:15
        System.out.println(ldt5.withNano(300));      //prints: 2020-02-22T20:23:12:000000300

        System.out.println(ldt5.plusDays(10));       //prints: 2020-03-04T20:23:12
        System.out.println(ldt5.plusMonths(2));      //prints: 2020-04-23T20:23:12
        System.out.println(ldt5.plusYears(2));       //prints: 2022-02-23T20:23:12

        System.out.println(ldt5.plusHours(10));      //prints: 2020-02-22T06:23:12
        System.out.println(ldt5.plusMinutes(2));     //prints: 2020-02-22T20:25:12
        System.out.println(ldt5.plusSeconds(2));     //prints: 2020-02-22T20:23:14
        System.out.println(ldt5.plusNanos(200));     //prints: 2020-02-22T20:23:14:000000200

        System.out.println(ldt5.minusDays(10));      //prints: 2020-02-13T20:23:12
        System.out.println(ldt5.minusMonths(2));     //prints: 2019-12-23T20:23:12
        System.out.println(ldt5.minusYears(2));      //prints: 2018-02-23T20:23:12

        System.out.println(ldt5.minusHours(10));      //prints: 2020-02-22T10:23:12
        System.out.println(ldt5.minusMinutes(2));     //prints: 2020-02-22T20:21:12
        System.out.println(ldt5.minusSeconds(2));     //prints: 2020-02-22T20:23:10
        System.out.println(ldt5.minusNanos(200));     //prints: 2020-02-22T20:23:11.999999800

        LocalDateTime ldt6 =  LocalDateTime.parse("2020-02-22T20:23:11");
        LocalDateTime ldt7 =  LocalDateTime.parse("2020-02-22T20:23:12");
        System.out.println(ldt6.isAfter(ldt7));       //prints: false
        System.out.println(ldt6.isBefore(ldt7));      //prints: true

    }


    private static void localTime(){

        System.out.println(LocalTime.now());   //prints: 21:15:46.360904

        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        System.out.println(LocalTime.now(zoneId));   //prints: 12:15:46.364378

        LocalTime lt1 =  LocalTime.parse("20:23:12");
        System.out.println(lt1);                     //prints: 20:23:12

        LocalTime lt2 =  LocalTime.of(20, 23, 12);
        System.out.println(lt2);                     //prints: 20:23:12

        System.out.println(lt2.getHour());          //prints: 20
        System.out.println(lt2.getMinute());        //prints: 23
        System.out.println(lt2.getSecond());        //prints: 12
        System.out.println(lt2.getNano());          //prints: 0

        System.out.println(lt2.withHour(3));        //prints: 03:23:12
        System.out.println(lt2.withMinute(10));     //prints: 20:10:12
        System.out.println(lt2.withSecond(15));     //prints: 20:23:15
        System.out.println(lt2.withNano(300));      //prints: 20:23:12:000000300

        System.out.println(lt2.plusHours(10));      //prints: 06:23:12
        System.out.println(lt2.plusMinutes(2));     //prints: 20:25:12
        System.out.println(lt2.plusSeconds(2));     //prints: 20:23:14
        System.out.println(lt2.plusNanos(200));     //prints: 20:23:14:000000200

        System.out.println(lt2.minusHours(10));      //prints: 10:23:12
        System.out.println(lt2.minusMinutes(2));     //prints: 20:21:12
        System.out.println(lt2.minusSeconds(2));     //prints: 20:23:10
        System.out.println(lt2.minusNanos(200));     //prints: 20:23:11.999999800

        LocalTime lt3 =  LocalTime.parse("20:23:12");
        LocalTime lt4 =  LocalTime.parse("20:25:12");
        System.out.println(lt3.isAfter(lt4));       //prints: false
        System.out.println(lt3.isBefore(lt4));      //prints: true
    }

    private static void localDate(){

        System.out.println(LocalDate.now());   //prints: 2018-04-14

        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        for(String zoneId: zoneIds){
            //System.out.println(zoneId);  //prints: Asia/Tokyo
        }

        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        System.out.println(LocalDate.now(zoneId));   //prints: 2018-04-15

        LocalDate lc1 =  LocalDate.parse("2020-02-23");
        System.out.println(lc1);                     //prints: 2020-02-23

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate lc2 =  LocalDate.parse("23/02/2020", formatter);
        System.out.println(lc2);                     //prints: 2020-02-23

        LocalDate lc3 =  LocalDate.of(2020, 2, 23);
        System.out.println(lc3);                     //prints: 2020-02-23

        LocalDate lc4 =  LocalDate.of(2020, Month.FEBRUARY, 23);
        System.out.println(lc4);                     //prints: 2020-02-23

        LocalDate lc5 = LocalDate.ofYearDay(2020, 54);
        System.out.println(lc5);                     //prints: 2020-02-23

        System.out.println(lc5.getYear());          //prints: 2020
        System.out.println(lc5.getMonth());         //prints: FEBRUARY
        System.out.println(lc5.getMonthValue());    //prints: 2
        System.out.println(lc5.getDayOfMonth());    //prints: 23

        System.out.println(lc5.getDayOfWeek());     //prints: SUNDAY
        System.out.println(lc5.isLeapYear());       //prints: true
        System.out.println(lc5.lengthOfMonth());    //prints: 29
        System.out.println(lc5.lengthOfYear());     //prints: 366

        System.out.println(lc5.withYear(2021));     //prints: 2021-02-23
        System.out.println(lc5.withMonth(5));       //prints: 2020-05-23
        System.out.println(lc5.withDayOfMonth(5));  //prints: 2020-02-05
        System.out.println(lc5.withDayOfYear(53));  //prints: 2020-02-22

        System.out.println(lc5.plusDays(10));       //prints: 2020-03-04
        System.out.println(lc5.plusMonths(2));      //prints: 2020-04-23
        System.out.println(lc5.plusYears(2));       //prints: 2022-02-23

        System.out.println(lc5.minusDays(10));      //prints: 2020-02-13
        System.out.println(lc5.minusMonths(2));     //prints: 2019-12-23
        System.out.println(lc5.minusYears(2));      //prints: 2018-02-23

        LocalDate lc6 =  LocalDate.parse("2020-02-22");
        LocalDate lc7 =  LocalDate.parse("2020-02-23");
        System.out.println(lc6.isAfter(lc7));       //prints: false
        System.out.println(lc6.isBefore(lc7));      //prints: true

    }


}
