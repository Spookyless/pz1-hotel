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

    @Test
    public void shouldReturnRoomNumber() {
        assertEquals("042", room.getRoomNumber());
    }

    @Test
    public void shouldReturnFloorNumber() {
        assertEquals(0, room.getFloorNumber());
    }

    @Test
    public void shouldReturnDescription() {
        assertEquals("Lorem ipsum", room.getDescription());
    }

    @Test
    public void shouldReturnPrice() {
        assertEquals(123.5f, room.getPrice());
    }

    @Test
    public void shouldReturnGuest() throws RoomOccupiedException {
        room.checkIn(guest);

        assertEquals(guest, room.getGuest());
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
        public void WhenGuestIsNull() throws RoomOccupiedException {
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
