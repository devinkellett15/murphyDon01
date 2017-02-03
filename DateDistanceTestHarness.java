//DateDistance.java

public class DateDistance {

    public static boolean isLeapYear ( long year ) {

        if (year % 4 != 0) {
            return false;
        } else if (year % 100 != 0) {
            return true;
        } else if (year % 400 != 0) {
            return false;
        } else {
            return true;
        }
    }

    public static long daysInMonth ( long month, long year ) {

        long days = 0;
        boolean isLeapYear = isLeapYear (year);
        switch ((int)month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11: days = 30;
                break;
            case 2:
                if (isLeapYear) {
                    days = 29;
                    } else {
                    days = 28;
                    }
                break;

            default: days = 0;
                break;
        }
        return days;
    }

    public static boolean isValidDate ( long day, long month, long year ) {

        if ( day < 1 || day > daysInMonth(month, year)) {
            System.out.println ("Not a valid day");
            return false;
        } else if (month >= 13 || month <= 0) {
            System.out.println ("Not a valid month");
            return false;
        } else if (year <= 0) {
            System.out.println ("Not a valid year");
            return false;
        } else {
            return true;
        }
    }

    public static long daysBetween ( long day0, long month0, long year0,
            long day1, long month1, long year1 ) {

        boolean firstDateIsBefore;
        long dayCounter = 0;

        if (year0 > year1 || (year0 == year1 && month0 > month1) || (year0 == year1 && month0 == month1 && day0 > day1)) {
            return daysBetween (day1, month1, year1, day0, month0, year0);
        }
        if (day0 == day1 && month0 == month1 && year0 == year1) {
            return dayCounter;
        } else {
            while (year0 != year1 || month0 != month1 || day0 != day1) {
                day0++;
                dayCounter++;
                if (day0 > daysInMonth(month0, year0)) {
                    month0++;
                    day0 = 1;
                }
                if (month0 > 12) {
                    year0++;
                    month0 = 1;
                }
            }
            return dayCounter;
        }
    }

    public static String dayOfTheWeek ( long day, long month, long year ) {

        long numberOfTheDay;
        String SUN = "Sunday";
        String MON = "Monday";
        String TUES = "Tuesday";
        String WED = "Wednesday";
        String THURS = "Thursday";
        String FRI = "Friday";
        String SAT = "Saturday";

        if (year< 2017) {
             numberOfTheDay = ( ( 7 - ( ( daysBetween(1, 1, 2017, day, month, year) ) %7 ) ) %7);
        } else if (year == 2017 && month == 1 && day == 1) {
            return SUN;
        } else {
             numberOfTheDay = ( ( daysBetween(1, 1, 2017, day, month, year) ) %7 );
        }
        if (numberOfTheDay == 0){
            return SUN;
        } else if (numberOfTheDay == 1){
            return MON;
        } else if (numberOfTheDay == 2){
            return TUES;
        } else if (numberOfTheDay == 3){
            return WED;
        } else if (numberOfTheDay == 4){
            return THURS;
        } else if (numberOfTheDay == 5){
            return FRI;
        } else if (numberOfTheDay == 6){
            return SAT;
        }
        return "";
    }

    public static String longformDate ( long day, long month, long year ) {

        String monthName = "";

        if (month == 1) {
            monthName = "January";
        } else if (month == 2) {
                monthName = "February";
        } else if (month == 3) {
                monthName = "March";
        } else if (month == 4) {
                monthName = "April";
        } else if (month == 5) {
                monthName = "May";
        } else if (month == 6) {
                monthName = "June";
        } else if (month == 7) {
                monthName = "July";
        } else if (month == 8) {
                monthName = "August";
        } else if (month == 9) {
                monthName = "September";
        } else if (month == 10) {
                monthName = "October";
        } else if (month == 11) {
                monthName = "November";
        } else if (month == 12) {
                monthName = "December";
        }
            return (dayOfTheWeek(day, month, year) + ", " + day + " " + monthName + " " + year);
    }

    public static void main ( String[] args ) {

        long day0 = 0;
        long month0 = 0;
        long year0 = 0;
        long day1 = 0;
        long month1 = 0;
        long year1 = 0;

        if (args.length == 6) {
            day0 = Integer.parseInt(args[0]);
            month0 = Integer.parseInt(args[1]);
            year0 = Integer.parseInt(args[2]);
            day1 = Integer.parseInt(args[3]);
            month1 = Integer.parseInt(args[4]);
            year1 = Integer.parseInt(args[5]);
            try {
                if (!isValidDate (day0, month0, year0)){
                    System.out.println ("Invalid date entered!");
                } else if (!isValidDate (day1, month1, year1)){
                    System.out.println ("Invalid date entered!");
                } else {
                    System.out.println ("There are " + (daysBetween ( day0, month0, year0,
                    day1, month1, year1 )) + " days between " + (longformDate (day0, month0, year0))
                    + " and " + (longformDate (day1 , month1, year1)) + ".");
                }
            } catch ( NumberFormatException nfe ) {
                System.out.println("Usage instructions: java DateDistance <day0> <month0> <year0> <day1> <month1> <year1>");
                }
        } else {
            System.out.println ("Usage instructions: java DateDistance <day0> <month0> <year0> <day1> <month1> <year1>");
        }
    }
}
