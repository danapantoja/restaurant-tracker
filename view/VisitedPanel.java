package view;
import javax.swing.*;
import java.awt.*;
import model.Restaurant;
import service.RestaurantManager;


public class VisitedPanel extends JPanel{
    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JButton searchBtn;
    private JList<Restaurant> restaurantList;
    private DefaultListModel<Restaurant> listModel;

    private RestaurantManager manager;

    public VisitedPanel(RestaurantManager manager){
        this.manager = manager;

        //add search functionality (on keystroke ? maybe, or just add search button)
    
        //list of restaurants here scrollable (use restuaurant cell renderer)


        //---bottom button panel---
        //add button to add new restaurant gui
        //add edit restuarant functionality when a user clicks on a restaurant and presses edit
        //add delete restaurant button functionality when user clicks on a restaurant and presses delete

    }


    
    
}
