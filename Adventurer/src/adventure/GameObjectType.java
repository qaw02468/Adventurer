package adventure;

public enum GameObjectType {
    MONSTER("M"), TREASURE_CHEST("◆"), OBSTACLE("□"), HERO_UP("↑"),
    HERO_DOWN("↓"), HERO_RIGHT("→"), HERO_LEFT("←");

    String patten;

    GameObjectType(String patten) {
        this.patten = patten;
    }

    public String getPatten() {
        return patten;
    }
}
