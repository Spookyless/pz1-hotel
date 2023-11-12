package net.spookyless;

import net.spookyless.commands.*;
import net.spookyless.fs.Persist;
import net.spookyless.fs.PersistHotel;
import net.spookyless.hotel.Hotel;
import net.spookyless.hotel.Room;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Hotel hotel;

        PersistHotel ph = new PersistHotel("hoteldata.csv");

        try {
            hotel = ph.load();
        } catch (Exception e) {
            hotel = new Hotel(getHotelExampleRooms());
        }

        CommandRunner<Hotel> runner = getHotelCommandRunner(ph);
        runner.run(hotel);
    }

    private static CommandRunner<Hotel> getHotelCommandRunner(Persist<Hotel> persist) {
        List<Command<Hotel>> commands = Arrays.asList(
                new ListCommand("list", "lists all rooms in a hotel"),
                new ViewCommand("view", "prints room details"),
                new CheckInCommand("checkin", "checks in the guest"),
                new CheckOutCommand("checkout", "checks out the guest"),
                new SaveCommand("save", "saves current hotel configuration to file", persist),
                new ExitCommand("exit", "exits the program")
        );

        return new CommandRunner<>(System.in, System.out, commands);
    }

    public static List<Room> getHotelExampleRooms() {
        return Arrays.asList(
                new Room("011", "2 Twin Beds", 395),
                new Room("012", "1 King Bed", 395),
                new Room("013", "1 King Bed", 465),
                new Room("131", "2 Twin Beds", 495),
                new Room("132", "1 King Bed", 495),
                new Room("274", "1 King Bed", 470),
                new Room("275", "2 Twin Beds", 445)
        );
    }
}
