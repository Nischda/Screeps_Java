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
                                || structure.structureType == STRUCTURE_TOWER
                                || structure.structureType == STRUCTURE_EXTENSION)
                                && ((Spawn) structure).energy < ((Spawn) structure).energyCapacity //Spawn is structure which owns energy function
        ));
        String[] structureIDs = new String[0];
        for(int i = 0; i < structures.length; i++) {
            structureIDs[i] = structures[i].id;
        }
        room.memory.$set("structureIDs", structureIDs);
      //   System.out.println("After setting Memory: " + room.memory.$get("structureIDs"));
    }

    public void isConstructing(){
        if ((Integer)room.$get("savedControllerLevel") < room.controller.level) { //Make it build and update on every difference in levels
            room.memory.$set("constructing", true);
            room.memory.$set("savedControllerLevel", room.controller.level);

            System.out.println("savedControllerLevel: " + room.memory.$get("savedControllerLevel"));
            System.out.println("room.controllerlevel: " + room.controller.level);
            System.out.println( (Double)room.memory.$get("savedControllerLevel") != room.controller.level);
        }
        else {
            room.memory.$set("constructing", false);
        }
    }

    public void setCreepBodies() {

        //System.out.println("energyCapacityAvailable: " + room.energyCapacityAvailable);
        //System.out.println("Get energyCapacityLevel: " + room.memory.$get("energyCapacityLevel"));

        if (room.energyCapacityAvailable != (Double) room.memory.$get("energyCapacityLevel")) {// ToDo: add limit,    //&& room.controller.level < 5
            System.out.println("ATTRIBUTES GET ADJUSTED");
            ArrayList<String> baseAttributes = new ArrayList<String>(); //ToDo Add multiple at once
            baseAttributes.add("move");
            baseAttributes.add("work");
            baseAttributes.add("carry");
            ArrayList<String> builderAttributes = new ArrayList<String>();
            builderAttributes.add("move");
            builderAttributes.add("work");
            builderAttributes.add("carry");
            ArrayList<String> repairerAttributes = new ArrayList<String>();
            repairerAttributes.add("move");
            repairerAttributes.add("work");
            repairerAttributes.add("carry");
            ArrayList<String> harvesterAttributes = new ArrayList<String>();
            harvesterAttributes.add("move");
            harvesterAttributes.add("work");
            harvesterAttributes.add("carry");
            ArrayList<String> haulerAttributes = new ArrayList<String>();
            haulerAttributes.add("move");
            haulerAttributes.add("work");
            haulerAttributes.add("carry");
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
            room.memory.$set("builderAttributes", builderAttributes);
            room.memory.$set("repairerAttributes", repairerAttributes);
            room.memory.$set("harvesterAttributes", harvesterAttributes);
            room.memory.$set("haulerAttributes", haulerAttributes);
/*
            room.memory.extensions = room.find(FIND_MY_STRUCTURES, {filter: { structureType: STRUCTURE_EXTENSION }});
            room.memory.extensionsCount = room.memory.extensions.length;
            if (room.memory.extensionsCount == 'undefined') {
                room.memory.extensionsCount = 0

            };
*/
            //
            //Spawn spawn1 = (Spawn) Game.spawns.$get("Spawn1");
            //move into spawn memory
            room.memory.$set("energyCapacityLevel", room.energyCapacityAvailable); //.memory.extensions.length * room.memory.extensions[0].energyCapacity + 300//room.energyCapacityAvailable
        }
    }
}
