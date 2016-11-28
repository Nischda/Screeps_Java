package org.fnord.screeps.api.roles;

import def.screeps.Creep;
import def.screeps.Structure;
import org.fnord.screeps.api.MyCreepCommander;

public class Harvester extends HiveMind { //make interface?

    private Creep creep;
    private MyCreepCommander myCreepCommander;

    public Harvester(Creep creep, MyCreepCommander myCreepCommander) {
        this.creep = creep;
        this.myCreepCommander = myCreepCommander;
    }

    public void run() {
        isLoaded(this.creep);
        if((Boolean)creep.memory.$get("loaded")) {
            Structure energyStructure = myCreepCommander.getEnergyStructure();
            deliver(creep, energyStructure);
        }
        else {
            harvest(creep); //(Source)creep.memory.$get("source")
        }
    }
}
