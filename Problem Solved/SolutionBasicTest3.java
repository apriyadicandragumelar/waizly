
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


    public class SolutionBasicTest3 {
        public static String hours24(String input) throws ParseException {

        //format waktu date  
        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss aa"); //SimpleDateFormat("hh:mm:ss aa");

        //format waktu date
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date time = null;
        String output = "";

        time = dateFormat.parse(input);
        output = format.format(time);
        return output;

        }

        public static void main(String[] args) throws ParseException {

            //case 1
            System.out.println(hours24("07:05:45 PM"));

            //case 2
            System.out.println(hours24("11:00:00 PM"));

        }
}