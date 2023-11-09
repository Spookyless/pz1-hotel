package net.spookyless.hotel.exceptions;

public class RoomOccupiedException extends HotelException {
    public RoomOccupiedException(String roomNumber) {
        super("Room " + roomNumber + " is occupied");
    }
}
