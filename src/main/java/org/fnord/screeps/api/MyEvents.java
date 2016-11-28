package org.fnord.screeps.api;

import def.screeps.Spawn;
import def.screeps.Structure;
import def.screeps.Room;
import org.parakoopa.screeps.api.Helper;
import java.util.ArrayList;
import java.util.Arrays;

import static def.screeps.Globals.*;

public class MyEvents {

    private Room room;
    public MyEvents(Room room) {
        this.room = room;
    }

    public void energyStorage() { //make static?
        Structure[] structures = room.find(FIND_STRUCTURES, Helper.findFilter(
                (
                        def.screeps.Structure structure) ->
                        (structure.structureType == STRUCTURE_SPAWN
                                || structure.structureType == STRUCTURE_EXTENSION)
                                && ((Spawn) structure).energy < ((Spawn) structure).energyCapacity //Spawn is structure which owns energy function
        ));
        String[] structureIDs = new String[0];
        for(int i = 0; i < structures.length; i++) {
            structureIDs[i] = structures[i].id;
        }
        room.$set("structureIDs", structureIDs);
        // System.out.println("After setting Memory: " + room.$get("structureIDs"));
    }

    public void isConstructing(){
        if ((Integer)room.$get("savedControllerLevel") < room.controller.level) { //Make it build and update on every difference in levels
            room.$set("constructing", true);
            room.$set("savedControllerLevel", room.controller.level);

            System.out.println("savedControllerLevel: " + room.$get("savedControllerLevel"));
            System.out.println("room.controllerlevel: " + room.controller.level);
            System.out.println( (Double)room.$get("savedControllerLevel") != room.controller.level);
        }
        else {
            room.$set("constructing", false);
        }
    }
    public void setCreepBodies() {
        if (room.energyCapacityAvailable != ((Double)room.$get("energyCapacityLevel")) && room.controller.level < 5) {//recheck for right values and comparison
            System.out.println("ATTRIBUTES GET ADJUSTED");
            ArrayList<String> baseAttributes = new ArrayList<String>() {{add("move");add("work");add("carry");}};
            ArrayList<String> builderAttributes = new ArrayList<String>() {{add("move");add("work");add("carry");}};
            ArrayList<String> repairerAttributes = new ArrayList<String>() {{add("move");add("work");add("carry");}};
            ArrayList<String> harvesterAttributes = new ArrayList<String>() {{add("move");add("work");add("carry");}};
            ArrayList<String> haulerAttributes = new ArrayList<String>() {{add("move");add("work");add("carry");}};
            double energyCapacityLeft = room.energyCapacityAvailable - 200;

            while(energyCapacityLeft >= 200) {
                builderAttributes.addAll(baseAttributes);
                repairerAttributes.addAll(baseAttributes);
                harvesterAttributes.addAll(baseAttributes);
                haulerAttributes.addAll(Arrays.asList("move", "move", "carry", "carry"));
                energyCapacityLeft -= 200;
            }
            while(energyCapacityLeft >= 150) {
                builderAttributes.add("work");
                repairerAttributes.add("work");
                harvesterAttributes.add("work");
                haulerAttributes.add("move");
                energyCapacityLeft -= 100;
            }
            while(energyCapacityLeft >= 50) {
                builderAttributes.add("move");
                repairerAttributes.add("move");
                harvesterAttributes.add("carry");
                haulerAttributes.add("carry");
                energyCapacityLeft -= 50;
            }
            //       while(energyCapacityLeft >= 10) {
            //         upgraderAttributes.push('TOUGH')
            //         repairerAttributes.push('TOUGH')
            //         energyCapacityLeft -= 10
            //     }
            room.$set("builderAttributes", builderAttributes);
            room.$set("repairerAttributes", builderAttributes);
            room.$set("harvesterAttributes", builderAttributes);
            room.$set("haulerAttributes", builderAttributes);
/*
            room.memory.extensions = room.find(FIND_MY_STRUCTURES, {filter: { structureType: STRUCTURE_EXTENSION }});
            room.memory.extensionsCount = room.memory.extensions.length
            if (room.memory.extensionsCount = 'undefined') {
                room.memory.extensionsCount = 0

            };
            */
            room.$set("energyCapacityLevel", room.energyCapacityAvailable); //.memory.extensions.length * room.memory.extensions[0].energyCapacity + 300//room.energyCapacityAvailable
        }
    }
}
