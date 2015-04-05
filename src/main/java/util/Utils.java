package util;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by abora on 4/4/15.
 */
public abstract class Utils {

    public static Timestamp getRenewdTimestamp(Timestamp end_date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(end_date);
        cal.add(Calendar.DAY_OF_WEEK, 150);
        end_date.setTime(cal.getTime().getTime());
        return new Timestamp(cal.getTime().getTime());
    }

    public static Long getCurrentTS(){
        return  Calendar.getInstance().getTimeInMillis();
    }

    public static void main(String[] args) {
        System.out.println(getCurrentTS());
    }

}
