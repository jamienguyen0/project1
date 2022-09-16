package Model;

// Represents an entry in the database
public class Entry {
    private int entryID;
    private int classID;
    private int mapID;
    private double money;
    private double exp;
    private String videoLink;

    public Entry() {
    }

    public Entry(int entryID, int className, int mapName, double money, double exp, String videoLink) {
        // this.entryID = entryID;
        this.classID = className;
        this.mapID = mapName;
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

    public int getMapID() {
        return mapID;
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
