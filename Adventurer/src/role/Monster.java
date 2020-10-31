package role;

import adventure.AdventurerSystem;
import adventure.Direction;
import adventure.GameObjectType;
import adventure.Sprite;
import attackstrategy.AttackAround;
import state.Erupt;
import state.Invincible;

public class Monster extends Role {
    private final int UP = 1;
    private final int DOWN = 2;
    private final int LEFT = 3;
    private final int RIGHT = 4;

    public Monster(String name, int hp, int damage, int[] position,
                   AdventurerSystem adventurer, GameObjectType gameObjectType) {
        super(name, hp, damage, position, adventurer, gameObjectType);
        setAttackStrategy(new AttackAround(this));
    }

    @Override
    public void makeSelection() {
        Direction heroDirection = checkRoundHeroDirection();

        if (heroDirection != null) {
            getAttackStrategy().attack(heroDirection);
            return;
        }

        int randomSelect = (int) (Math.random() * 4) + 1;
        if (randomSelect < 3) {
            moveUpAndDown(randomSelect);
        } else {
            moveLeftAndRight(randomSelect);
        }
    }


    @Override
    public void onlyMoveUpOrDown() {
        int randomSelect = (int) (Math.random() * 2) + 1;

        moveUpAndDown(randomSelect);
    }

    @Override
    public void onlyMoveRightOrLeft() {
        int randomSelect = (int) (Math.random() * 2) + 3;

        moveLeftAndRight(randomSelect);
    }

    @Override
    public void moveUpAndDown(int moveSelect) {
        int x = getX();

        switch (moveSelect) {
            case UP:
                if (x - 1 < 0) {
                    move();
                } else {
                    if (!getAdventurerSystem().checkCollision(Direction.UP, this)) {
                        setX(x - 1);
                    }
                }
                break;
            case DOWN:
                if (x + 1 >= getMap().length) {
                    move();
                } else {
                    if (!getAdventurerSystem().checkCollision(Direction.DOWN, this)) {
                        setX(x + 1);
                    }
                }
                break;
        }
    }

    @Override
    public void moveLeftAndRight(int moveSelect) {
        int y = getY();

        switch (moveSelect) {
            case LEFT:
                if (y - 1 < 0) {
                    move();
                } else {
                    if (!getAdventurerSystem().checkCollision(Direction.LEFT, this)) {
                        setY(y - 1);
                    }
                }
                break;
            case RIGHT:
                if (y + 1 >= getMap().length) {
                    move();
                } else {
                    if (!getAdventurerSystem().checkCollision(Direction.RIGHT, this)) {
                        setY(y + 1);
                    }
                }
                break;
        }
    }

    public Direction checkRoundHeroDirection() {
        Sprite target;

        target = getAdventurerSystem().searchThisCoordinateSprite(getX() - 1,
                getY());
        if (target instanceof Hero) {
            return Direction.UP;
        }
        target = getAdventurerSystem().searchThisCoordinateSprite(getX() + 1,
                getY());
        if (target instanceof Hero) {
            return Direction.DOWN;
        }
        target = getAdventurerSystem().searchThisCoordinateSprite(getX(),
                getY() - 1);
        if (target instanceof Hero) {
            return Direction.LEFT;
        }
        target = getAdventurerSystem().searchThisCoordinateSprite(getX(),
                getY() + 1);
        if (target instanceof Hero) {
            getAttackStrategy().attack(Direction.RIGHT);
            return Direction.RIGHT;
        }
        return null;
    }
}

