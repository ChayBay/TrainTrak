import java.util.*;


public class ConvertTime {


    static String convertTime(String str)
    {
        String hours, minutes;
        // Get Hours
        int h1 = (int)str.charAt(0) - '0';
        int h2 = (int)str.charAt(1)- '0';
        minutes = str.substring(3, 5);

        int hh = h1 * 10 + h2;

        // Finding out the Meridiem of time (AM/PM)
        String meridiem;
        if (hh < 12) {
            meridiem = "AM";
        }
        else
            meridiem = "PM";

        hh %= 12;

        //If 0 hours = 12
        if (hh == 0) {
            hours = "12";
        }
        else {
            hours = String.valueOf(hh);
        }

        String convertedTime = (hours + ":" + minutes + meridiem);

        return convertedTime;
    }

}
