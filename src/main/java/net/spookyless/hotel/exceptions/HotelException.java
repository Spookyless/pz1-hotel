package net.spookyless.hotel.exceptions;

public abstract class HotelException extends Exception {
    HotelException(String message) {
        super(message);
    }
}
