package DOA;

import Model.Map;
import Model.MaplestoryClass;
import Util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaplestoryClassRepository {
    Connection conn = ConnectionUtil.getConnection();

    public List<MaplestoryClass> getAllClasses() {
        List<MaplestoryClass> classes = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * from msclasses");

            while (rs.next()) {
                int classID = rs.getInt("classID");
                String className = rs.getString("className");
                MaplestoryClass newClass = new MaplestoryClass(classID, className);
                classes.add(newClass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classes;
    }

    public String getClassNameFromID(int id) {
        try {
            PreparedStatement statement = conn.prepareStatement("Select * from msclasses where classID = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("className");
                return name;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    public int getClassIDFromName(String name) {
        try {
            PreparedStatement statement = conn.prepareStatement("Select * from msclasses where className = ?");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("classID");
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public MaplestoryClass getClassByID(int id) {
        try {
            PreparedStatement statement = conn.prepareStatement("Select * from msclasses where classID = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int classID = rs.getInt("classID");
                String className = rs.getString("className");
                MaplestoryClass newClass = new MaplestoryClass(classID, className);
                return newClass;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public MaplestoryClass getClassByName(String name) {
        try {
            // Case-insensitive query for class names
            PreparedStatement statement = conn.prepareStatement("Select * from msclasses where LOWER(className) = ?");
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int classID = rs.getInt("classID");
                String className = rs.getString("className");
                MaplestoryClass newClass = new MaplestoryClass(classID, className);
                return newClass;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addClass(MaplestoryClass mc) {
        try {
            PreparedStatement statement = conn.prepareStatement("insert into msclasses(classID, className) values(?,?)");
            statement.setInt(1, mc.getClassID());
            statement.setString(2, mc.getClassName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
