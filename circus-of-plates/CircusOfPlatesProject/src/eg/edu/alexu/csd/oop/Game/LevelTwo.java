package eg.edu.alexu.csd.oop.Game;

public class LevelTwo implements Level {

    private int plateSpeed = 60;
    private int plateMovement = 5;
    private int clownSpeed = 5;
    private int timeLimits = 4;
    private int timeBetweenPlates = 2000;
    private int numOfClowns = 1;
    
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
        return new LevelThree();
    }
}