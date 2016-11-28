package org.fnord.screeps.api;

import def.screeps.Game;
import def.screeps.Room;
import jsweet.lang.Array;
import org.parakoopa.screeps.api.Mapper;

public class MyStatus {

    private Room room;
    public MyStatus(Room room) {
        this.room = room;
    }

    public void statusReport() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("roomName: " + room.name + ", roomEnergy: " + room.energyAvailable);
    }
}
