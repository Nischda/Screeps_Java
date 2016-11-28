package org.fnord.screeps.api;

import def.screeps.Game;
import def.screeps.Memory;
import org.parakoopa.screeps.api.Mapper;

public class MyGraveyard {

    public void buryCorpses() {
        String[] creepNames = new Mapper(Memory.creeps).getKeys();
        for (String creepName : creepNames) {
            if (Game.creeps.$get(creepName) == null) {
                Memory.creeps.$delete(creepName);
            }
        }
    }
}
