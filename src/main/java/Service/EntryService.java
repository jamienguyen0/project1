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

    public void addEntry(int entryID, int classID, int mapID, int moneyEarned, int expEarned, String url) {
        Entry entry = er.getEntryByID(entryID);

        // Check if already exists, add if it doesn't
        if (entry == null) {
            Entry newEntry = new Entry(entryID, classID, mapID, moneyEarned, expEarned, url);
            er.addEntry(newEntry);
        }
    }

    public void deleteEntry(int entryID) {
        Entry entry = er.getEntryByID(entryID);

        // Check if entry exists before deleting, delete if it does
        if (entry != null) {
            er.deleteEntry(entryID);
        }
    }
}
