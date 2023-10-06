package homework.polymorphism.competition.obstacle;

import homework.Displayable;

public abstract class Obstacle {

    private final String name;
    private final Type type;
    private boolean overcome;

    public Obstacle(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public boolean isOvercome() {
        return overcome;
    }

    public void setOvercome(boolean overcome) {
        this.overcome = overcome;
    }

    public abstract int getLength();

    public abstract Obstacle copy(Obstacle obstacleCopy);

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof Obstacle))
            return false;

        Obstacle obstacleObj = (Obstacle) obj;
        return name.equals(obstacleObj.getName()) && type.equals(obstacleObj.getType());
    }

    public enum Type implements Displayable {
        RACETRACK("Racetrack"),
        WALL("Wall");

        private final String display;

        Type(String display) {
            this.display = display;
        }

        @Override
        public String getDisplayString() {
            return display;
        }
    }
}
