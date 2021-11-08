package ua.com.alevel;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class OldDate {

    public void test() {
        Date date = new Date();
        System.out.println("date = " + date);

        Date dateMin = new Date(0L);
        System.out.println("date min = " + dateMin);

        Date dateMax = new Date(Long.MAX_VALUE);
        System.out.println("date max = " + dateMax);

        TimeZone timeZone = TimeZone.getDefault();
        System.out.println("timeZone = " + timeZone);

        Calendar calendar = Calendar.getInstance();
        System.out.println("calendar = " + calendar);

        System.out.println("calendar = " + calendar.getTime());
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.add(Calendar.DAY_OF_WEEK, -1);
        System.out.println("newCalendar = " + newCalendar.getTime());

        LocalTime localTime = LocalTime.now();
        System.out.println("localTime = " + localTime);

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime = " + localDateTime);

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        System.out.println("offsetDateTime = " + offsetDateTime);
    }
}
