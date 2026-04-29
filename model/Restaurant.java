package model;

import java.io.Serializable;



//import java.util;
 
public class Restaurant implements Comparable<Restaurant>, Serializable {

    private String name;
    private String location;
    private String cuisine;

    private double rating;
    private String review;
    private static final long serialVersionUID = 1L;
    
    public Restaurant(String _name, String _location, String _cuisine){

        this.name = _name;
        this.location = _location;
        this.cuisine = _cuisine;
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
    public Double getRating() {
        return this.rating;
    }
    public String getReview() {
        return this.review;
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
    public void editRating(Double _newRating) {
        this.rating = _newRating;
    }
    public void editReview(String _newReview) {
        this.review = _newReview;
    }
    
    @Override // alphabetical order
    public int compareTo(Restaurant _res) {
        return this.name.compareTo(_res.name);
    }

    public boolean containsIgnoreCase(String s) {
        return this.name.toLowerCase().contains(s.toLowerCase());
    }

}
