package data_access;

import use_case.edit_account_information.EditDataAccessInterface;
import use_case.edit_account_information.EditInputData;

import entity.User;
import entity.UserFactory;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AccountFileDataAccessObject implements EditDataAccessInterface {

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, String> accounts = new HashMap<>();

    public AccountFileDataAccessObject(String csvPath) {
        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        if (csvFile.length() != 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    accounts.put(username, password);
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
        accounts.remove(editInputData.getCurrentUsername());
        accounts.put(editInputData.getNewUsername(), editInputData.getNewPassword());
        this.save();
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Entry<String, String> entry : accounts.entrySet()) {
                String line = "%s,%s,%s".formatted(
                        entry.getKey(), entry.getValue());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}


