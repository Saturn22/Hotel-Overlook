import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class Test_Guest {

    Guest guest;

    @Before
    public void setup() {
        String fname = "Firstname", lname = "Lastname", address = "STL", phone = "1233121", nationality = "US";
        guest = new Guest(fname, lname, address, phone, new Date(), nationality);
    }

    @Test
    public void test_toString() {
        assertEquals("Firstname Lastname", guest.toString());
    }

    @Test
    public void test_getAddress() {
        assertEquals("STL", guest.getAddress());
    }

    @Test
    public void test_getPhone() {
        assertEquals("1233121", guest.getPhone());
    }

    @Test
    public void test_getNationality() {
        assertEquals("US", guest.getNationality());
    }
}
