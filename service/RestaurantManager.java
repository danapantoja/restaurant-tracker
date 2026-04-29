package service;
import io.FileRepository;
import java.util.ArrayList;
import java.util.Collections;
import model.Restaurant;

public class RestaurantManager {

    final private ArrayList<Restaurant> visited;
    final private ArrayList<Restaurant> wantToVisit;
    final private FileRepository repository;
    final private String visitedFile = "data/visited.bin";
    final private String wantToVisitFile = "data/wantToVisit.bin";
    
    public RestaurantManager(){
        this.repository = new FileRepository();
        this.visited = repository.loadRestaurants(visitedFile);
        this.wantToVisit = repository.loadRestaurants(wantToVisitFile);

    }
    public void addToVisited(Restaurant _res) {
        this.visited.add(_res);
        Collections.sort(this.visited);
        saveData();
    }

    public void addToWantToVisit(Restaurant _res) {
        this.wantToVisit.add(_res);
        Collections.sort(this.wantToVisit);
        saveData();
    }

    public ArrayList<Restaurant> getVisited(){
        return this.visited;
    }
    public ArrayList<Restaurant> getWantToVisit(){
        return this.wantToVisit;
    }

    public void deleteFromVisited(Restaurant _selected) {
        this.visited.remove(_selected);
        saveData();
    }
    public void deleteFromWantToVisit(Restaurant _selected) {
        this.wantToVisit.remove(_selected);
        saveData();
    }

    public void moveToVisited(Restaurant _selected){
        this.deleteFromWantToVisit(_selected);
        this.addToVisited(_selected);
        saveData();
    }

    public ArrayList<Restaurant> searchRestaurant(String _search) {
        ArrayList<Restaurant> results = new ArrayList<>();

        for (Restaurant _res : visited) {
            if (_res.containsIgnoreCase(_search)) {
                results.add(_res);
            }
        }

        for (Restaurant _res : wantToVisit) {
            if (_res.containsIgnoreCase(_search)) {
                results.add(_res);
            }
        }

        Collections.sort(results);
        return results;
    }

    public void updateRestaurant(Restaurant _res, String name, String location, String cuisine, double rating,
            String review) {
        _res.editName(name);
        _res.editLocation(location);
        _res.editCuisine(cuisine);
        _res.editRating(rating);
        _res.editReview(review);
        saveData();
    
        Collections.sort(this.visited);

    }
    public void updateRestaurant(Restaurant _res, String name, String location, String cuisine){
        _res.editName(name);
        _res.editLocation(location);
        _res.editCuisine(cuisine);
        saveData();
        Collections.sort(this.wantToVisit);
    }
    private void saveData() {
        repository.saveRestaurants(visitedFile, visited);
        repository.saveRestaurants(wantToVisitFile, wantToVisit);
    }
}
