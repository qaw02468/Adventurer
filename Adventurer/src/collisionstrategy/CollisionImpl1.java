package collisionstrategy;

import adventure.AdventurerSystem;
import adventure.Direction;
import adventure.Sprite;
import role.Role;
import treasurechest.TreasureChest;

public class CollisionImpl1 implements CollisionStrategy {
    private AdventurerSystem adventurerSystem;

    public CollisionImpl1(AdventurerSystem adventurerSystem) {
        this.adventurerSystem = adventurerSystem;
    }

    @Override
    public boolean checkCollision(Direction direction, Role role) {
        int x = role.getX();
        int y = role.getY();
        boolean isCollision = false;

        switch (direction) {
            case UP:
                if (checkAboveCollision(x, y, role)) {
                    isCollision = true;
                }
                break;
            case DOWN:
                if (checkBelowCollision(x, y, role)) {
                    isCollision = true;
                }
                break;
            case LEFT:
                if (checkLeftCollision(x, y, role)) {
                    isCollision = true;
                }
                break;
            case RIGHT:
                if (checkRightCollision(x, y, role)) {
                    isCollision = true;
                }
        }
        return isCollision;
    }

    public boolean checkAboveCollision(int x, int y, Role role) {
        for (Sprite sprite : adventurerSystem.getSprites()) {
            if (sprite.getX() == x - 1 && sprite.getY() == y) {
                if (sprite instanceof TreasureChest) {
                    adventurerSystem.useTreasureChest(x - 1, y, role);
                } else {
                    return true;
                }
            }
        }
        adventurerSystem.getMap()[x][y] = " ";
        return false;
    }

    public boolean checkBelowCollision(int x, int y, Role role) {
        for (Sprite sprite : adventurerSystem.getSprites()) {
            if (sprite.getX() == x + 1 && sprite.getY() == y) {
                if (sprite instanceof TreasureChest) {
                    adventurerSystem.useTreasureChest(x + 1, y, role);
                } else {
                    return true;
                }
            }
        }
        adventurerSystem.getMap()[x][y] = " ";
        return false;
    }

    public boolean checkLeftCollision(int x, int y, Role role) {
        for (Sprite sprite : adventurerSystem.getSprites()) {
            if (sprite.getX() == x && sprite.getY() == y - 1) {
                if (sprite instanceof TreasureChest) {
                    adventurerSystem.useTreasureChest(x, y - 1, role);
                } else {
                    return true;
                }
            }
        }
        adventurerSystem.getMap()[x][y] = " ";
        return false;
    }

    public boolean checkRightCollision(int x, int y, Role role) {
        for (Sprite sprite : adventurerSystem.getSprites()) {
            if (sprite.getX() == x && sprite.getY() == y + 1) {
                if (sprite instanceof TreasureChest) {
                    adventurerSystem.useTreasureChest(x, y + 1, role);
                } else {
                    return true;
                }
            }
        }
        adventurerSystem.getMap()[x][y] = " ";
        return false;
    }
}
