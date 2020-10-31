package treasurechest;

import adventure.GameObjectType;
import adventure.Sprite;
import state.State;

public class TreasureChest extends Sprite {
    private String name;
    private State state;

    public TreasureChest(String name, State state, int[] position, GameObjectType gameObjectType) {
        super(position, GameObjectType.TREASURE_CHEST);
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public String getStatusName() {
        return state.getName();
    }

    public State useTreasureChest() {
        return state;
    }

}
