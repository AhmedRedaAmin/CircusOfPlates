import java.util.ArrayList;

import javax.swing.JPanel;

public class Hand {

    private ArrayList<Plate> plates;
    private Character clown;
    private int hight = 0;
    private int plateWidth = 20;
    private int handWidth = 20;
    
    public Hand(Character clown) {
        plates = new ArrayList<>();
        this.clown = clown;
    }
    
    private boolean iscontinuous(JPanel panal) {
        if (plates.size() >= 3) {
            int startPlate = plates.size() - 1;
            for (int i = startPlate-1; i > startPlate - 3; i--) {
                if (plates.get(i+1).getColor() != plates.get(i).getColor())
                    return false;
            }
            removePlates(startPlate, panal);
            return true;
        } else {
            return false;
        }
    }
    
    private void removePlates(int startPlate, JPanel panel) {
        for (int i = startPlate; i > startPlate - 3; i--) {
            panel.remove(plates.get(i));
            hight -= plates.get(i).getHight();
            plates.remove(i);
        }
    }

    public boolean addPlate(Plate plate, JPanel panal) {
        plate.stopMove();
        plates.add(plate);
        hight += plate.getHight();
        return iscontinuous(panal);
    }
    
    public int platesHight() {
        return hight;
    }
    
    public boolean inRangeLeft (Plate plate) {
        if (plate.getXcoord() >= clown.getXCoordinatesLeft() - plateWidth && plate.getXcoord() <= clown.getXCoordinatesLeft() + handWidth) {
            return true;
        } else 
            return false;
    }
    
    public boolean inRangeRight (Plate plate) {
        if (plate.getXcoord() < clown.getXCoordinatesRight() && plate.getXcoord() >= clown.getXCoordinatesRight() - handWidth - plateWidth - 10) {
            return true;
        } else
            return false;
    }
}
