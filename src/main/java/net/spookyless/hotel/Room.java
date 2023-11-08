package net.spookyless.hotel;

import net.spookyless.hotel.exceptions.RoomEmptyException;
import net.spookyless.hotel.exceptions.RoomOccupiedException;

public class Room {
    /**
     * Room number as {@code String} so we can keep the trailing zeros
     */
    private final String roomNumber;
    private final String description;
    private final float price;
    private Guest guest = null;

    public Room(String roomNumber, String description, float price) {
        this.roomNumber = roomNumber;
        this.description = description;
        this.price = price;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public int getFloorNumber() {
        return Integer.parseInt(roomNumber.substring(0,1));
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public Guest getGuest() {
        return guest;
    }

    public boolean isOccupied() {
        return guest != null;
    }

    public void checkIn(Guest guest) throws RoomOccupiedException {
        if(guest == null) {
            throw new IllegalArgumentException("Argument guest is null");
        }

        if(isOccupied()) {
            throw new RoomOccupiedException("Room " + roomNumber + " is occupied");
        }

        this.guest = guest;
    }

    public void checkOut() throws RoomEmptyException {
        if(!isOccupied()) {
            throw new RoomEmptyException("Room " + roomNumber + " is empty");
        }

        this.guest = null;
    }
}
