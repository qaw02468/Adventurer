package treasurechest;

import adventure.GameObjectType;
import state.*;

public class TreasureChestFactory {

    public TreasureChest createTreasureChest(int[] coordinate) {
        int probability = (int) (Math.random() * 100) + 1;
        TreasureChest treasureChest;

        if (probability <= 10) {
            treasureChest = new TreasureChest("無敵星星", new Invincible(), coordinate,
                    GameObjectType.TREASURE_CHEST);

        } else if (probability <= 35) {
            treasureChest = new TreasureChest("毒藥", new Poisoning(), coordinate,
                    GameObjectType.TREASURE_CHEST);

        } else if (probability <= 55) {
            treasureChest = new TreasureChest("加速藥水", new Accelerate(), coordinate,
                    GameObjectType.TREASURE_CHEST);

        } else if (probability <= 70) {
            treasureChest = new TreasureChest("補血罐", new Restore(), coordinate,
                    GameObjectType.TREASURE_CHEST);

        } else if (probability <= 80) {
            treasureChest = new TreasureChest("惡魔果實", new Confusion(), coordinate,
                    GameObjectType.TREASURE_CHEST);

        } else if (probability <= 90) {
            treasureChest = new TreasureChest("王者之印", new Accumulate(), coordinate,
                    GameObjectType.TREASURE_CHEST);

        } else {
            treasureChest = new TreasureChest("任意門", new Teleport(), coordinate,
                    GameObjectType.TREASURE_CHEST);

        }

        return treasureChest;
    }
}
