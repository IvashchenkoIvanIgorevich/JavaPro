package homework.polymorphism.competition.obstacle;

public class Racetrack extends Obstacle {

    private final int length;

    public Racetrack(String name, int length) {
        super(name, Type.RACETRACK);
        this.length = length;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public Obstacle copy(Obstacle obstacleCopy) {
        return new Racetrack(obstacleCopy.getName(), obstacleCopy.getLength());
    }
}
