package net.spookyless.commands;

import net.spookyless.hotel.Hotel;
import net.spookyless.hotel.Room;
import net.spookyless.hotel.exceptions.RoomNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class CheckInCommandTest {
    private Hotel hotel;
    private CheckInCommand cic;

    @BeforeEach
    public void BeforeEach() {
        hotel = new Hotel(Arrays.asList(new Room("111", "Room 111", 111), new Room("222", "Room 222", 222), new Room("333", "Room 333", 333)));

        cic = new CheckInCommand();
    }

    @Test
    public void ShouldCheckInGuest() throws RoomNotFoundException {
        InputStream in = new ByteArrayInputStream(("""
                111
                Jan
                Nowak
                jan@nowak.com""").getBytes(StandardCharsets.UTF_8));
        OutputStream out = new ByteArrayOutputStream();
        PrintStream pr = new PrintStream(out);

        assertThatNoException().isThrownBy(() -> cic.execute(in, pr, hotel));
        assertTrue(hotel.getRoomDetails("111").isOccupied());
    }
}
