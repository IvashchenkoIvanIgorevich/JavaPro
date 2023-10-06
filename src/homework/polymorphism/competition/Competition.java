package homework.polymorphism.competition;

import homework.polymorphism.competition.obstacle.Obstacle;
import homework.polymorphism.competition.obstacle.Racetrack;
import homework.polymorphism.competition.obstacle.Wall;
import homework.polymorphism.competition.participant.Participant;

import java.util.*;
import java.util.stream.Collectors;

public class Competition {

    private final List<Participant> participants;
    private final List<Obstacle> obstacles;

    public Competition(List<Participant> participants, List<Obstacle> obstacles) {
        this.participants = participants;
        this.obstacles = obstacles;
    }

    public Map<Participant, List<Obstacle>> start() {
        return participants.stream()
                .collect(Collectors.toMap(
                        participant -> participant,
                        participant -> processObstacles(participant, obstacles)
                ));
    }

    private List<Obstacle> processObstacles(Participant participant, List<Obstacle> obstacles) {
        List<Obstacle> overcomingResult = new ArrayList<>();
        for (Obstacle obstacle : obstacles) {
            Obstacle copy = obstacle.copy(obstacle);

            if (obstacle instanceof Wall) {
                if (participant.jump(copy.getLength())) {
                    copy.setOvercome(true);
                }
            } else if (obstacle instanceof Racetrack) {
                if (participant.run(copy.getLength())) {
                    copy.setOvercome(true);
                }
            }

            overcomingResult.add(copy);

            if (!copy.isOvercome()) {
                break;
            }
        }
        return overcomingResult;
    }
}
