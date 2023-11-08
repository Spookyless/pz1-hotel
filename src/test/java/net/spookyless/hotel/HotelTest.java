package net.spookyless.hotel;

import net.spookyless.hotel.exceptions.RoomEmptyException;
import net.spookyless.hotel.exceptions.RoomNotFoundException;
import net.spookyless.hotel.exceptions.RoomOccupiedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class HotelTest {
    private Hotel hotel = new Hotel();
    private final List<Room> rooms = Arrays.asList(
        new Room("001", "Lorem", 1f),
        new Room("014", "ipsum", 14f),
        new Room("231", "dolor", 231f)
    );
    private final Guest guest = new Guest("Jan", "Nowak", "jnowak@domain.com");

    @BeforeEach
    public void beforeEach() {
        hotel = new Hotel(rooms);
    }

    @Test
    public void shouldReturnRoomNumbers() {
        List<String> roomNumbers = Arrays.asList("001", "014", "231");

        assertEquals(roomNumbers, hotel.getRoomNumbers());
    }

    @Nested
    public class shouldReturnRoomDetails {
        @Test
        public void whenRoomExists() throws RoomNotFoundException {
            Room room = hotel.getRoomDetails("014");

            assertEquals(rooms.get(1), room);
        }

        @Test
        public void whenRoomDoesNotExist() {
            assertThatExceptionOfType(RoomNotFoundException.class)
                .isThrownBy(() -> hotel.getRoomDetails("does not exist"));
        }
    }

    @Nested
    public class shouldCheckInGuests {
        @Test
        public void whenRoomIsEmpty() throws RoomNotFoundException, RoomOccupiedException {
            hotel.checkIn("001", guest);

            assertTrue(hotel.getRoomDetails("001").isOccupied());
        }

        @Test
        public void whenRoomIsOccupied() throws RoomNotFoundException, RoomOccupiedException {
            hotel.checkIn("001", guest);

            assertThatExceptionOfType(RoomOccupiedException.class)
                .isThrownBy(() -> hotel.checkIn("001", guest));
        }

        @Test
        public void whenRoomDoesNotExist() {
            assertThatExceptionOfType(RoomNotFoundException.class)
                .isThrownBy(() -> hotel.checkIn("does not exist", guest));
        }
    }

    @Nested
    public class shouldCheckOutGuests {
        @Test
        public void whenRoomIsEmpty() {
            assertThatExceptionOfType(RoomEmptyException.class)
                .isThrownBy(() -> hotel.checkOut("001"));
        }

        @Test
        public void whenRoomIsOccupied() throws RoomNotFoundException, RoomOccupiedException, RoomEmptyException {
            hotel.checkIn("001", guest);
            hotel.checkOut("001");

            assertFalse(hotel.getRoomDetails("001").isOccupied());
        }

        @Test
        public void whenRoomDoesNotExist() {
            assertThatExceptionOfType(RoomNotFoundException.class)
                .isThrownBy(() -> hotel.checkOut("does not exist"));
        }
    }
}
