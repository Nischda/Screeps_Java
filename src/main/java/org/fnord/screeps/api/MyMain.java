package org.fnord.screeps.api;

import def.screeps.Game;
import def.screeps.Room;
import jsweet.lang.Array;
import org.parakoopa.screeps.api.Mapper;

public class MyMain {

    public static void loop() {

        Array<Room> rooms = new Mapper<Room>(Game.rooms).toArray();
        for (Room room : rooms) {

            new MyGraveyard().buryCorpses();    //Remove Memory of dead creeps
            new MyEventCommander(room).checkRoom();
            new MyStatus(room).statusReport();      //Print available Data
            new MySpawn(room).spawn();              //Spawn creep population
            new MyCreepCommander(room).command();   //Delivers tasks to creeps
            new MyArchitect(room).createConstructionSites();
            //new MyArchitect(room).removeConstructionSites();
        }
    }
}
