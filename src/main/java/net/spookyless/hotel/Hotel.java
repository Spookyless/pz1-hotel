package net.spookyless.hotel;

import net.spookyless.hotel.exceptions.RoomEmptyException;
import net.spookyless.hotel.exceptions.RoomNotFoundException;
import net.spookyless.hotel.exceptions.RoomOccupiedException;
import net.spookyless.util.MyMap;

import java.util.List;

public class Hotel {
    private final MyMap<String, Room> rooms = new MyMap<>();

    public Hotel() {}

    public Hotel(List<Room> rooms) {
        for (Room room: rooms) {
            this.rooms.put(room.getRoomNumber(), room);
        }
    }

    public List<String> getRoomNumbers() {
        return rooms.keys();
    }

    public Room getRoomDetails(String roomNumber) throws RoomNotFoundException {
        if(!rooms.contains(roomNumber)) {
            throw new RoomNotFoundException("Room " + roomNumber + " does not exist");
        }

        return rooms.get(roomNumber);
    }

    public void checkIn(String roomNumber, Guest guest) throws RoomNotFoundException, RoomOccupiedException {
        getRoomDetails(roomNumber).checkIn(guest);
    }

    public void checkOut(String roomNumber) throws RoomNotFoundException, RoomEmptyException {
        getRoomDetails(roomNumber).checkOut();
    }
}
