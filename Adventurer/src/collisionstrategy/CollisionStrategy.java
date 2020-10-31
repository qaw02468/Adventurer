package collisionstrategy;

import adventure.Direction;
import role.Role;

public interface CollisionStrategy {
    boolean checkCollision(Direction direction, Role role);
}
