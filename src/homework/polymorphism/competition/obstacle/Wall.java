package homework.polymorphism.competition.obstacle;

public class Wall extends Obstacle {

    private final int height;

    public Wall(String name, int height) {
        super(name, Type.WALL);
        this.height = height;
    }

    @Override
    public int getLength() {
        return height;
    }

    @Override
    public Obstacle copy(Obstacle obstacleCopy) {
        return new Wall(obstacleCopy.getName(), obstacleCopy.getLength());
    }
}
