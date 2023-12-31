package net.spookyless.hotel;

import net.spookyless.hotel.exceptions.RoomEmptyException;
import net.spookyless.hotel.exceptions.RoomOccupiedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
    private Room room;
    private final Guest guest = new Guest("Jan", "Nowak", "jnowak@domain.com");

    @BeforeEach
    public void beforeEach() {
        room = new Room("042", "Lorem ipsum", 123.5f);
    }

    @Nested
    public class ShouldCheckIn {
        @Test
        public void WhenRoomIsEmpty() throws RoomOccupiedException {
            room.checkIn(guest);

            assertTrue(room.isOccupied());
            assertEquals(guest, room.getGuest());
        }

        @Test
        public void WhenRoomIsOccupied() throws RoomOccupiedException {
            room.checkIn(guest);

            assertThatExceptionOfType(RoomOccupiedException.class)
                .isThrownBy(() -> room.checkIn(guest));
        }

        @Test
        public void WhenGuestIsNull() {
            assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> room.checkIn(null));
        }
    }

    @Nested
    public class ShouldCheckOut {
        @Test
        public void WhenRoomIsEmpty() {
            assertThatExceptionOfType(RoomEmptyException.class)
                .isThrownBy(() -> room.checkOut());
        }

        @Test
        public void WhenRoomIsOccupied() throws RoomOccupiedException, RoomEmptyException {
            room.checkIn(guest);

            room.checkOut();

            assertFalse(room.isOccupied());
        }
    }
}
