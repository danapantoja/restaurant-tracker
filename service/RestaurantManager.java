package service;
import java.util.ArrayList;
import java.util.Collections;
import model.Restaurant;

public class RestaurantManager {

    private ArrayList<Restaurant> visited = new ArrayList<>();
    private ArrayList<Restaurant> wantToVisit = new ArrayList<>();
    
    public RestaurantManager(){
        this.visited = visited;
        this.wantToVisit = wantToVisit;
        
    }

    public void addToVisited(Restaurant _res) {
        visited.add(_res);
        Collections.sort(visited);
    }

    public void addToWantToVisit(Restaurant _res) {
        wantToVisit.add(_res);
        Collections.sort(wantToVisit);
    }

    public ArrayList<Restaurant> getVisited(){
        return this.visited;
    }
    public ArrayList<Restaurant> getWantToVisit(){
        return this.wantToVisit;
    }
    




    // Restaurant is selected from GUI
    public void deleteRestaurant(ArrayList<Restaurant> _tab, Restaurant _selected) {
        _tab.remove(_selected);
    }
}
