package net.spookyless.commands;

import net.spookyless.hotel.Guest;
import net.spookyless.hotel.Hotel;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CheckInCommand extends Command<Hotel> {
    public CheckInCommand() {
        super("checkin", "checks in the guest");
    }

    @Override
    public void execute(InputStream in, PrintStream pr, Hotel hotel) throws Exception {
        Scanner scanner = new Scanner(in);

        pr.print("Enter room number: ");
        String roomNumber = scanner.nextLine().trim();

        pr.print("Guest first name: ");
        String firstName = scanner.nextLine().trim();
        pr.print("Guest last name: ");
        String lastName = scanner.nextLine().trim();
        pr.print("Guest email address: ");
        String email = scanner.nextLine().trim();

        hotel.checkIn(roomNumber, new Guest(firstName, lastName, email));
    }
}
