package eg.edu.alexu.csd.oop.Game;

public class LevelFive implements Level {

    private int plateSpeed = 40;
    private int plateMovement = 6;
    private int clownSpeed = 7;
    private int timeLimits = 2;
    private int timeBetweenPlates = 1000;
    private int numOfClowns = 2;
    
    @Override
    public int getSpeedOfClown() {
        return clownSpeed;
    }

    @Override
    public int getSpeedOfPlates() {
        return plateSpeed;
    }

    @Override
    public int getNumberOfClowns() {
        return numOfClowns;
    }

    @Override
    public int getTimeLimits() {
        return timeLimits;
    }

    @Override
    public int getTimeBetweenPlates() {
        return timeBetweenPlates;
    }

    @Override
    public int getPlateMovement() {
        return plateMovement;
    }
    
    @Override
    public Level levelUp() {
        return new LevelOne();
    }
}