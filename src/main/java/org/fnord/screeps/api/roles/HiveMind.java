package org.fnord.screeps.api.roles;

import def.screeps.Structure;
import def.screeps.Creep;
import def.screeps.ConstructionSite;
import def.screeps.Source;
import def.screeps.Game;


import static def.screeps.Globals.*;
import static def.screeps.Globals.ERR_NOT_IN_RANGE;

public class HiveMind {

    public static void isLoaded(Creep creep) {
     //   System.out.println(creep.name + creep.memory.$get("loaded"));
        if((Boolean)creep.memory.$get("loaded") && creep.carry.energy == 0) {
            creep.memory.$set("loaded", false);
        }
        if(!(Boolean)creep.memory.$get("loaded") && creep.carry.energy == creep.carryCapacity) {
            creep.memory.$set("loaded", true);
            creep.say("loaded");
        }
    }

    public static void harvest(Creep creep) {
        Source source = Game.getObjectById((String)creep.memory.$get("sourceID"));
        if (creep.harvest(source) == ERR_NOT_IN_RANGE) {
            creep.moveTo(source.pos);
        }
    }
    public static void harvest(Creep creep, int i) {
        Source[] sources = creep.room.find(FIND_SOURCES);
        if (creep.harvest(sources[i]) == ERR_NOT_IN_RANGE) {
            creep.moveTo(sources[i].pos);
        }
    }

    public void deliver(Creep creep, Structure energyStructure) {
        //System.out.println("HiveMind: " + energyStructure);
        if (energyStructure != null && creep.transfer(energyStructure, RESOURCE_ENERGY) == ERR_NOT_IN_RANGE) {
            creep.moveTo(energyStructure.pos);
        }
        else if (creep.upgradeController(creep.room.controller) == ERR_NOT_IN_RANGE) { //redundant code, somewhen split by making functions return boolean?
            creep.moveTo(creep.room.controller.pos);
        }
    }

    public static void upgrade(Creep creep) {
        if (creep.upgradeController(creep.room.controller) == ERR_NOT_IN_RANGE) {
            creep.moveTo(creep.room.controller.pos);
        }
    }

    public static void build(Creep creep, ConstructionSite[] constructionSite) {
        if(constructionSite.length>0) {
            if(creep.build(constructionSite[0]) == ERR_NOT_IN_RANGE) {
                creep.moveTo(constructionSite[0].pos);
            }
        }
        else if (creep.upgradeController(creep.room.controller) == ERR_NOT_IN_RANGE) { //redundant code, somewhen split by making functions return boolean?
            creep.moveTo(creep.room.controller.pos);
        }
    }
    public static void repair(Creep creep, Structure[] repairStructures) {
        if(repairStructures.length>0) {
            if(creep.repair(repairStructures[0]) == ERR_NOT_IN_RANGE) {
                creep.moveTo(repairStructures[0].pos);
            }
        }
        else if (creep.upgradeController(creep.room.controller) == ERR_NOT_IN_RANGE) { //redundant code, somewhen split by making functions return boolean?
            creep.moveTo(creep.room.controller.pos);
        }
    }
}
