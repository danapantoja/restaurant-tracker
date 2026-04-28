package view;

import java.awt.*;

import javax.swing.*;
import model.Restaurant;

//display for each restaurant in the scrollable list
public class RestaurantCellRenderer extends JPanel implements ListCellRenderer<Restaurant>{

    private JLabel nameLbl;
    private JLabel infoLbl;
    private JLabel reviewLbl;
    private boolean wantToVisit;
    public RestaurantCellRenderer(boolean wantToVisit){
        this.wantToVisit = wantToVisit;
        setLayout(new GridLayout(wantToVisit ? 2 : 3, 1));
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(10, 14, 10, 14)
        ));
        nameLbl = new JLabel();
        nameLbl.setFont(new Font("Arial", Font.BOLD, 16));

        infoLbl = new JLabel();
        infoLbl.setFont(new Font("Arial", Font.PLAIN, 13));

        reviewLbl = new JLabel();
        reviewLbl.setFont(new Font("Arial", Font.ITALIC, 12));

        add(nameLbl);
        add(infoLbl);
        if (!this.wantToVisit) add(reviewLbl);
        

    }
    @Override
    public Component getListCellRendererComponent(JList<? extends Restaurant> list, Restaurant value, int index,
            boolean isSelected, boolean cellHasFocus) {
            nameLbl.setText(value.getName());
            if (this.wantToVisit){
                infoLbl.setText(value.getLocation() + " • " + value.getCuisine());
            }else{
                infoLbl.setText(value.getLocation() + " • " + value.getCuisine() + " • Rating: "+ value.getRating() + "/5.0");
                reviewLbl.setText("“"+value.getReview()+"“");
            }
            


            if (isSelected) {
                setBackground(new Color(220, 235, 255));
            } else {
                setBackground(Color.WHITE);
            }

            nameLbl.setForeground(Color.BLACK);
            infoLbl.setForeground(Color.DARK_GRAY);
            reviewLbl.setForeground(Color.GRAY);
            setOpaque(true);
           
            return this;

    }
    
}
