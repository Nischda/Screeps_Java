package org.fnord.screeps.api;
import def.screeps.Creep;
import def.screeps.StructureTower;
public class MyTower {

    private StructureTower tower;

    public MyTower(StructureTower tower) {
        this.tower = tower;
    }

    public void run() {
        Creep closestHostile = tower.pos.findClosestByRange(3); // 3 = Hostile_Creeps
        if(closestHostile != null) {
            tower.attack(closestHostile);
        }
    }
}
