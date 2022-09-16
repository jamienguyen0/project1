import Model.Entry;
import Model.Map;
import Model.MaplestoryClass;
import Service.EntryService;
import Service.MapService;
import Service.MaplestoryClassService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaplestoryRatesRepository {
    public static void main(String[] args) {
        EntryService es = new EntryService();
        MapService ms = new MapService();
        MaplestoryClassService mcs = new MaplestoryClassService();

        Javalin app = Javalin.create(JavalinConfig::enableCorsForAllOrigins).start(9000);

        // All entries
        app.get("/entries/", ctx -> ctx.json(es.getAllEntries()));

        // All entries associated with entry id (single entry)
        app.get("/entries/{id}", ctx -> ctx.json(es.getEntryByID( Integer.parseInt(ctx.pathParam("id")))));

        // All entries associated with class id
        app.get("/entries/class/{id}", ctx -> {
           ctx.json(es.getAllEntriesByClassID( Integer.parseInt(ctx.pathParam("id"))));
        });

        app.post("entries", ctx -> {
            ObjectMapper mapper = new ObjectMapper();
            Entry requestEntry = mapper.readValue(ctx.body(), Entry.class);

            // Add map to maps table
            ms.addMap(ms.getMapNameFromID(requestEntry.getMapID()));

            // Add class to classes table
            mcs.addClass(mcs.getClassNameFromID(requestEntry.getClassID()));

            // TODO: Change classID, mapID in Entry.java to className, mapName and retrieve the id from name
            es.addEntry(requestEntry.getEntryID(), requestEntry.getClassID(), requestEntry.getMapID(), requestEntry.getMoney(), requestEntry.getExp(), requestEntry.getVideoLink());
            // es.addEntry(requestEntry.getClassID(), requestEntry.getMapID(), requestEntry.getMoney(), requestEntry.getExp(), requestEntry.getVideoLink());
        });
    }
}
