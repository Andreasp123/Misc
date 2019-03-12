
import org.junit.*;
import java.io.FileNotFoundException;
import java.text.ParseException;

import static org.junit.Assert.*;

public class TestClass {
    TrackingReport tr = new TrackingReport();

    @Before
    public void setUp() throws FileNotFoundException, ParseException {
        tr.readVisitLogs();
    }
    @Test
    public void testPageViews() throws ParseException {
        assertEquals(13, tr.testPageViews("/contact.html", "2019-03-09 09:00:00 - 18:59:59"));
    }

    @Test
    public void testUniqueVisitors() throws ParseException {
        assertEquals(6, tr.testUniqueVisitors("/api", "2019-03-09 10:00:00 - 13:59:59"));
    }

    @Test
    public void testContainsUrl(){
        assertTrue(tr.testContainsUrl("/api"));
    }
}
