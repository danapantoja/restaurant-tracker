package model;

//import java.util;

public class Restaurant implements Comparable<Restaurant> {

    private String name;
    private String location;
    private String cuisine;

    private double rating;
    private String review;
    
    public Restaurant(String _name, String _location, String _cuisine){
        this.name = _name;
        this.location = _location;
        this.cuisine = _location;
    }

    public String getName() {
        return this.name;
    }
    public String getLocation() {
        return this.location;
    }
    public String getCuisine() {
        return this.cuisine;
    }

    public void editName(String _newName) {
        this.name = _newName;
    }

    public void editLocation(String _newLocation) {
        this.location = _newLocation;
    }

    public void editCuisine(String _newCuisine) {
        this.cuisine = _newCuisine;
    }
    

    @Override // alphabetical order
    public int compareTo(Restaurant _res) {
        return this.name.compareTo(_res.name);
    }
}
