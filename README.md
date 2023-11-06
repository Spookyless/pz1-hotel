# pz1-hotel

[![Build Status](https://drone.spookyless.net/api/badges/Spookyless/pz1-hotel/status.svg)](https://drone.spookyless.net/Spookyless/pz1-hotel)
[![Quality Gate Status](https://sonar.spookyless.net/api/project_badges/measure?project=Spookyless_pz1-hotel_AYumlzOy6v4EdcFC8vRC&metric=alert_status)](https://sonar.spookyless.net/dashboard?id=Spookyless_pz1-hotel_AYumlzOy6v4EdcFC8vRC)
[![Coverage](https://sonar.spookyless.net/api/project_badges/measure?project=Spookyless_pz1-hotel_AYumlzOy6v4EdcFC8vRC&metric=coverage)](https://sonar.spookyless.net/dashboard?id=Spookyless_pz1-hotel_AYumlzOy6v4EdcFC8vRC)

Project created for [Programowanie Zaawansowane 1](https://sylabusy.agh.edu.pl/en/1/2/18/1/4/16/140) course at AGH University of Krakow.

## Project description

### Hotel management
Create a simple CLI program that manages a hotel. Rooms are numbered using whole numbers, where the first digit signifies the floor of the room (i.e. room number 101 is located on the first floor).

Program should allow to use the following commands (case-insensitive):
- `list` - lists all the rooms
- `view` - prompts for room number, then displays information about the room and the guest, if any is staying there. If the room doesn't exist shows an error
- `checkin` - prompts for room number, then asks for guest's details and assigns him to the room. If the room is occupied or not valid, shows an error
- `checkout` - reverse of `checkin`, prompts for room number and removes the quest. If the room is not occupied or not valid, shows an error
- (bonus) `save` - saves current state to CSV/XLSX file
- `exit` - self-explanatory

### Implementation of Map
You are also required to write your own implementation of a generic class `MyMap`, which implements the `Map` interface with (at least) these methods:
```Java
public interface Map<K, V> {
    boolean put(K key, V value);
    boolean remove(K key);
    V get(K key);
    List<K> keys();
}
```


You can use the following classes to implement it: `java.util.List`, `java.util.LinkedList`, `java.util.ArrayList`.
Your implementation should be based on two lists: keys and values.

### Bonus
The initial configuration of the hotel should be read from a CSV/XLSX file with the following structure:

| room number | description | price | (optional) guest |
|-------------|-------------|-------|------------------|
