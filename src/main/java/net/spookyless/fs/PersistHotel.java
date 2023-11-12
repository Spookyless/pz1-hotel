package net.spookyless.fs;

import net.spookyless.hotel.Guest;
import net.spookyless.hotel.Hotel;
import net.spookyless.hotel.Room;
import net.spookyless.hotel.exceptions.RoomNotFoundException;
import net.spookyless.hotel.exceptions.RoomOccupiedException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersistHotel extends Persist<Hotel> {
    public PersistHotel(String filepath) {
        super(filepath);
    }

    @Override
    public void save(Hotel hotel) throws IOException, RoomNotFoundException {
        CSVPrinter printer = new CSVPrinter(new FileWriter(filepath), CSVFormat.DEFAULT);

        List<String> roomNumbers = hotel.getRoomNumbers();

        printer.printRecord("Room number", "Description", "Price", "Guest");

        for (String roomNumber : roomNumbers) {
            Room room = hotel.getRoomDetails(roomNumber);
            String guestJSON = "";

            if (room.isOccupied()) {
                Guest guest = room.getGuest();
                JSONObject guestData = new JSONObject();

                guestData.put("firstName", guest.firstName());
                guestData.put("lastName", guest.lastName());
                guestData.put("email", guest.email());

                guestJSON = guestData.toString();
            }

            printer.printRecord(roomNumber, room.description, room.price, guestJSON);
        }

        printer.close();
    }

    @Override
    public Hotel load() throws IOException, RoomOccupiedException {
        CSVParser parser = new CSVParser(new FileReader(filepath), CSVFormat.DEFAULT);
        List<CSVRecord> records = parser.getRecords();

        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < records.size(); i++) {
            // skip headers
            if (i == 0) continue;

            CSVRecord record = records.get(i);

            String roomNumber = record.get(0);
            String description = record.get(1);
            float price = Float.parseFloat(record.get(2));
            String guestInfo = record.get(3);

            Room room = new Room(roomNumber, description, price);

            if (guestInfo != null && !guestInfo.isEmpty()) {
                JSONObject guestData = new JSONObject(guestInfo);
                Guest guest = new Guest(
                        guestData.optString("firstName"),
                        guestData.optString("lastName"),
                        guestData.optString("email")
                );

                room.checkIn(guest);
            }

            rooms.add(room);
        }

        parser.close();

        return new Hotel(rooms);
    }
}
