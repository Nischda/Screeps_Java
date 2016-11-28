package org.fnord.screeps.api.roles;

import def.screeps.Creep;
import def.screeps.Structure;
import org.fnord.screeps.api.MyCreepCommander;

public class Repairer extends HiveMind {

    private Creep creep;
    private MyCreepCommander myCreepCommander;

    public Repairer(Creep creep, MyCreepCommander myCreepCommander) {
        this.creep = creep;
        this.myCreepCommander = myCreepCommander;
    }

    public void run() {
        isLoaded(creep);
        if((Boolean)creep.memory.$get("loaded")) {
            Structure[] repairStructures = myCreepCommander.getRepairStructures();
            repair(creep, repairStructures);
        }
        else {
            harvest(creep);
        }
    }
}
