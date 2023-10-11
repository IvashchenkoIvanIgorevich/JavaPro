package homework.polymorphism.competition.participant;

import homework.Displayable;

public abstract class Participant implements Runnable, Jumpable {

    private final String name;
    private final Type type;
    private int passedDistance;

    public Participant(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public int getPassedDistance() {
        return passedDistance;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void countDistance(int obstacleLength) {
        passedDistance += obstacleLength;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof Participant))
            return false;

        Participant participantObj = (Participant) obj;
        return name.equals(participantObj.getName()) && type.equals(participantObj.getType());
    }

    public enum Type implements Displayable {
        HUMAN("Human"),
        CAT("Cat"),
        ROBOT("Robot");

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
