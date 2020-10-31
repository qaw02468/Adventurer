package role;


import adventure.AdventurerSystem;
import adventure.GameObjectType;
import adventure.Sprite;
import attackstrategy.AttackStrategy;
import state.Normal;
import state.State;

import java.util.List;

public abstract class Role extends Sprite {
    private AdventurerSystem adventurer;
    private String name;
    private int originalHp;
    private int hp;
    private int damage;
    private AttackStrategy attackStrategy;
    private State state = new Normal(this);

    public Role(String name, int hp, int damage, int[] position,
                AdventurerSystem adventurer, GameObjectType gameObjectType) {
        super(position, gameObjectType);
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.adventurer = adventurer;
        this.originalHp = hp;
    }

    public abstract void moveUpAndDown(int moveSelect);

    public abstract void moveLeftAndRight(int moveSelect);

    public abstract void makeSelection();

    public abstract void onlyMoveUpOrDown();

    public abstract void onlyMoveRightOrLeft();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public String[][] getMap() {
        return adventurer.getMap();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void move() {
        state.move();
    }

    public AdventurerSystem getAdventurerSystem() {
        return adventurer;
    }

    public Sprite searchThisCoordinateSprite(int x, int y) {
        return adventurer.searchThisCoordinateSprite(x, y);
    }

    public List<Role> getMonsters() {
        return adventurer.getMonsters();
    }

    public List<Role> getHeroes() {
        return adventurer.getHeroes();
    }

    public int getOriginalHp() {
        return originalHp;
    }

    public boolean isDead() {
        return getHp() <= 0;
    }

    public int[] getRandomCoordinate() {
        return adventurer.generateNoUsedCoordinate();
    }

    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }

    @Override
    public String toString() {
        return "[" + getState().getName() + " " + getState().getCount() + " 回合" +
                ":" + "]" + " (HP: " + getHp() + "/" + getOriginalHp() + ")";
    }


}
