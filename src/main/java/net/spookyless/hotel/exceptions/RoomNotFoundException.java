package net.spookyless.hotel.exceptions;

public class RoomNotFoundException extends Exception{
    public RoomNotFoundException(String message) {
        super(message);
    }
}
