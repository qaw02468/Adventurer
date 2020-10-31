package role;

import adventure.AdventurerSystem;
import adventure.Direction;
import adventure.GameObjectType;
import attackstrategy.AttackStraightLine;

import java.util.Scanner;

public class Hero extends Role {
    private Scanner input = new Scanner(System.in);
    private boolean isObstacle;
    private final int UP = 1;
    private final int DOWN = 2;
    private final int LEFT = 3;
    private final int RIGHT = 4;


    public Hero(String name, int hp, int damage, int[] position
            , AdventurerSystem adventurer, GameObjectType gameObjectType) {
        super(name, hp, damage, position, adventurer, gameObjectType);

        setAttackStrategy(new AttackStraightLine(this));
    }

    @Override
    public void makeSelection() {
        int select;
        int moveSelect;

        System.out.println("請選擇 (1)移動 (2)攻擊");
        select = inputSelect(1, 2);
        if (select == 1) {
            System.out.println("請選擇 (1)上 (2)下 (3)左 (4)右");
            moveSelect = inputSelect(1, 4);

            if (moveSelect == UP || moveSelect == DOWN) {
                moveUpAndDown(moveSelect);
            } else {
                moveLeftAndRight(moveSelect);
            }
        } else {
            attack();
        }
    }

    @Override
    public void onlyMoveUpOrDown() {
        int moveSelect;

        System.out.println("請選擇 (1)上 (2)下 ");
        moveSelect = inputSelect(1, 2);
        moveUpAndDown(moveSelect);
    }

    @Override
    public void onlyMoveRightOrLeft() {
        int moveSelect;

        System.out.println("請選擇 (1)左 (2)右");
        moveSelect = inputSelect(1, 2);
        moveLeftAndRight(moveSelect + 2);
    }

    @Override
    public void moveUpAndDown(int moveSelect) {
        int roleCoordinateX = getX();
        switch (moveSelect) {

            case UP:
                if (roleCoordinateX - 1 < 0) {
                    System.out.println("超出地圖範圍，請重新選擇");
                    move();
                } else {
                    setGameObjectType(GameObjectType.HERO_UP);
                    if (!getAdventurerSystem().checkCollision(Direction.UP, this)) {
                        setX(roleCoordinateX - 1);
                    }
                }
                break;
            case DOWN:
                if (roleCoordinateX + 1 >= getMap().length) {
                    System.out.println("超出地圖範圍，請重新選擇");
                    move();
                } else {
                    setGameObjectType(GameObjectType.HERO_DOWN);
                    if (!getAdventurerSystem().checkCollision(Direction.DOWN, this)) {
                        setX(roleCoordinateX + 1);
                    }
                }
                break;
        }
    }

    @Override
    public void moveLeftAndRight(int moveSelect) {
        int roleCoordinateY = getY();

        switch (moveSelect) {
            case LEFT:
                if (roleCoordinateY - 1 < 0) {
                    System.out.println("超出地圖範圍，請重新選擇");
                    move();
                } else {
                    setGameObjectType(GameObjectType.HERO_LEFT);
                    if (!getAdventurerSystem().checkCollision(Direction.LEFT, this)) {
                        setY(roleCoordinateY - 1);
                    }
                }
                break;
            case RIGHT:
                if (roleCoordinateY + 1 >= getMap().length) {
                    System.out.println("超出地圖範圍，請重新選擇");
                    move();
                } else {
                    setGameObjectType(GameObjectType.HERO_RIGHT);
                    if (!getAdventurerSystem().checkCollision(Direction.RIGHT, this)) {
                        setY(roleCoordinateY + 1);
                    }
                }
                break;
        }
    }

    public void attack() {
        switch (getGameObjectType()) {
            case HERO_UP:
                getAttackStrategy().attack(Direction.UP);
                break;
            case HERO_DOWN:
                getAttackStrategy().attack(Direction.DOWN);
                break;
            case HERO_LEFT:
                getAttackStrategy().attack(Direction.LEFT);
                break;
            case HERO_RIGHT:
                getAttackStrategy().attack(Direction.RIGHT);
                break;
        }
    }

    private int inputSelect(int lowestOption, int maxOption) {
        int select;

        do {
            select = input.nextInt();
            if (select > maxOption || select < lowestOption) {
                System.out.println("只能輸入 " + lowestOption + " ~ " + maxOption + " 的數字");
            }
        } while (select > maxOption || select < lowestOption);

        return select;
    }
}
