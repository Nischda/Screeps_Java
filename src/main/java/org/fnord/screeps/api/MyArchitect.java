package org.fnord.screeps.api;

import def.screeps.Room;
import def.screeps.Spawn;
import def.screeps.Structure;
import def.screeps.Game;
import def.screeps.ConstructionSite;

import static def.screeps.Globals.FIND_CONSTRUCTION_SITES;
import static def.screeps.Globals.FIND_SOURCES;

public class MyArchitect {

    private Room room;
    public MyArchitect(Room room) {
        this.room = room;
    }

    public void createConstructionSites() {
        if((Boolean)room.$get("constructing")) {
            System.out.println("Architect: Planning ConstructionSites");

            MyBlueprints myBlueprints = new MyBlueprints(room);//add room to constructor
            Spawn spawn1 = (Spawn)Game.spawns.$get("Spawn1");

            myBlueprints.planStructuresAround(spawn1,3,3, "road");
            myBlueprints.planStructuresNextTo(spawn1, 5,5,"extension",2,99 );
            myBlueprints.planStructuresBetween(spawn1, room.controller, false, "road");
            for(Structure source : ((Structure[])room.find(FIND_SOURCES))) {
                myBlueprints.planStructuresAround( source, 1, 1, "road");
                myBlueprints.planStructuresBetween(spawn1, source, false, "road");

            }
            //ToDo add tower constructions ad make it add itself to memory
        }
    }
    public void removeConstructionSites(Room room) {
        ConstructionSite[] constructionSites = room.find(FIND_CONSTRUCTION_SITES);
        for (ConstructionSite constructionSite : constructionSites) {
            constructionSite.remove();
        }
    }
}
