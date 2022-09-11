package DOA;

import Model.Entry;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntryRepository {
    Connection conn;

    MaplestoryClassRepository mcr;

    public EntryRepository() {
        conn = ConnectionUtil.getConnection();
        mcr = new MaplestoryClassRepository();
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
                int moneyEarned = rs.getInt("moneyEarned");
                int expEarned = rs.getInt("expEarned");
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
                int moneyEarned = rs.getInt("moneyEarned");
                int expEarned = rs.getInt("expEarned");
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
                int moneyEarned = rs.getInt("moneyEarned");
                int expEarned = rs.getInt("expEarned");
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
                // int entryID = rs.getInt("entryID");
                int classID = rs.getInt("classID");
                int mapID = rs.getInt("mapID");
                int moneyEarned = rs.getInt("moneyEarned");
                int expEarned = rs.getInt("expEarned");
                String videoURL = rs.getString("videoURL");
                Entry entry = new Entry(id, classID, mapID, moneyEarned, expEarned, videoURL);
                return entry;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addEntry(Entry newEntry) {
        try {
            PreparedStatement statement = conn.prepareStatement("insert into entries(entryID, classID, mapID, moneyEarned, expEarned, videoURL) values(?,?,?,?,?,?)");
            statement.setInt(1, newEntry.getEntryID());
            statement.setInt(2, newEntry.getClassID());
            statement.setInt(3, newEntry.getMapID());
            statement.setInt(4, newEntry.getMoney());
            statement.setInt(5, newEntry.getExp());
            statement.setString(6, newEntry.getVideoLink());
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
