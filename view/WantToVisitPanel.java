package view;
import java.awt.*;

import javax.swing.*;

import model.Restaurant;
import service.RestaurantManager;

public class WantToVisitPanel extends JPanel{
    private JButton searchBtn;
    private JTextField searchField;
    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;

    private JList<Restaurant> restaurantList;
    private DefaultListModel<Restaurant> listModel;

    private RestaurantManager manager;
    public WantToVisitPanel(RestaurantManager manager){
        this.manager = manager;


        setLayout(new BorderLayout());
        //add search functionality
        searchField = new JTextField(20);
        searchBtn = new JButton("Search");

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);
        topPanel.add(searchBtn);

        add(topPanel, BorderLayout.NORTH);

        //list of restaurants here scrollable (use restuaurant cell renderer)


        //---bottom button panel---
        //add button to add new restaurant gui
        //add edit restuarant functionality when a user clicks on a restaurant and presses edit
        //add delete restaurant button functionality when user clicks on a restaurant and presses delete
    }
    
}
