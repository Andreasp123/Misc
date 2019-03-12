

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class TrackingReport {
    private ArrayList<Url> urls = new ArrayList<>();
    private String filePath;
    private String interval;
    private int year;
    private Date startTime;
    private Date endTime;

    public TrackingReport(){
        System.out.println("Wrong input format");
        System.exit(0);
    }
    public TrackingReport(String[] args){
        this.filePath = args[0];
        this.interval = args[1] + " " + args[2] + " - " + args[4];
    }

    /*
    Reads all the lines of visits in visitlog.txt. For each URL a new instance of the URL class is created.
    For each page visit a Visit instance is created.
     */
    public void readVisitLogs() throws FileNotFoundException, ParseException {
        File file = new File(filePath);
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            String visit = scan.nextLine();
            String[] logs = visit.split("\\|");
            String date = logs[1];
            Date timestamp = fixDate(date);
            String url = logs[2].replace(" ", "");
            String userId = logs[3];
            Visit v = new Visit(timestamp, userId);
            //If url is already in the URL list, add another visit. If not, add URL to list and then add visit
            if (containsUrl(url)) {
                addVisit(url, v);
            } else {
                Url u = new Url(url);
                urls.add(u);
                u.addVisit(v);
            }
        }
        scan.close();
    }

    private void getVisitLog() throws ParseException {
        try {
            String[] words = interval.split(" ");
            String year = words[0];
            startTime = fixDate(year + " " + words[1]);
            endTime = fixDate(year + " " + words[3]);
            System.out.println("|url" + "               " + "|page views " + "|visitors |");
            for (Url u : urls) {
                u.printUrlVisits(startTime, endTime);
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong input format, try again");
            getVisitLog();
        }
    }

    private boolean containsUrl(String url) {
        for (Url u : urls) {
            if (u.getUrl().equals(url)) {
                return true;
            }
        }
        return false;
    }

    private void addVisit(String url, Visit visit) {
        for (Url u : urls) {
            if (u.getUrl().equals(url)) {
                u.addVisit(visit);
            }
        }
    }



    private Date fixDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(date);
    }




    //Unit tests
    public int testUniqueVisitors(String url, String interval) throws ParseException {
        String[] words = interval.split(" ");
        String year = words[0];
        startTime = fixDate(year + " " + words[1]);
        endTime = fixDate(year + " " + words[3]);
        for (Url u : urls) {
            if (u.getUrl().equals(url)) {
                u.getVisits(startTime, endTime);
                return u.getVisitors();
            }
        }
        return 0;
    }

    public int testPageViews(String url, String interval) throws ParseException {
        String[] words = interval.split(" ");
        String year = words[0];
        startTime = fixDate(year + " " + words[1]);
        endTime = fixDate(year + " " + words[3]);
        for (Url u : urls) {
            if (u.getUrl().equals(url)) {
                u.getVisits(startTime, endTime);
                return u.getViews();
            }
        }
        return 0;
    }

    public boolean testContainsUrl(String url) {
        for (Url u : urls) {
            if (u.getUrl().equals(url)) {
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args) throws FileNotFoundException, ParseException {
        TrackingReport tr;
        if(args.length < 4){
            tr = new TrackingReport();
        }
        else {
            tr = new TrackingReport(args);
        }
        tr.readVisitLogs();
        tr.getVisitLog();
    }
}
