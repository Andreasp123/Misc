

import java.util.Date;

public class Visit {

    private String userID;
    private Date timestamp;

    public Visit(Date timestamp, String userID){
        this.timestamp = timestamp;
        this.userID = userID;

    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getUserID(){
        return userID;
    }

}
