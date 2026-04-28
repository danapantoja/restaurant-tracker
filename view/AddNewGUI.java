package view;

import javax.swing.*;
import java.awt.*;
import model.Restaurant;
import service.RestaurantManager;
//gui for adding a new restaurant to either visited or want to visit (if user inputs rating / review, disable want to visit button)


public class AddNewGUI extends JFrame{

    private RestaurantManager manager;
    private boolean wantToVisit;
    public AddNewGUI(RestaurantManager manager, boolean wantToVist){
        this.manager = manager;
        this.wantToVisit = wantToVisit;


        setTitle(this.wantToVisit ? "Add Restaurant to Want-To-Visit" : "Add Restaurant to Visited");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 2, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));





        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);

    }
}
