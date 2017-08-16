package DB;

import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by luisazurlo on 16/08/2017.
 */

public class JSONManager {

    // === [ Private Data Members ] ============================================

    // Our JSON, in string form.
    private String jsonString;
    private static final String LOGTAG = JSONManager.class.getSimpleName();

    // === [ Public API ] ======================================================

    /**
     * Read from a resources file and create a {@link JSONManager} object that will allow the creation of other
     * objects from this resource.
     *
     * @param resources An application {@link Resources} object.
     * @param id The id for the resource to load, typically held in the raw/ folder.
     */
    public JSONManager(Resources resources, int id) {
        InputStream resourceReader = resources.openRawResource(id);
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println("Unhandled exception while using JSONResourceReader");
            Log.e(LOGTAG, "Unhandled exception while using JSONResourceReader", e);
        } finally {
            try {
                resourceReader.close();
            } catch (Exception e) {
                System.out.println("Unhandled exception while using JSONResourceReader");
                Log.e(LOGTAG, "Unhandled exception while using JSONResourceReader", e);
            }
        }

        jsonString = writer.toString();
    }

    public String getString(){
        return jsonString;
    }

}
