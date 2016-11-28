package org.fnord.screeps.api.roles;

import def.screeps.ConstructionSite;
import def.screeps.Creep;
import org.fnord.screeps.api.MyCreepCommander;

public class Builder extends HiveMind {

    private Creep creep;
    private MyCreepCommander myCreepCommander;

    public Builder(Creep creep, MyCreepCommander myCreepCommander) {
        this.creep = creep;
        this.myCreepCommander = myCreepCommander;
    }

    public void run() {
        isLoaded(creep);
        if((Boolean)creep.memory.$get("loaded")) {
            ConstructionSite[] constructionSites = myCreepCommander.getConstructionSites();
            build(creep, constructionSites);
        }
        else {
            harvest(creep);
        }
    }
}
