package view;

import javax.swing.*;
import java.awt.*;
import model.Restaurant;
import service.RestaurantManager;
//gui for adding a new restaurant to either visited or want to visit (if user inputs rating / review, disable want to visit button)


public class AddNewGUI extends JFrame{

    private RestaurantManager manager;
    private boolean wantToVisit;
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

    public AddNewGUI(RestaurantManager manager, boolean wantToVisit){
        this.manager = manager;
        this.wantToVisit = wantToVisit;


        setTitle(this.wantToVisit ? "Add Restaurant to Want-To-Visit" : "Add Restaurant to Visited");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 2, 0));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 35, 25, 35));



        //add edit restaurant name 
        nameLbl = new JLabel("Add Name");
        nameField = new JTextField();
        mainPanel.add(nameLbl);
        mainPanel.add(nameField);
        
        //add restaurant location
        locationLbl = new JLabel("Add Location");
        locationField = new JTextField();
        mainPanel.add(locationLbl);
        mainPanel.add(locationField);

        //add restaurant cuisine
        cuisineLbl = new JLabel("Add Cuisine");
        cuisineField = new JTextField();
        mainPanel.add(cuisineLbl);
        mainPanel.add(cuisineField);

        if(!this.wantToVisit){
            //add rating
            ratingLbl = new JLabel("Add Rating (1-5)");
            ratingField = new JTextField();
            mainPanel.add(ratingLbl);
            mainPanel.add(ratingField);


            // add review
            reviewLbl = new JLabel("Add Review");
            reviewArea = new JTextArea( 5, 20);
            mainPanel.add(reviewLbl);
            reviewArea.setLineWrap(true);
            reviewArea.setWrapStyleWord(true);
            mainPanel.add(new JScrollPane(reviewArea));

        }

        
        // submit 
        submitBtn = new JButton("Submit");
        submitBtn.addActionListener(e -> {
            addRestaurant();
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
    //add validation for name , location, cuisine type
    //use wantToVisit to add to specific list
    public void addRestaurant(){


        if (this.nameField.getText().trim().equals("") || this.locationField.getText().trim().equals("") || this.cuisineField.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Please enter all required fields.");
            return;
        }
        if (this.wantToVisit){ //add to want to visit
            manager.addToWantToVisit( new Restaurant(this.nameField.getText(), this.locationField.getText(), this.cuisineField.getText()));
        }else{ //try to add to visited

            Double ratingDouble;
            try{
                ratingDouble = Double.parseDouble(this.ratingField.getText());
                if (ratingDouble < 1 || ratingDouble > 5) throw new NumberFormatException();

            }catch (NumberFormatException e){
                JOptionPane.showMessageDialog(this, "Please enter a valid rating.");
                return;
            }
            Restaurant r = new Restaurant(this.nameField.getText(), this.locationField.getText(), this.cuisineField.getText());
            r.editRating(ratingDouble);
            r.editReview(this.reviewArea.getText());
            manager.addToVisited(r);

        }
        dispose();

        
    }
}
