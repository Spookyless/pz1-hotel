package net.spookyless;

import net.spookyless.commands.Command;
import net.spookyless.commands.CommandRunner;
import net.spookyless.commands.ExitCommand;
import net.spookyless.commands.ListCommand;
import net.spookyless.hotel.Hotel;
import net.spookyless.hotel.Room;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Room> rooms = Arrays.asList(
            new Room("011", "2 Twin Beds\nSettle into 24 square meters of space featuring two twin beds, seating area and a work desk. With your stay enjoy free Wi-Fi and fitness center access.", 395),
            new Room("012", "1 King Bed\nRest comfortably in 24 square meters of space featuring one king bed, seating area and a work desk. With your stay enjoy free Wi-Fi and fitness center access.", 395),
            new Room("013", "1 King Bed with Sofa Bed\nRest comfortably in 24 square meters of space featuring one king bed, a Cozy Corner with sofa-sleeper, and a work desk. With your stay enjoy free Wi-Fi and fitness center access.", 465),
            new Room("131", "2 Twin Beds Deluxe\nSettle into 24 square meters of space featuring two twin beds, welcome amenity, high floor, espresso machine, a work desk, and local gift. With your stay enjoy free Wi-Fi and fitness center access.", 495),
            new Room("132", "1 King Bed with Sofa Bed Deluxe\nFind your place in 24 square meters of space featuring one king bed, welcome amenity, high floor, espresso machine, a work desk, and local gift. With your stay enjoy free Wi-Fi and fitness center access.", 495),
            new Room("274", "1 King Bed Park View with Sofa Bed\nUnwind in 24 square meters of space featuring one king bed, sofa-sleeper, work desk and views overlooking Blonia meadow. With your stay enjoy free Wi-Fi and fitness center access.", 470),
            new Room("275", "2 Twin Beds Park View\nSettle into 24 square meters of space featuring two twin beds, a work desk and views overlooking Blonia meadow. With your stay enjoy free Wi-Fi and fitness center access.", 445)
        );

        Hotel hotel = new Hotel(rooms);

        List<Command<Hotel>> commands = Arrays.asList(
            new ListCommand("list", "lists all rooms in a hotel"),
            new ExitCommand("exit", "exits the program")
        );

        CommandRunner<Hotel> runner = new CommandRunner<>(System.in, System.out, commands);
        runner.run(hotel);
    }
}
