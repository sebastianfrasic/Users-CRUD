package com.example.eafit.repositories;

import com.example.eafit.model.User;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl {

    private final String FILE_PATH = "E:\\users-api-main01\\src\\main\\resources\\users_database.txt";
    private final String CONFIDENTIAL_PASSWORD = "Confidential password";

    @SneakyThrows
    public User saveUser(User user) {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
        User newUser = new User(user.getUsername(), user.getPassword(), user.getEmail());
        writer.newLine();
        String userPropertiesString = newUser.getId() + "," + newUser.getUsername() + "," + newUser.getPassword() + ","
                + newUser.getEmail() + "," + newUser.isActive();
        writer.write(userPropertiesString);
        writer.close();
        return newUser;
    }

    @SneakyThrows
    public User getUserById(String id) {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");

            User user = User.builder()
                    .id(fields[0])
                    .username(fields[1])
                    .password(CONFIDENTIAL_PASSWORD)
                    .email(fields[3])
                    .isActive(Boolean.parseBoolean(fields[4])).build();

            if (fields[0].equals(id)) {
                return user;
            }
        }
        return null;
    }

    @SneakyThrows
    public List<User> getUsersByName(String name) {
        List<User> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");

            User user = User.builder()
                    .id(fields[0])
                    .username(fields[1])
                    .password(CONFIDENTIAL_PASSWORD)
                    .email(fields[3])
                    .isActive(Boolean.parseBoolean(fields[4])).build();
            if (fields[1].equalsIgnoreCase(name)) {
                users.add(user);
            }
        }
        return users;
    }

    @SneakyThrows
    public List<User> getAllUsersFromDatabase() {
        List<User> users = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            User user = User.builder()
                    .id(fields[0])
                    .username(fields[1])
                    .password(CONFIDENTIAL_PASSWORD)
                    .email(fields[3])
                    .isActive(Boolean.parseBoolean(fields[4])).build();
            users.add(user);
        }
        return users;
    }


}

