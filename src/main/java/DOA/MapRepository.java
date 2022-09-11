package DOA;

import Model.Map;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MapRepository {
    Connection conn = ConnectionUtil.getConnection();

    public List<Map> getAllMaps() {
        List<Map> maps = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * from maps");

            while (rs.next()) {
                int mapID = rs.getInt("mapID");
                String mapName = rs.getString("mapName");
                Map newMap = new Map(mapID, mapName);
                maps.add(newMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maps;
    }

    public String getMapNameFromID(int id) {
        try {
            PreparedStatement statement = conn.prepareStatement("Select * from maps where mapID = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("mapName");
                return name;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public Map getMapByID(int id) {
        try {
            PreparedStatement statement = conn.prepareStatement("Select * from maps where mapID = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int mapID = rs.getInt("mapID");
                String mapName = rs.getString("mapName");
                Map map = new Map(mapID, mapName);
                return map;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Map getMapByName(String name) {
        try {
            PreparedStatement statement = conn.prepareStatement("Select * from maps where mapName = ?");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int mapID = rs.getInt("mapID");
                String mapName = rs.getString("mapName");
                Map map = new Map(mapID, mapName);
                return map;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addMap(Map m) {
        try {
            PreparedStatement statement = conn.prepareStatement("insert into maps(mapID, mapName) values(?,?)");
            statement.setInt(1, m.getMapID());
            statement.setString(2, m.getMapName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
