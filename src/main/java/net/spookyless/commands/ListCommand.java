package net.spookyless.commands;

import net.spookyless.hotel.Hotel;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

public class ListCommand extends Command<Hotel> {
    public ListCommand() {
        super("list", "lists all rooms in a hotel");
    }

    @Override
    public void execute(InputStream in, PrintStream pr, Hotel hotel) {
        List<String> roomsNumbers = hotel.getRoomNumbers();
        pr.println(String.join(", ", roomsNumbers));
    }
}
