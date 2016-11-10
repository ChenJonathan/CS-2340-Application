package junit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import database.Model;
import model.WorkerReport;
import model.UserReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Testing database authenticateUser methods.
 * @author Wesley Cheung
 *
 */
public class ModelAuthenticateUserTest {

    private Model instance;

    @Before
    public void setup() {
        instance = Model.instance();
    }

    @Test
    public void testAuthenticateUserValid() {

        ArrayList<String[]> super_secret_buffered_sha1024_mega_quintuple_AES_encrypted_credentials = new ArrayList<String[]>();
        String[] user = {"test", "test"};
        String[] worker = {"asdf", "asdf"};
        String[] manager = {"a", "a"};
        super_secret_buffered_sha1024_mega_quintuple_AES_encrypted_credentials.add(user);
        super_secret_buffered_sha1024_mega_quintuple_AES_encrypted_credentials.add(worker);
        super_secret_buffered_sha1024_mega_quintuple_AES_encrypted_credentials.add(manager);

        for (String[] u : super_secret_buffered_sha1024_mega_quintuple_AES_encrypted_credentials) {

            assertTrue(instance.authenticateUser(u[0], u[1]));

        }
    }

    @Test
    public void testAuthenticateUserInvalid() {

        ArrayList<String[]> super_secret_buffered_sha1024_mega_quintuple_AES_encrypted_credentials = new ArrayList<String[]>();
        String[] empty = {"", ""};
        String[] BUFFER_OVERFLOW_xDDDDDDDDDDD = {
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"};
        String[] NEGATIVE_STRINGSkappa = {"-9000", "-1"};
        String[] leethaxor = {"<script>Window.alert(1)</script>", "' or 1=1;+"};
        super_secret_buffered_sha1024_mega_quintuple_AES_encrypted_credentials.add(empty);
        super_secret_buffered_sha1024_mega_quintuple_AES_encrypted_credentials.add(BUFFER_OVERFLOW_xDDDDDDDDDDD);
        super_secret_buffered_sha1024_mega_quintuple_AES_encrypted_credentials.add(NEGATIVE_STRINGSkappa);
        super_secret_buffered_sha1024_mega_quintuple_AES_encrypted_credentials.add(leethaxor);

        for (String[] u : super_secret_buffered_sha1024_mega_quintuple_AES_encrypted_credentials) {
            assertFalse(instance.authenticateUser(u[0], u[1]));
        }

    }
}
