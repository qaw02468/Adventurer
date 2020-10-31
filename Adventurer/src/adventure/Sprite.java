package adventure;

public class Sprite {
    private int coordinate[];
    private GameObjectType gameObjectType;
    private Boolean isExist = true;

    public Sprite(int[] coordinate, GameObjectType gameObjectType) {
        this.coordinate = coordinate;
        this.gameObjectType = gameObjectType;
    }

    public int getX() {
        return coordinate[0];
    }

    public int getY() {
        return coordinate[1];
    }

    public void setX(int x) {
        coordinate[0] = x;
    }

    public void setY(int y) {
        coordinate[1] = y;
    }

    public void setGameObjectType(GameObjectType gameObjectType) {
        this.gameObjectType = gameObjectType;
    }

    public String getPatten() {
        return gameObjectType.getPatten();
    }

    public Boolean getExist() {
        return isExist;
    }

    public GameObjectType getGameObjectType() {
        return gameObjectType;
    }

    public void setExist(Boolean exist) {
        isExist = exist;
    }
}
