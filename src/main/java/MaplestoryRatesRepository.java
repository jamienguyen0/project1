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

        app.get("/entries/", ctx -> ctx.json(es.getAllEntries().toString()));

        app.get("/entries/class/{id}", ctx -> {
           ctx.json(es.getAllEntriesByClassID( Integer.parseInt(ctx.pathParam("id")) ) );
        });

        app.post("entries", ctx -> {
            ObjectMapper mapper = new ObjectMapper();
            Entry requestEntry = mapper.readValue(ctx.body(), Entry.class);
            es.addEntry(requestEntry.getEntryID(), requestEntry.getClassID(), requestEntry.getMapID(), requestEntry.getMoney(), requestEntry.getExp(), requestEntry.getVideoLink());
        });
    }
}
