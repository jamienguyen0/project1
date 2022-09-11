package Model;

public class Map {
    private int mapID;
    private String mapName;

    public Map() {
    }

    public Map(int mapID, String mapName) {
        this.mapID = mapID;
        this.mapName = mapName;
    }

    public int getMapID() {
        return mapID;
    }

    public String getMapName() {
        return mapName;
    }

    @Override
    public String toString() {
        return "(" + this.mapID + ", " + this.mapName + ")";
    }
}
