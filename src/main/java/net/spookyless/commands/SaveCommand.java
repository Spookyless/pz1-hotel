package net.spookyless.commands;

import net.spookyless.fs.Persist;
import net.spookyless.hotel.Hotel;

import java.io.InputStream;
import java.io.PrintStream;

public class SaveCommand extends Command<Hotel> {
    private final Persist<Hotel> persist;

    public SaveCommand(Persist<Hotel> persist) {
        super("save", "saves current hotel configuration to file");

        this.persist = persist;
    }

    @Override
    public void execute(InputStream in, PrintStream pr, Hotel hotel) throws Exception {
        persist.save(hotel);
    }
}
