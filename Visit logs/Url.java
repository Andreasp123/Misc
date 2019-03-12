

import java.util.ArrayList;
import java.util.Date;

public class Url {
    private String url;
    private ArrayList<Visit> visits = new ArrayList<>();
    private int pageViews = 0;
    private int uniqueVisitors = 0;

    /*
    For each URL there's a list of visits that contains information about the user and timestamp.
     */
    public Url(String url){
        this.url = url;
    }

    public void addVisit(Visit visit){
        visits.add(visit);
    }

    public String getUrl(){
        return url;
    }

    public int getViews(){
        return pageViews;
    }
    public int getVisitors(){
        return uniqueVisitors;
    }

    public void getVisits(Date startTime, Date endTime){
        ArrayList<String> visitors = new ArrayList<>();
        for(Visit visit : visits){
            if(visit.getTimestamp().compareTo(startTime) >= 0 && visit.getTimestamp().compareTo(endTime) <= 0){
                pageViews++;
                boolean found = false;
                for(String v : visitors){
                    if(v.equals(visit.getUserID())){
                        found = true;
                    }
                } if(!found){
                    visitors.add(visit.getUserID());
                    uniqueVisitors++;
                }
            }
        }
    }

    //Not the prettiest solution but it's just to make the print look better
    public void printUrlVisits(Date startTime, Date endTime){
        getVisits(startTime, endTime);
        String fixedUrl = "|" + url;
        String fixedPageViews = "|" + pageViews;
        String fixedVisitors = "|" + uniqueVisitors;
        while(fixedUrl.length() < 19){
            fixedUrl += " ";
        }
        while(fixedPageViews.length() < 12){
            fixedPageViews += " ";
        }
        while(fixedVisitors.length() < 10){
            fixedVisitors += " ";
        }
        System.out.println(fixedUrl + fixedPageViews + fixedVisitors + "|");
    }

}
