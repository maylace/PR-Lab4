/**
 * Created by Dorin Luca on 27.05.2017.
 */

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONConfig {
    String fileLocation;

    public JSONConfig(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public JSONObject getObject() {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(fileLocation));
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
