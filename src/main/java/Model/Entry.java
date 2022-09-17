package Model;

// Represents an entry in the database
public class Entry {
    private int entryID;
    private int classID;
    private String className;
    private int mapID;
    private String mapName;
    private double money;
    private double exp;
    private String videoLink;

    public Entry() {
    }

    public Entry(int entryID, int classID, int mapID, double money, double exp, String videoLink) {
        this.entryID = entryID;
        this.classID = classID;
        this.mapID = mapID;
        this.money = money;
        this.exp = exp;
        this.videoLink = videoLink;
    }

    public Entry(int classID, int mapID, double money, double exp, String videoLink) {
        this.classID = classID;
        this.mapID = mapID;
        this.money = money;
        this.exp = exp;
        this.videoLink = videoLink;
    }

    // Using Strings for class and map instead of id for receiving json from the web
    public Entry(int entryID, String className, String mapName, double money, double exp, String videoLink) {
        this.entryID = entryID;
        this.className = className;
        this.mapName = mapName;
        this.money = money;
        this.exp = exp;
        this.videoLink = videoLink;
    }

    public int getEntryID() {
        return entryID;
    }

    public int getClassID() {
        return classID;
    }

    public String getClassName() {
        return className;
    }

    public int getMapID() {
        return mapID;
    }

    public String getMapName() {
        return mapName;
    }

    public double getMoney() {
        return money;
    }

    public double getExp() {
        return exp;
    }

    public String getVideoLink() {
        return videoLink;
    }

    @Override
    public String toString() {
        return "(" + classID + ", " + mapID + ", " + money + ", " + exp + ", " + videoLink + ")";
    }
}
