package ua.com.alevel;

import com.google.gson.Gson;
import ua.com.alevel.generics.ResponseContainer;
import ua.com.alevel.generics.ResponseData;
import ua.com.alevel.generics.User;

import java.util.UUID;

public class GenericsClass {

    public void test() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setLogin("login");
        user.setPassword("password");

        Gson gson = new Gson();
        String json = gson.toJson(user);
        System.out.println("json = " + json);

        ResponseData responseData = new ResponseData();
        responseData.setData(user);
        responseData.setStatus(true);

        json = gson.toJson(responseData);
        System.out.println("responseData = " + json);

        ResponseContainer<User, Boolean> responseContainer = new ResponseContainer<>(user, true);
        json = gson.toJson(responseContainer);
        System.out.println("responseContainer = " + json);
    }
}
