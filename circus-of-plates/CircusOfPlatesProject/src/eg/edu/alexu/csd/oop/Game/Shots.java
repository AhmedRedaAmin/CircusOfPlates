package eg.edu.alexu.csd.oop.Game;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Shots {
    private List<SnapShot> mementoList = new ArrayList<SnapShot>();

    public void add(SnapShot state) throws Exception{
       mementoList.add(state);
       /*FileOutputStream output = new FileOutputStream("Dodo.data");
       ObjectOutputStream obOut = new ObjectOutputStream(output);
       obOut.writeObject(mementoList);*/
    }

    public SnapShot get(int index){
       return mementoList.get(index);
    }
}