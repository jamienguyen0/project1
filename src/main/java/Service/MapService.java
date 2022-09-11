package Service;

import DOA.MapRepository;
import Model.Map;

import java.util.List;

public class MapService {
    MapRepository mr;

    public MapService() {
        mr = new MapRepository();
    }

    public MapService(MapRepository mr) {
        this.mr = mr;
    }

    public List<Map> getAllMaps() {
        return mr.getAllMaps();
    }

    public String getMapNameFromID(int id) {
        return mr.getMapNameFromID(id);
    }

    public void addMap(int mapID, String mapName) {
        /* ID's should be unique so looking up a class by name rather than ID should eliminate duplicates
         * In a real world setting, this wouldn't completely eliminate all duplicate names because
         * while there are abbreviations that are generally agreed upon, some people just make up their own anyway.
         */
        // Map map = mr.getMapByID(mapID);
        Map map = mr.getMapByName(mapName);

        if (map == null) {
            Map newMap = new Map(mapID, mapName);
            mr.addMap(newMap);
        }
    }
}
