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
            spawnCreep("harvester", null, new String[]{WORK, CARRY, MOVE}, 1 );
        }
        else if (upgrader.length < 2) {
            spawnCreep("upgrader", null, new String[]{WORK, CARRY, MOVE}, 1 );
        }
        else if (builder.length < 8) {
            spawnCreep("builder", null, new String[]{WORK, CARRY, MOVE}, 1 );
        }
        else if (repairer.length < 2) {
            spawnCreep("repairer", null, new String[]{WORK, CARRY, MOVE}, 1 );
        }
    }
    private void spawnCreep(String role, String name, String[] bodyparts, int sourceID) {
        System.out.println("Spawning " + role);
        spawn1.createCreep(
                bodyparts,
                null,
                $map("role", role, "sourceID", ((Structure)spawn1.room.find(FIND_SOURCES)[sourceID]).id) //sort by amount of sources and modulo
        );
    }
}
