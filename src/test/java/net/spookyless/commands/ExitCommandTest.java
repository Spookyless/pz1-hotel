package net.spookyless.commands;

import net.spookyless.hotel.Hotel;
import net.spookyless.hotel.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ExitCommandTest {
    private Hotel hotel;
    private ExitCommand ec;

    @BeforeEach
    public void BeforeEach() {
        hotel = new Hotel(Arrays.asList(new Room("111", "Room 111", 111), new Room("222", "Room 222", 222), new Room("333", "Room 333", 333)));

        ec = new ExitCommand();
    }

    @Test
    public void ShouldExit() {
        InputStream in = new ByteArrayInputStream(new byte[0]);
        OutputStream out = new ByteArrayOutputStream();
        PrintStream pr = new PrintStream(out);

        assertThatExceptionOfType(StopIterationException.class)
                .isThrownBy(() -> ec.execute(in, pr, hotel));
    }
}
