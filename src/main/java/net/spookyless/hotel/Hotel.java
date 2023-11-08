package net.spookyless.hotel;

import net.spookyless.hotel.exceptions.RoomEmptyException;
import net.spookyless.hotel.exceptions.RoomNotFoundException;
import net.spookyless.hotel.exceptions.RoomOccupiedException;

import java.util.List;

public class Hotel {
    public Hotel() {
    }

    public Hotel(List<Room> rooms) {
    }

    public List<String> getRoomNumbers() {
        return null;
    }

    public Room getRoomDetails(String roomNumber) throws RoomNotFoundException {
        return null;
    }

    public void checkIn(String roomNumber, Guest guest) throws RoomNotFoundException, RoomOccupiedException {
    }

    public void checkOut(String roomNumber) throws RoomNotFoundException, RoomEmptyException {
    }
}
