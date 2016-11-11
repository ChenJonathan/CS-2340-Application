package junit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import database.Model;
import model.WorkerReport;
import model.UserReport;
import model.Report;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Testing database methods.
 * @author alokpathy
 *
 */
public class ModelDatabaseTests {

    private Model instance;
    
    @Before
    public void setup() {
        instance = Model.instance();
    }
    
    @Test
    public void testGetReportsByLocationNone() {
        ObservableList<Report> expected = FXCollections.observableArrayList();
        
        int numLists = 3;
        ObservableList<Report>[] actualLists = new ObservableList[numLists];
        for (int i = 0; i < actualLists.length; i++) {
            actualLists[i] = FXCollections.observableArrayList();
        }
        
        actualLists[0] = instance.getReportsByLocation(-1, -1, 0);
        actualLists[1] = instance.getReportsByLocation(1, 0, 0);
        actualLists[2] = instance.getReportsByLocation(8, 9, 4);

        
        for (int i = 0; i < actualLists.length; i++) {
            assertEquals(expected, actualLists[i]);
        }
    }

    @Test
    public void testGetReportsByLocationOneWorker() {

        int numTests = 1;
        ObservableList<Report>[] expectedLists = new ObservableList[numTests];
        ObservableList<Report>[] actualLists = new ObservableList[numTests];
        for (int i = 0; i < expectedLists.length; i++) {
            expectedLists[i] = FXCollections.observableArrayList();
            actualLists[i] = FXCollections.observableArrayList();
        }
        
        expectedLists[0].add(new WorkerReport("7418234", 0, 0, "ew", 
                                    "11/3/2016 19:44.23", "a", "Spring", "Waste", 50, 2));
        actualLists[0] = instance.getReportsByLocation(0, 0, 0);
        
        for (int i = 0; i < expectedLists.length; i++) {
            System.out.println(expectedLists[i].size() + " " + actualLists[i].size());
            assertSame(expectedLists[i].size(), actualLists[i].size());
            for (int j = 0; j < expectedLists[i].size(); j++) {
                Report expectedReport = expectedLists[i].get(j);
                Report actualReport = actualLists[i].get(j);
                assertTrue(expectedReport instanceof WorkerReport);
                assertEquals(expectedLists[i].get(j).toString(), actualLists[i].get(j)
                                                                .toString());
            }
        }

    }
}
