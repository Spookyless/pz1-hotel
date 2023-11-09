package net.spookyless.commands;

import net.spookyless.hotel.Guest;
import net.spookyless.hotel.Hotel;
import net.spookyless.hotel.Room;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ViewCommand extends Command<Hotel> {
    public ViewCommand(String name, String description) {
        super(name, description);
    }

    @Override
    public void execute(InputStream in, PrintStream pr, Hotel hotel) throws Exception {
        Scanner scanner = new Scanner(in);

        pr.print("Enter room number: ");
        String roomNumber = scanner.nextLine().trim();

        Room room = hotel.getRoomDetails(roomNumber);

        pr.println("Room number: " + room.roomNumber);
        pr.println("Room floor: " + room.roomFloor);
        pr.print("Description:\n" + room.description.indent(2));
        pr.println("Price: " + String.format("%.2f", room.price) + " PLN per night");

        Guest guest = room.getGuest();

        pr.print("Guest: ");

        if(guest == null) {
            pr.println("empty");
            return;
        }

        pr.println();

        pr.print(("First name: " + guest.firstName()).indent(2));
        pr.print(("Last name: " + guest.lastName()).indent(2));
        pr.print(("Email: " + guest.email()).indent(2));
    }
}
