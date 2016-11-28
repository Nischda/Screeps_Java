package org.fnord.screeps.api;

import def.screeps.Creep;
import def.screeps.Game;
import def.screeps.Spawn;
import def.screeps.Structure;
import org.parakoopa.screeps.api.Mapper;

import static def.screeps.Globals.*;
import static jsweet.util.Globals.$map;
import static jsweet.util.Globals.union;

public class MySpawn {

    Spawn spawn1 = (Spawn)Game.spawns.$get("Spawn1");

    public void spawn() {

        Creep[] harvester = new Mapper<Creep>(Game.creeps).filter(
                (Creep creep) -> creep.memory.$get("role") == "harvester"
        );
        Creep[] upgrader = new Mapper<Creep>(Game.creeps).filter(
                (Creep creep) -> creep.memory.$get("role") == "upgrader"
        );
        Creep[] builder = new Mapper<Creep>(Game.creeps).filter(
                (Creep creep) -> creep.memory.$get("role") == "builder"
        );
        Creep[] repairer = new Mapper<Creep>(Game.creeps).filter(
                (Creep creep) -> creep.memory.$get("role") == "repairer"
        );


        if (harvester.length < 6) {
            System.out.println("Spawning harvester"); //useful?:  String newName = union(
            spawn1.createCreep(
                    new String[]{WORK, CARRY, MOVE},
                    null,
                    $map("role", "harvester", "sourceID", ((Structure)spawn1.room.find(FIND_SOURCES)[0]).id)
            );

        }
        else if (upgrader.length < 2) {
            System.out.println("Spawning upgrader");
            spawn1.createCreep(
                    new String[]{WORK, CARRY, MOVE},
                    null,
                    $map("role", "upgrader", "sourceID", ((Structure)spawn1.room.find(FIND_SOURCES)[1]).id) //sort by amount of sources and modulo
            );
        }
        else if (builder.length < 8) {
            System.out.println("Spawning builder");
            spawn1.createCreep(
                    new String[]{WORK, CARRY, MOVE},
                    null,
                    $map("role", "builder", "sourceID", ((Structure)spawn1.room.find(FIND_SOURCES)[1]).id) //sort by amount of sources and modulo
            );
        }
        else if (repairer.length < 2) {
            System.out.println("Spawning repairer");
            spawn1.createCreep(
                    new String[]{WORK, CARRY, MOVE},
                    null,
                    $map("role", "repairer", "sourceID", ((Structure)spawn1.room.find(FIND_SOURCES)[1]).id) //sort by amount of sources and modulo
            );
        }
    }
}
