package net.spookyless.hotel.exceptions;

public class RoomEmptyException extends HotelException {
    public RoomEmptyException(String roomNumber) {
        super("Room " + roomNumber + " is empty");
    }
}
