package util;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by abora on 4/4/15.
 */
public abstract class Utils {

    public static Long getCurrentTS(){
        return  Calendar.getInstance().getTimeInMillis();
    }

    public static void main(String[] args) {
        System.out.println(getCurrentTS());
    }

}
