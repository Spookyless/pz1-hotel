package net.spookyless.hotel.exceptions;

public class RoomNotFoundException extends HotelException {
    public RoomNotFoundException(String roomNumber) {
        super("Room " + roomNumber + " does not exist");
    }
}
