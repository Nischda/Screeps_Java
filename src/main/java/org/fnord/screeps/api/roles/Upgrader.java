package org.fnord.screeps.api.roles;

import def.screeps.Creep;
import org.fnord.screeps.api.MyCreepCommander;

public class Upgrader extends HiveMind {

    private Creep creep;
    private MyCreepCommander myCreepCommander;

    public Upgrader(Creep creep, MyCreepCommander myCreepCommander) {
        this.creep = creep;
        this.myCreepCommander = myCreepCommander;
    }

    public void run() {
        isLoaded(creep);
        if((Boolean)creep.memory.$get("loaded")) {
            upgrade(creep);
        }
        else {
            harvest(creep);
        }
    }
}
