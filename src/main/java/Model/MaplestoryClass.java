package Model;

public class MaplestoryClass {
    private int classID;
    private String className;

    public MaplestoryClass() {
    }

    public MaplestoryClass(int classID, String className) {
        this.classID = classID;
        this.className = className;
    }

    public int getClassID() {
        return classID;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return this.className;
    }
}
