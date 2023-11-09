package net.spookyless.commands;

import net.spookyless.hotel.Hotel;

import java.io.InputStream;
import java.io.PrintStream;

public class ExitCommand extends Command<Hotel> {
    public ExitCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public void execute(InputStream in, PrintStream pr, Hotel params) throws Exception {
        throw new StopIterationException();
    }
}
