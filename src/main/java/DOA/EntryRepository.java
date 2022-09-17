package DOA;

import Model.Entry;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntryRepository {
    Connection conn;

    MaplestoryClassRepository mcr;

    MapRepository mr;

    public EntryRepository() {
        conn = ConnectionUtil.getConnection();
        mcr = new MaplestoryClassRepository();
        mr = new MapRepository();
    }

    public List<Entry> getAllEntries() {
        List<Entry> allEntries = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * from entries");

            while (rs.next()) {
                int entryID = rs.getInt("entryID");
                int classID = rs.getInt("classID");
                int mapID = rs.getInt("mapID");
                double moneyEarned = rs.getInt("moneyEarned");
                double expEarned = rs.getInt("expEarned");
                String videoURL = rs.getString("videoURL");
                Entry newEntry = new Entry(entryID, classID, mapID, moneyEarned, expEarned, videoURL);
                allEntries.add(newEntry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allEntries;
    }

    public List<Entry> getAllEntriesByClassID(int classID) {
        List<Entry> entries = new ArrayList<>();

        try {
            PreparedStatement statement = conn.prepareStatement("Select * from entries where classID = ?");
            statement.setInt(1, classID);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int entryID = rs.getInt("entryID");
                int mapID = rs.getInt("mapID");
                double moneyEarned = rs.getInt("moneyEarned");
                double expEarned = rs.getInt("expEarned");
                String videoURL = rs.getString("videoURL");
                Entry newEntry = new Entry(entryID, classID, mapID, moneyEarned, expEarned, videoURL);
                entries.add(newEntry);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entries;
    }

    public List<Entry> getAllEntriesByMapID(int mapID) {
        List<Entry> entries = new ArrayList<>();

        try {
            PreparedStatement statement = conn.prepareStatement("Select * from entries where mapID = ?");
            statement.setInt(1, mapID);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int entryID = rs.getInt("entryID");
                int classID = rs.getInt("classID");
                double moneyEarned = rs.getInt("moneyEarned");
                double expEarned = rs.getInt("expEarned");
                String videoURL = rs.getString("videoURL");
                Entry newEntry = new Entry(entryID, classID, mapID, moneyEarned, expEarned, videoURL);
                entries.add(newEntry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entries;
    }

    public Entry getEntryByID(int id) {
        try {
            PreparedStatement statement = conn.prepareStatement("Select * from entries where entryID = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int entryID = rs.getInt("entryID");
                int classID = rs.getInt("classID");
                int mapID = rs.getInt("mapID");
                double moneyEarned = rs.getInt("moneyEarned");
                double expEarned = rs.getInt("expEarned");
                String videoURL = rs.getString("videoURL");
                Entry entry = new Entry(entryID, classID, mapID, moneyEarned, expEarned, videoURL);
                return entry;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addEntry(Entry newEntry) {
        try {
            PreparedStatement statement = conn.prepareStatement("insert into entries(classID, mapID, moneyEarned, expEarned, videoURL) values(?,?,?,?,?)");
            // statement.setInt(1, newEntry.getEntryID());
            statement.setInt(1, newEntry.getClassID());
            statement.setInt(2, newEntry.getMapID());
            statement.setDouble(3, newEntry.getMoney());
            statement.setDouble(4, newEntry.getExp());
            statement.setString(5, newEntry.getVideoLink());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEntry(int id) {
        try {
            PreparedStatement statement = conn.prepareStatement("delete from entries where entryID = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
