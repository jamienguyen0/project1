package Service;

import DOA.EntryRepository;
import DOA.MapRepository;
import DOA.MaplestoryClassRepository;
import Model.Entry;

import java.util.List;

public class EntryService {
    EntryRepository er;

    public EntryService() {
        er = new EntryRepository();
    }

    public List<Entry> getAllEntries() {
        return er.getAllEntries();
    }

    public List<Entry> getAllEntriesByClassID(int id) {
        return er.getAllEntriesByClassID(id);
    }

    public List<Entry> getAllEntriesByMapID(int id) {
        return er.getAllEntriesByMapID(id);
    }

    public Entry getEntryByID(int id) {
        return er.getEntryByID(id);
    }

    public void addEntry(int classID, int mapID, double moneyEarned, double expEarned, String url) {
        // Entries don't need to be checked because people can get similar results when training
        // Entry entry = er.getEntryByID(entryID);

        // Check if already exists, add if it doesn't
        Entry newEntry = new Entry(classID, mapID, moneyEarned, expEarned, url);
        er.addEntry(newEntry);
    }

    public void deleteEntry(int entryID) {
        Entry entry = er.getEntryByID(entryID);

        // Check if entry exists before deleting, delete if it does
        if (entry != null) {
            er.deleteEntry(entryID);
        }
    }
}
