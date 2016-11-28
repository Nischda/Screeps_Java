package org.fnord.screeps.api;


import def.screeps.Room;
import def.screeps.Structure;
import def.screeps.PathStep;
import def.screeps.FindPathOpts;

public class MyBlueprints {

    private Room room;
    public MyBlueprints(Room room) {
        this.room = room;
    }

    public void planRoads() {

    }

    public void planStructuresAround(Structure startStructure, int rangeX, int rangeY, String structureType) {
        Double coodX = startStructure.pos.x - rangeX;
        for(int x = 1; x < rangeX*2; x++) {
            coodX++;
            Double coodY = startStructure.pos.y - rangeY;
            for(int y = 1; y < rangeY*2; y++) {
                coodY++;
                room.createConstructionSite(coodX, coodY, structureType);
                //System.out.println("Room: " + room + " startStructure: " + startStructure + " CoodX: " + coodX + " CoodY: " + coodY + " StructureType: " + structureType);
            }
        }
    }
    public Boolean isSmartBuildingPosition(Structure startObject, double x, double y, int minDistance) {
            Boolean smart = true;
            smart = smart && Math.abs( startObject.pos.x - x ) >= minDistance;
            smart = smart && Math.abs( startObject.pos.y - y ) >= minDistance;
            smart = smart && ( !(Boolean)room.getPositionAt(x, y).lookFor("structure")[0]); //|| (RoomPosition)room.getPositionAt(x, y).lookFor("structure")[0].structureType != "road" );
            return smart;

    }
    public void planStructuresNextTo(Structure startStructure, int rangeX, int rangeY, String structureType, int minDistance_, int maxCreatedStructures) {
        int createdStructures = 0;

        double minX = startStructure.pos.x - rangeX;
     //   minX = minX + (minX % 2);
        double maxX = startStructure.pos.x + rangeX;
     //   maxX = maxX - (maxX % 2);
        double minY = startStructure.pos.y - rangeY;
    //    minY = minY + (minY % 2);
        double maxY = startStructure.pos.y + rangeY;
     //   maxY = maxY - (maxY % 2);
        for (double x = minX; x <= maxX; x = x + 2) {
            for (double y = minY; y <= maxY; y = y + 2) {
                if (this.isSmartBuildingPosition(startStructure, x, y, minDistance_)) {
            //        System.out.println("x: " + x + " y: " + y);
                    room.createConstructionSite(x, y, structureType);
                    createdStructures += 1;
                    if (createdStructures >= maxCreatedStructures) {
                        return;
                    }
                }
            }
        }
    }
    public void planStructuresBetween(Structure startStructure,Structure targetStructure, Boolean spread, String structureType) {
        FindPathOpts pathOpts = new FindPathOpts() {
            Boolean ignoreCreeps = true;
            Boolean ignoreRoads = true;
        };
     //   System.out.println(pathOpts);
        PathStep[] buildPath = room.findPath(startStructure.pos, targetStructure.pos, pathOpts); //()$map("ignoreCreeps", true, "ignoreRoads", true))).toArray();

        for(int j = 0; j < buildPath.length; j++) {
            if(!spread) {
                room.createConstructionSite(buildPath[j].x, buildPath[j].y, structureType);
            }
            else {
                for(int x = -1; x <= 1; x++) {
                    for(int y = -1; y <= 1; y++) {
                        if((Math.abs(x) +Math.abs(y)) < 2) {
                            room.createConstructionSite(buildPath[j].x + x, buildPath[j].y + y, structureType);
                        }
                    }
                }
            }
        }
    }
}
