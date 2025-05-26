package edu.fiuba.algo3.repositorios;

import edu.fiuba.algo3.modelo.Message;
import edu.fiuba.algo3.modelo.MessageRepository;
import edu.fiuba.algo3.modelo.MessagesFileInvalid;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonMessageRepository implements MessageRepository {
    public static final String MESSAGES_KEY = "messages";
    public static final String MESSAGE_ES_CONTENT = "esContent";
    public static final String MESSAGE_US_CONTENT = "usContent";
    private final BufferedReader jsonBuffer;

    public JsonMessageRepository(String path) {
        ClassLoader classLoader = getClass().getClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream(path);
        InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        jsonBuffer = new BufferedReader(streamReader);
    }

    @Override
    public List<Message> load() {
        JSONArray messages = getJsonMessageArray();

        return getAllMessagesFrom(messages);
    }

    private JSONArray getJsonMessageArray() {
        try {
            JSONParser parser = new JSONParser();
            JSONObject wholeJson = (JSONObject) parser.parse(jsonBuffer);
            return (JSONArray) wholeJson.get(MESSAGES_KEY);
        } catch (FileNotFoundException e) {
            throw new MessagesFileInvalid();
        } catch (ParseException e) {
            throw new MessagesFileInvalid();
        } catch (IOException e) {
            throw new MessagesFileInvalid();
        }
    }

    private List<Message> getAllMessagesFrom(JSONArray messages) {
        List<Message> result = new ArrayList<>();

        Iterator i = messages.listIterator();

        while (i.hasNext()) {
            result.add(getMessageFrom(i.next()));
        }

        return result;
    }

    private Message getMessageFrom(Object next) {
        JSONObject msgAsJson = (JSONObject) next;

        return new Message(
                msgAsJson.get(MESSAGE_ES_CONTENT).toString(),
                msgAsJson.get(MESSAGE_US_CONTENT).toString()
        );
    }
}
