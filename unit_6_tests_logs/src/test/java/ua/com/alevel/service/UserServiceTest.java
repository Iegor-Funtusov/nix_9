package ua.com.alevel.service;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.*;
import ua.com.alevel.entity.User;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    private final static UserService userService = new UserService();
    private final static int USERS_SIZE = 10;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < USERS_SIZE; i++) {
            User user = UserGenerationUtil.generateUser(UserGenerationUtil.NAME + i, i);
            userService.create(user);
        }
        Assertions.assertEquals(USERS_SIZE, userService.findAll().size());
    }

    @Order(1)
    @Test
    public void shouldBeCreateUserWhenNameIsEmpty() {
        User user = new User();
        user.setName(null);
        user.setAge(10);
        userService.create(user);

        verifyUserListWhenUsersListIsNotEmpty(11);
    }

    @Order(2)
    @Test
    public void shouldBeDeleteUser() {
        String id = getRandomIdFromUserList();
        userService.delete(id);
        verifyUserListWhenUsersListIsNotEmpty(10);
    }

    @Order(3)
    @Test
    public void shouldBeFindUserWhenIdIsRandom() {
        User user = getRandomUserFromUserList(getRandomIdFromUserList());

        Assertions.assertNotNull(user);
    }

    @Order(4)
    @Test
    public void shouldBeUpdateUser() {
        String id = getRandomIdFromUserList();
        User user = getRandomUserFromUserList(id);
        user.setAge(100);
        user.setName("test");
        userService.update(user);

        user = userService.findById(id);

        Assertions.assertEquals("test", user.getName());
        Assertions.assertEquals(100, user.getAge());
    }

    private void verifyUserListWhenUsersListIsNotEmpty(int size) {
        List<User> users = userService.findAll();

        Assertions.assertTrue(CollectionUtils.isNotEmpty(users));
        Assertions.assertEquals(size, userService.findAll().size());
    }

    private String getRandomIdFromUserList() {
        return userService.findAll().stream().findFirst().get().getId();
    }

    private User getRandomUserFromUserList(String id) {
        return userService.findById(id);
    }
}
