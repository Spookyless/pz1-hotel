package net.spookyless.commands;

import net.spookyless.hotel.Hotel;
import net.spookyless.hotel.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    private Hotel hotel;
    private ListCommand lc;

    @BeforeEach
    public void BeforeEach() {
        hotel = new Hotel(Arrays.asList(new Room("111", "Room 111", 111), new Room("222", "Room 222", 222), new Room("333", "Room 333", 333)));

        lc = new ListCommand();
    }

    @Test
    public void ShouldListRoomNumbers() {
        InputStream in = new ByteArrayInputStream(new byte[0]);
        OutputStream out = new ByteArrayOutputStream();
        PrintStream pr = new PrintStream(out);

        String expected = "111, 222, 333" + System.lineSeparator();

        assertThatNoException().isThrownBy(() -> lc.execute(in, pr, hotel));
        assertEquals(expected, out.toString());
    }
}
