package Model;// Represents an entry in the database

public class Entry {
    private int entryID;
    private int classID;
    private int mapID;
    private int money;
    private int exp;
    private String videoLink;

    public Entry() {
    }

    public Entry(int entryID, int classID, int mapID, int money, int exp, String videoLink) {
        this.entryID = entryID;
        this.classID = classID;
        this.mapID = mapID;
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

    public int getMoney() {
        return money;
    }

    public int getExp() {
        return exp;
    }

    public String getVideoLink() {
        return videoLink;
    }

    @Override
    public String toString() {
        return "(" + entryID + ", " + classID + ", " + mapID + ", " + money + ", " + exp + ", " + videoLink + ")";
    }
}
