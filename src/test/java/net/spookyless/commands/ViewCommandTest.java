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
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewCommandTest {
    private Hotel hotel;
    private ViewCommand vc;

    @BeforeEach
    public void BeforeEach() {
        hotel = new Hotel(Arrays.asList(new Room("111", "Room 111", 111), new Room("222", "Room 222", 222), new Room("333", "Room 333", 333)));

        vc = new ViewCommand();
    }

    @Test
    public void ShouldShowDetailsOfEmptyRoom() {
        InputStream in = new ByteArrayInputStream("111".getBytes(StandardCharsets.UTF_8));
        OutputStream out = new ByteArrayOutputStream();
        PrintStream pr = new PrintStream(out);

        String expected =
                "Enter room number: Room number: 111" + System.lineSeparator() +
                        "Room floor: 1" + System.lineSeparator() +
                        "Description: Room 111" + System.lineSeparator() +
                        "Price: " + String.format("%.2f", 111.0f) + " PLN per night" + System.lineSeparator() +
                        "Guest: empty" + System.lineSeparator();

        assertThatNoException().isThrownBy(() -> vc.execute(in, pr, hotel));
        assertEquals(expected, out.toString());
    }

    @Test
    public void ShouldShowDetailsOfOccupiedRoom() throws RoomNotFoundException, RoomOccupiedException {
        InputStream in = new ByteArrayInputStream("111".getBytes(StandardCharsets.UTF_8));
        OutputStream out = new ByteArrayOutputStream();
        PrintStream pr = new PrintStream(out);

        hotel.checkIn("111", new Guest("Jan", "Nowak", "jan@nowak.com"));

        String expected =
                "Enter room number: Room number: 111" + System.lineSeparator() +
                        "Room floor: 1" + System.lineSeparator() +
                        "Description: Room 111" + System.lineSeparator() +
                        "Price: " + String.format("%.2f", 111.0f) + " PLN per night" + System.lineSeparator() +
                        "Guest: " + System.lineSeparator() +
                        ("First name: Jan" + System.lineSeparator()).indent(2) +
                        ("Last name: Nowak" + System.lineSeparator()).indent(2) +
                        ("Email: jan@nowak.com" + System.lineSeparator()).indent(2);

        assertThatNoException().isThrownBy(() -> vc.execute(in, pr, hotel));
        assertEquals(expected, out.toString());
    }
}
