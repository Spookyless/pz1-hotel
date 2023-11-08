package net.spookyless.hotel;

import net.spookyless.hotel.exceptions.RoomEmptyException;
import net.spookyless.hotel.exceptions.RoomOccupiedException;

public class Room {
    /**
     * Room number as {@code String} so we can keep the trailing zeros
     */
    private String roomNumber;
    private String description;
    private float price;
    private Guest guest = null;

    public Room(String roomNumber, String description, float price) {
    }

    public String getRoomNumber() {
        return null;
    }

    public int getFloorNumber() {
        return 0;
    }

    public String getDescription() {
        return null;
    }

    public float getPrice() {
        return 0;
    }

    public Guest getGuest() {
        return null;
    }

    public boolean isOccupied() {
        return false;
    }

    public void checkIn(Guest guest) throws RoomOccupiedException, IllegalArgumentException {
    }

    public void checkOut() throws RoomEmptyException {
    }
}
