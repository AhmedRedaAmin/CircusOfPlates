package eg.edu.alexu.csd.oop.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RightPlate extends Plate{
	 public RightPlate(String color, int startx, int starty, int shielfWidth, Character[] clown, Game panel, int speed, int movement) {
	        super(color, startx, starty, shielfWidth, clown, panel, speed, movement);
	    }

	    @Override
	    protected void moveIt(Game panel) {
	        if(move) {
	            this.rightchecker(panel);
	            this.leftchecker(panel);
	            this.setBounds((xcoordinates >= panel.getWidth()-shielfWidth-20)?xcoordinates-=movement:xcoordinates,(xcoordinates < panel.getWidth()-shielfWidth-20)?ycoordinates+=movement:ycoordinates, width, hight);
	        } else {
	            if (right) {
	                this.setBounds(clown[0].getXCoordinatesRight()-42, ycoordinates, width, hight);
	            } else if (left) {
	                this.setBounds(clown[0].getXCoordinatesLeft(), ycoordinates, width, hight);
	            }
	        }
	    }
	}