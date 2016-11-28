package org.fnord.screeps.api;

import def.screeps.Room;
import def.screeps.ConstructionSite;
import def.screeps.Creep;
import def.screeps.Structure;
import def.screeps.Game;
import jsweet.lang.Array;
import org.fnord.screeps.api.roles.Harvester;
import org.fnord.screeps.api.roles.Upgrader;
import org.fnord.screeps.api.roles.Builder;
import org.fnord.screeps.api.roles.Repairer;
import org.parakoopa.screeps.api.Helper;
import org.parakoopa.screeps.api.Mapper;

import static def.screeps.Globals.*;
import static def.screeps.Globals.STRUCTURE_EXTENSION;
import static def.screeps.Globals.STRUCTURE_SPAWN;

public class MyCreepCommander {

    private Room room;
    private Structure energyStructure;
    private Array<Creep> creeps;
    private ConstructionSite[] constructionSites;
    private Structure[] repairStructures;

    public MyCreepCommander(Room room) {
        this.room = room;
        this.creeps = new Mapper<Creep>(Game.creeps).toArray();

        this.setEnergyStructure();
        this.setConstructionSites();
        this.setRepairStructures();
    }

    private void setEnergyStructure() {
        String[] targetStructures = (String[])room.$get("structureIDs");
        Game.getObjectById(targetStructures[0]);
    }
    private void setConstructionSites() {
        this.constructionSites = room.find(FIND_CONSTRUCTION_SITES);
    }
    private void setRepairStructures() {
        Structure[] repairStructures = room.find(FIND_STRUCTURES, Helper.findFilter(
                (Structure structure) ->
                        (structure.structureType == STRUCTURE_ROAD
                                || structure.structureType == STRUCTURE_CONTAINER
                                || structure.structureType == STRUCTURE_EXTENSION
                                || structure.structureType == STRUCTURE_SPAWN)
                                &&  structure.hits < structure.hitsMax*0.9
        ));
        this.repairStructures = repairStructures;
    }

    public Structure getEnergyStructure() {
        System.out.println("MyCreepCommander.getEnergyStructure: " + energyStructure);
        return this.energyStructure;
    }
    public ConstructionSite[] getConstructionSites() {
        return constructionSites;
    }
    public Structure[] getRepairStructures() {
        return repairStructures;
    }

    public void command() {

        for(Creep creep : creeps) { //limit creeps by room
            if(creep.memory.$get("role") == "harvester") {
                new Harvester(creep, this).run();
            }
            else if(creep.memory.$get("role") == "upgrader") {
                 new Upgrader(creep, this).run();
            }
            else if(creep.memory.$get("role") == "builder") {
                new Builder(creep, this).run();
            }
            else if(creep.memory.$get("role") == "repairer") {
                new Repairer(creep, this).run();
            }
        }
    }
}
