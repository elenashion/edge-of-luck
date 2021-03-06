package edge.of.luck.classes;

import edge.of.luck.entities.User;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UsersHelper {
    private final Map<String, User> usersByName = new HashMap<>();
//    private final FileOutputStream outputStream;

    private final String pathToSaveFile = "settings.xml";


    public UsersHelper() {
//        outputStream = createFileForResults();
//        getAllUsers();

    }

    private void getAllUsers() {
        try {
            File file = new File(pathToSaveFile);
            FileInputStream inputStream = new FileInputStream(file);

            // TODO
        } catch (IOException e) {
            log.error("getAllUsers is failed. Message={}", e.getMessage());
        }
    }

    private User createNewUser(String name) {
        User user = new User(name);
        log.trace("putUserByName. Put user with name={}", user.getName());
        usersByName.put(user.getName(), user);
        return user;
    }

    public User getOrCreateUserByName(String name) {
        User user = usersByName.get(name);
        return user != null ? user : createNewUser(name);
    }

    private void saveUsers() {
        // TODO
    }

    private FileOutputStream createFileForResults() {
        try {
            File file = new File(pathToSaveFile);
            return new FileOutputStream(file.getAbsolutePath(), true);
        } catch (FileNotFoundException e) {
            log.error("createFileForResults is failed. Message={}", e.getMessage());
            return null;
        }
    }

}
