package service;
import java.util.ArrayList;
import java.util.Collections;
import model.Restaurant;

public class RestaurantManager {

    private ArrayList<Restaurant> visited;
    private ArrayList<Restaurant> wantToVisit;
    
    public RestaurantManager(){
        this.visited = new ArrayList<>();
        this.wantToVisit =  new ArrayList<>();
        
    }
    public void addToVisited(Restaurant _res) {
        this.visited.add(_res);
        Collections.sort(this.visited);
    }

    public void addToWantToVisit(Restaurant _res) {
        this.wantToVisit.add(_res);
        Collections.sort(this.wantToVisit);
    }

    public ArrayList<Restaurant> getVisited(){
        return this.visited;
    }
    public ArrayList<Restaurant> getWantToVisit(){
        return this.wantToVisit;
    }

    public void deleteFromVisited( Restaurant _selected) {
        this.visited.remove(_selected);
    }
    public void deleteFromWantToVisit( Restaurant _selected) {
        this.wantToVisit.remove(_selected);
    }

    public void moveToVisited(Restaurant _selected){
        this.deleteFromWantToVisit(_selected);
        this.addToVisited(_selected);
    }
    public void updateRestaurant(Restaurant restaurant, String name, String location, String cuisine, double rating,
            String review) {
        restaurant.editName(name);
        restaurant.editLocation(location);
        restaurant.editCuisine(cuisine);
        restaurant.editRating(rating);
        restaurant.editReview(review);
    
        Collections.sort(this.visited);

    }
    public void updateRestaurant(Restaurant restaurant, String name, String location, String cuisine){
        restaurant.editName(name);
        restaurant.editLocation(location);
        restaurant.editCuisine(cuisine);
        Collections.sort(this.wantToVisit);
    }

}
