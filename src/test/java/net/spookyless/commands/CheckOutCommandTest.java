package net.spookyless.commands;

import net.spookyless.hotel.Guest;
import net.spookyless.hotel.Hotel;
import net.spookyless.hotel.Room;
import net.spookyless.hotel.exceptions.RoomNotFoundException;
import net.spookyless.hotel.exceptions.RoomOccupiedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CheckOutCommandTest {
    private Hotel hotel;
    private CheckOutCommand coc;

    @BeforeEach
    public void BeforeEach() {
        hotel = new Hotel(Arrays.asList(new Room("111", "Room 111", 111), new Room("222", "Room 222", 222), new Room("333", "Room 333", 333)));

        coc = new CheckOutCommand();
    }

    @Test
    public void ShouldCheckOutGuest() throws RoomNotFoundException, RoomOccupiedException {
        InputStream in = new ByteArrayInputStream(("""
                111
                """).getBytes(StandardCharsets.UTF_8));
        OutputStream out = new ByteArrayOutputStream();
        PrintStream pr = new PrintStream(out);

        hotel.checkIn("111", new Guest("Jan", "Nowak", "jan@nowak.com"));

        assertThatNoException().isThrownBy(() -> coc.execute(in, pr, hotel));
        assertFalse(hotel.getRoomDetails("111").isOccupied());
    }
}
