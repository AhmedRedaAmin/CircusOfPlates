package eg.edu.alexu.csd.oop.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Shielf extends JLabel {

    private ImageIcon shielfShape = new ImageIcon(getClass().getResource("FinalShielf.png"));
    private int hight;
    private int width;
    
    public Shielf() {
        setIcon(shielfShape);
        hight = shielfShape.getIconHeight();
        width = shielfShape.getIconWidth();
    }
    
    public int getHight() {
        return hight;
    }
    
    public int getWidth() {
        return width;
    }
}