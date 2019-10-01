package main.java;

import main.java.entities.User;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CacheManager {
    private final LogicManager logicManager;
    private Map<String, User> usersByName = new HashMap<>();
    private final Logger log = Logger.getLogger(CacheManager.class);
    private final FileOutputStream outputStream;

    private final String pathToSaveFile = "settings.xml";


    public CacheManager(LogicManager logicManager) {
        this.logicManager = logicManager;
        outputStream = createFileForResults();
//        getAllUsers();

    }

    private void getAllUsers() {
        try {
            File file = new File(pathToSaveFile);
            FileInputStream inputStream = new FileInputStream(file);

            // TODO
        } catch (IOException e) {
            log.error("getAllUsers is failed. Message=" + e.getMessage());
        }
    }

    private void saveUsers() {

    }

    private FileOutputStream createFileForResults() {
        try {
            File file = new File(pathToSaveFile);
            return new FileOutputStream(file.getAbsolutePath(), true);
        } catch (FileNotFoundException e) {
            log.error("createFileForResults is failed. Message=" + e.getMessage());
            return null;
        }
    }

    public User getUserByName(String name) {
        log.trace("getUserByName. Return user with name=" + name);
        return usersByName.get(name);
    }

    public void putUserByName(User user) {
        log.trace("putUserByName. Put user with name=" + user.getName());
        usersByName.put(user.getName(), user);
    }

}
