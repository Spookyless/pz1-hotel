package net.spookyless.commands;

import net.spookyless.hotel.Hotel;
import net.spookyless.hotel.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandRunnerTest {
    private Hotel hotel;

    private final List<Command<Hotel>> commands = Arrays.asList(
            new ListCommand(),
            new ViewCommand(),
            new ExitCommand()
    );

    @BeforeEach
    public void BeforeEach() {
        hotel = new Hotel(Arrays.asList(new Room("111", "Room 111", 111), new Room("222", "Room 222", 222), new Room("333", "Room 333", 333)));
    }

    @Test
    public void ShouldReactToEmptyLines() {
        InputStream in = new ByteArrayInputStream((System.lineSeparator() + "exit").getBytes(StandardCharsets.UTF_8));
        OutputStream out = new ByteArrayOutputStream();
        PrintStream pr = new PrintStream(out);

        CommandRunner<Hotel> runner = new CommandRunner<>(in, pr, commands);

        String expected = "$ $";

        runner.run(hotel);

        assertTrue(out.toString().contains(expected));
    }

    @Test
    public void ShouldReactToHelpQuery() {
        InputStream in = new ByteArrayInputStream(("?" + System.lineSeparator() + "exit").getBytes(StandardCharsets.UTF_8));
        OutputStream out = new ByteArrayOutputStream();
        PrintStream pr = new PrintStream(out);

        CommandRunner<Hotel> runner = new CommandRunner<>(in, pr, commands);

        String expected = "list" + System.lineSeparator();

        runner.run(hotel);

        assertTrue(out.toString().contains(expected));
    }

    @Test
    public void ShouldReactToExitCommand() {
        InputStream in = new ByteArrayInputStream(("exit").getBytes(StandardCharsets.UTF_8));
        OutputStream out = new ByteArrayOutputStream();
        PrintStream pr = new PrintStream(out);

        CommandRunner<Hotel> runner = new CommandRunner<>(in, pr, commands);

        assertThatNoException().isThrownBy(() -> runner.run(hotel));
    }

    @Test
    public void ShouldPrintErrors() {
        InputStream in = new ByteArrayInputStream(("view" + System.lineSeparator() + "does not exist" + System.lineSeparator() + "exit").getBytes(StandardCharsets.UTF_8));
        OutputStream out = new ByteArrayOutputStream();
        PrintStream pr = new PrintStream(out);

        CommandRunner<Hotel> runner = new CommandRunner<>(in, pr, commands);

        runner.run(hotel);

        assertTrue(out.toString().contains("[Error]"));
    }

    @Test
    public void ShouldReactToUnknownCommands() {
        InputStream in = new ByteArrayInputStream(("unknown" + System.lineSeparator() + "exit").getBytes(StandardCharsets.UTF_8));
        OutputStream out = new ByteArrayOutputStream();
        PrintStream pr = new PrintStream(out);

        CommandRunner<Hotel> runner = new CommandRunner<>(in, pr, commands);

        runner.run(hotel);

        assertTrue(out.toString().contains("Unknown command"));
    }
}
