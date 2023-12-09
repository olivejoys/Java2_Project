package Java2_Project;

import java.io.*;
import java.util.ArrayList;

public class FitnessRecordList implements Serializable {
    private ArrayList<FitnessRecord> records;

    // Constructor
    public FitnessRecordList() {
        records = new ArrayList<>();
    }

    // Methods for managing the list of fitness records

    public void addRecord(FitnessRecord record) {
        records.add(record);
    }

    public void deleteRecord(int index) {
        records.remove(index);
    }

    public FitnessRecord getRecord(int index) {
        return records.get(index);
    }

    public int getSize() {
        return records.size();
    }

    // to search method
    public ArrayList<FitnessRecord>
    searchRecords(String key, String searchField) {
        ArrayList<FitnessRecord> result = new ArrayList<>();
        for (FitnessRecord record : records) {
            String fieldValue = null;
            switch (searchField) {
                case "product_id":
                    fieldValue = record.getProduct_id();
                    break;
                case "product_type":
                    fieldValue = record.getProduct_type();
                    break;
                // add new cases later (in case we need it)
            }

        if (fieldValue != null && fieldValue.toLowerCase()
                .contains(key.toLowerCase())) {
            result.add(record);
        }
    }
        return result;
}

    // Update method
    public void updateRecord(int index, FitnessRecord updatedRecord) {
        records.set(index, updatedRecord);
    }

    // Save to file method
    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new
                FileOutputStream(fileName))) {
            oos.writeObject(records);
            System.out.println("Records saved to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // this method will load from file method
    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new
                FileInputStream(fileName))) {
            records = (ArrayList<FitnessRecord>) ois.readObject();
            System.out.println("Records loaded from file successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}