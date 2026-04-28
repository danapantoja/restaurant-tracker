package view;
import javax.swing.*;
import java.awt.*;
import model.Restaurant;
import service.RestaurantManager;


//gui for editing a selected restaurant
public class EditGUI extends JFrame{
    
    private boolean moveToVisited;
    private JTextField nameField;
    private JLabel nameLbl;
    private JTextField locationField;
    private JLabel locationLbl;
    private JTextField foodField;
    private JLabel foodLbl;
    private JTextField ratingField;
    private JLabel ratingLbl;
    private JTextArea reviewArea;
    private JLabel reviewLbl;
    private JButton backBtn;
    private JButton submitBtn;

    private Restaurant restaurant;
    private RestaurantManager manager;

    public EditGUI(Restaurant restaurant, RestaurantManager manager, boolean moveToVisited){
        this.restaurant = restaurant;
        this.manager = manager;
        this.moveToVisited = moveToVisited;
        
        setTitle(moveToVisited ? "Move Restaurant to Visited" : "Edit Restaurant");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 2, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));

        //add edit restaurant name 
        nameLbl = new JLabel("Edit Name");
        nameField = new JTextField(restaurant.getName());
        mainPanel.add(nameLbl);
        mainPanel.add(nameField);
        
        // edit restaurant location
        locationLbl = new JLabel("Edit Location");
        locationField = new JTextField(restaurant.getLocation());
        mainPanel.add(locationLbl);
        mainPanel.add(locationField);

        //edit restaurant food type
        foodLbl = new JLabel("Edit Food Type");
        foodField = new JTextField(restaurant.getCuisine());
        mainPanel.add(foodLbl);
        mainPanel.add(foodField);

        //edit rating
        ratingLbl = new JLabel("Edit Rating (1-5)");
        ratingField = new JTextField(String.valueOf(restaurant.getRating()));
        mainPanel.add(ratingLbl);
        mainPanel.add(ratingField);


        // edit review
        reviewLbl = new JLabel("Edit Review");
        reviewArea = new JTextArea(restaurant.getReview(), 5, 20);
        mainPanel.add(reviewLbl);
        reviewArea.setLineWrap(true);
        reviewArea.setWrapStyleWord(true);
        mainPanel.add(new JScrollPane(reviewArea));


        // submit edit
        submitBtn = new JButton(this.moveToVisited ? "Move" : "Submit");
        submitBtn.addActionListener(e -> {
            if (this.moveToVisited) {
                moveRestaurant();
            } else {
                editRestaurant();
            }
        });

        //back to main
        backBtn = new JButton("Back");
        backBtn.addActionListener(e -> dispose());

        //bottom buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        buttonPanel.add(backBtn);
        buttonPanel.add(submitBtn);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
        

    }
    
    public void editRestaurant(){

        double ratingDouble;
        try {
            ratingDouble = Double.parseDouble(this.ratingField.getText());

            if (ratingDouble < 1 || ratingDouble > 5) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid rating.");
            return;
        }
        restaurant.editName(this.nameField.getText());
        restaurant.editLocation(this.locationField.getText());
        restaurant.editCuisine(this.foodField.getText());
        restaurant.editRating(ratingDouble);
        restaurant.editReview(this.reviewArea.getText());

        dispose();


    }
    public void moveRestaurant(){
        double ratingDouble;

        try {
            ratingDouble = Double.parseDouble(this.ratingField.getText());
            if (ratingDouble < 1 || ratingDouble > 5) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid rating.");
            return;
        }
        restaurant.editRating(ratingDouble);
        restaurant.editReview(this.reviewArea.getText());

        manager.moveToVisited(this.restaurant);
        dispose();
    }
    
    
}
