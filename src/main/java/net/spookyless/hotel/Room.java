package net.spookyless.hotel;

import net.spookyless.hotel.exceptions.RoomEmptyException;
import net.spookyless.hotel.exceptions.RoomOccupiedException;

public class Room {
    /**
     * Room number as {@code String} so we can keep the trailing zeros
     */
    public final String roomNumber;
    public final int roomFloor;
    public final String description;
    public final float price;
    private Guest guest = null;

    public Room(String roomNumber, String description, float price) {
        this.roomNumber = roomNumber;
        this.roomFloor = Integer.parseInt(roomNumber.substring(0,1));
        this.description = description;
        this.price = price;
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
            throw new RoomOccupiedException(roomNumber);
        }

        this.guest = guest;
    }

    public void checkOut() throws RoomEmptyException {
        if(!isOccupied()) {
            throw new RoomEmptyException(roomNumber);
        }

        this.guest = null;
    }
}
