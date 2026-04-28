package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Restaurant;

public class FileRepository {

    //static method to save to file (one list) 
    public void saveRestaurants(String fileName, ArrayList<Restaurant> restaurants){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(restaurants);
        } catch (IOException e) {
            System.out.println("Error saving restaurants: " + e.getMessage());
        }

    }

    //static method to load restaurants (one list)
    @SuppressWarnings("unchecked")
    public ArrayList<Restaurant> loadRestaurants(String fileName){
        File file = new File(fileName);

        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (ArrayList<Restaurant>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading restaurants: " + e.getMessage());
            return new ArrayList<>();
        }

    }
    
}
