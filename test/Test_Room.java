import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class Test_Room {

    Room room;

    @Before
    public void setup() {
        room = new Room(100.0, "Double", 321, true);
    }

    @Test
    public void test_toString() {
        assertEquals("Room [price=100.0, roomType=Double, smokingAllowed=true, roomNumber=321]", room.toString());
    }

    @Test
    public void test_getPrice() {
        assertEquals(100.0, room.getPrice(), 0);
    }

    @Test
    public void test_getRoomType() {
        assertEquals("Double", room.getRoomType());
    }

    @Test
    public void test_getPhone() {
        assertEquals(321, room.getRoomNumber());
    }

    @Test
    public void test_isSmokingAllowed() {
        assertEquals(true, room.isSmokingAllowed());
    }

    @Test
    public void test_isEquals() {

        assertEquals(true, room.equals(room.copy()));

        Room tempRoom = room.copy();
        tempRoom.setPrice(0);
        assertEquals(false, room.equals(tempRoom));
    }
}
