package ua.com.alevel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class JsonTest {

    public void test() throws IOException {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setAge(10);
            user.setFirstName("setFirstName" + i);
            user.setLastName("setLastName" + i);
            user.setFullName(user.getFirstName() + " " + user.getLastName());
            users.add(user);
        }

        UserItems items = new UserItems();
        items.setCount(users.size());
        items.setItems(users);

        Container<UserItems> container = new Container<>();
        container.setData(items);

        Gson gson = new Gson();
//        String json = gson.toJson(container);
//        System.out.println("json = " + json);
//
//        FileWriter fileWriter = new FileWriter("users.json");
//        fileWriter.write(json);
//        fileWriter.flush();

        BufferedReader bufferedReader = new BufferedReader(new FileReader("users.json"));

        StringBuilder builder = new StringBuilder();
        while (bufferedReader.ready()) {
            builder.append(bufferedReader.readLine());
        }
        System.out.println("builder = " + builder);

        container = gson.fromJson(builder.toString(), new TypeToken<Container<UserItems>>(){ }.getType());

        System.out.println("container = " + container);

//        ObjectMapper objectMapper = new ObjectMapper();
//        JSONPObject jsonpObject = objectMapper.readValue(builder.toString(), JSONPObject.class);
//        Object value = jsonpObject.getValue();
//        System.out.println("value = " + value);

        JsonElement jsonElement = gson.toJsonTree(builder.toString());
        System.out.println("jsonElement = " + jsonElement.toString());

        JsonObject jsonObject = gson.fromJson(builder.toString(), JsonObject.class);
        parseJson(jsonObject);

    }

    private void parseJson(JsonObject jsonObject) {
        Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
        for (Map.Entry<String, JsonElement> entry : entries) {
            System.out.println("key = " + entry.getKey());
            if (entry.getValue().isJsonNull()) {
                continue;
            }
            if (entry.getValue().isJsonPrimitive()) {
                String value = entry.getValue().toString();
                String trim = value.trim();
                if (trim.matches("(-?\\d+\\.?\\d*)")) {
                    System.out.println("value = " + value);
                }
            }
            if (entry.getValue().isJsonObject()) {
                parseJson(entry.getValue().getAsJsonObject());
            }
            if (entry.getValue().isJsonArray()) {
                JsonArray asJsonArray = entry.getValue().getAsJsonArray();
                for (JsonElement jsonElement : asJsonArray) {
                    parseJson((JsonObject) jsonElement);
                }
            }
        }
    }
}
