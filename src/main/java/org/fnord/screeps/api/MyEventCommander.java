package org.fnord.screeps.api;
import def.screeps.Room;

public class MyEventCommander {

    private Room room;
    public MyEventCommander(Room room) {
        this.room = room;
    }

    public void checkRoom() {

        MyEvents myEvents = new MyEvents(room);
        myEvents.energyStorage();
        myEvents.isConstructing();
        myEvents.setCreepBodies();
    }
}
