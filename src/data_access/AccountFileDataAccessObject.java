package data_access;

import use_case.edit_account_information.EditDataAccessInterface;
import use_case.edit_account_information.EditInputData;

import entity.User;
import entity.UserFactory;
import use_case.login.LoginUserDataAccessInterface;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class AccountFileDataAccessObject implements EditDataAccessInterface {

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, User> accounts = new HashMap<>();

    public AccountFileDataAccessObject(String csvPath) {
        csvFile = new File(csvPath);
        headers.put("userid", 0);
        headers.put("username", 1);
        headers.put("password", 2);
        if (csvFile.length() != 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String userid = String.valueOf(col[headers.get("userid")]);
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
//                    User oldUser = UserFactory.createUser(username, password);
//                    oldUser.setUserid(userid);
//                    accounts.put(userid, oldUser);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean existsByNameAndPassword(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();
            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String checkingUsername = String.valueOf(col[headers.get("username")]);
                String checkingPassword = String.valueOf(col[headers.get("password")]);
                if (username.equals(checkingUsername) && password.equals(checkingPassword)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(EditInputData editInputData) {
        User editingUser = accounts.get(editInputData.getUserid());
        editingUser.setUsername(editInputData.getNewUsername());
        editingUser.setPassword(editInputData.getNewPassword());
        accounts.remove(editInputData.getUserid());
        accounts.put(editingUser.getUserID(), editingUser);
        this.save();
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = "%s,%s,%s".formatted(
                        user.getUserID(), user.getUsername(), user.getPassword());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


