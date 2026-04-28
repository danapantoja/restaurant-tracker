package view;
import javax.swing.*;
import java.awt.*;
import model.Restaurant;
import service.RestaurantManager;


//gui for editing a selected restaurant
public class EditGUI extends JFrame{
    
    private boolean moveToVisited;
    private boolean fromWantToVisit;
    private JTextField nameField;
    private JLabel nameLbl;
    private JTextField locationField;
    private JLabel locationLbl;
    private JTextField cuisineField;
    private JLabel cuisineLbl;
    private JTextField ratingField;
    private JLabel ratingLbl;
    private JTextArea reviewArea;
    private JLabel reviewLbl;
    private JButton backBtn;
    private JButton submitBtn;

    private Restaurant restaurant;
    private RestaurantManager manager;

    public EditGUI(Restaurant restaurant, RestaurantManager manager, boolean moveToVisited, boolean fromWantToVisit){
        this.restaurant = restaurant;
        this.manager = manager;
        this.moveToVisited = moveToVisited;
        this.fromWantToVisit = fromWantToVisit;
        
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

        //edit restaurant cuisine type
        cuisineLbl = new JLabel("Edit Cuisine Type");
        cuisineField = new JTextField(restaurant.getCuisine());
        mainPanel.add(cuisineLbl);
        mainPanel.add(cuisineField);

        //edit rating
        if (!this.fromWantToVisit){
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

        }
        

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
        if (this.nameField.getText().trim().equals("") || this.locationField.getText().trim().equals("") || this.cuisineField.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter all required fields.");
            return;
        }

        if (!this.fromWantToVisit){

            double ratingDouble;
            try {
                ratingDouble = Double.parseDouble(this.ratingField.getText());

                if (ratingDouble < 1 || ratingDouble > 5) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid rating.");
                return;
            }
            //update visited
            manager.updateRestaurant(restaurant,this.nameField.getText(), this.locationField.getText(),this.cuisineField.getText(),ratingDouble,this.reviewArea.getText());
        }else{
            //update want to visit
            manager.updateRestaurant(restaurant, this.nameField.getText(), this.locationField.getText(),this.cuisineField.getText());
        }

        dispose();

    }
    public void moveRestaurant(){

        if (this.nameField.getText().trim().equals("") || this.locationField.getText().trim().equals("") || this.cuisineField.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter all required fields.");
            return;
        }
        double ratingDouble;

        try {
            ratingDouble = Double.parseDouble(this.ratingField.getText());
            if (ratingDouble < 1 || ratingDouble > 5) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid rating.");
            return;
        }
        
        manager.updateRestaurant(restaurant,this.nameField.getText(), this.locationField.getText(),this.cuisineField.getText(),ratingDouble,this.reviewArea.getText());
        

        manager.moveToVisited(this.restaurant);
        dispose();
    }
    
    
}
