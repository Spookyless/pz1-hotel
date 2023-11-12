package net.spookyless.commands;

import net.spookyless.hotel.Hotel;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class CheckOutCommand extends Command<Hotel> {
    public CheckOutCommand() {
        super("checkout", "checks out the guest");
    }

    @Override
    public void execute(InputStream in, PrintStream pr, Hotel hotel) throws Exception {
        Scanner scanner = new Scanner(in);

        pr.print("Enter room number: ");
        String roomNumber = scanner.nextLine().trim();

        hotel.checkOut(roomNumber);
    }
}
