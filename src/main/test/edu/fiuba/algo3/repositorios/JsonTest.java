package edu.fiuba.algo3.repositorios;

import edu.fiuba.algo3.modelo.Message;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    public static final String JSON_MESSAGES_JSON = "json/messages.json";

    @Test
    public void loadMessages() throws URISyntaxException {

        JsonMessageRepository reader = new JsonMessageRepository(JSON_MESSAGES_JSON);

        List<Message> actual = reader.load();
        List<Message> expected = new ArrayList<>(List.of(new Message[]{
                new Message("Hola", "Hello")
        }));

        assertEquals(actual, expected);
    }
}
