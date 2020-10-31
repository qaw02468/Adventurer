package adventure;

import collisionstrategy.CollisionStrategy;
import role.Hero;
import role.Monster;
import role.Role;
import treasurechest.TreasureChest;
import treasurechest.TreasureChestFactory;

import java.util.ArrayList;
import java.util.List;

public class AdventurerSystem {
    private int round = 0;
    private boolean gameOver = false;
    private String[][] map;
    private CollisionStrategy collisionStrategy;
    private List<Sprite> sprites = new ArrayList<>();
    private List<Role> heroes = new ArrayList<>();
    private List<Role> monsters = new ArrayList<>();
    private List<TreasureChest> treasureChests = new ArrayList<>();

    public AdventurerSystem() {
    }

    public void start(int mapSize, String playerName) {

        initialization(mapSize, playerName);
        displayMap();

        while (!gameOver) {
            for (Role hero : heroes) {
                System.out.println("輪到 " + hero.getName() + " 了 " + hero);

                hero.move();
                removeDeadMonster();
                updateMap();
                displayMap();
            }

            for (Role monster : monsters) {
                delay(2000);
                monster.move();
                checkHeroIsDead();
                updateMap();
                displayMap();
            }

            round++;
            setUpMonster();
            setUpTreasureChest();
        }

    }

    public void initialization(int mapSize, String playerName) {
        map = new String[mapSize][mapSize];

        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = " ";
            }
        }
        setUpObstacle();
        setUpHero(playerName);
        setUpMonster();
        setUpTreasureChest();
    }

    public void displayMap() {
        for (String[] strings : map) {
            System.out.print("=");
        }

        for (String[] strings : map) {
            System.out.println();
            for (int j = 0; j < map.length; j++) {
                System.out.print(strings[j]);
            }
        }
        System.out.println();

        for (String[] strings : map) {
            System.out.print("=");
        }
        System.out.println();
    }

    public void setUpHero(String name) {
        int coordinate[] = generateNoUsedCoordinate();

        Hero hero = new Hero(name, 300, 99999, coordinate, this, GameObjectType.HERO_RIGHT);

        heroes.add(hero);
        sprites.add(hero);
        map[coordinate[0]][coordinate[1]] = GameObjectType.HERO_RIGHT.getPatten();

    }

    public void setUpMonster() {
        int coordinate[] = generateNoUsedCoordinate();

        Monster monster = new Monster("怪物", 1, 50, coordinate, this, GameObjectType.MONSTER);

        monsters.add(monster);
        sprites.add(monster);
        map[coordinate[0]][coordinate[1]] = GameObjectType.MONSTER.getPatten();
    }

    public void setUpTreasureChest() {
        int coordinate[] = generateNoUsedCoordinate();

        TreasureChest treasureChest = new TreasureChestFactory().createTreasureChest(coordinate);

        treasureChests.add(treasureChest);
        sprites.add(treasureChest);
        map[coordinate[0]][coordinate[1]] = GameObjectType.TREASURE_CHEST.getPatten();
    }

    public void setUpObstacle() {
        for (int i = 0; i <= map.length * 2; i++) {
            int coordinate[] = generateObstacleCoordinate();
            sprites.add(new Obstacle(coordinate, GameObjectType.OBSTACLE));
            map[coordinate[0]][coordinate[1]] = GameObjectType.OBSTACLE.getPatten();
        }
    }

    public int[] generateNoUsedCoordinate() {
        do {
            int x = (int) (Math.random() * map.length);
            int y = (int) (Math.random() * map.length);

            for (Sprite sprite : sprites) {
                if (sprite.getX() != x && sprite.getY() != y) {
                    return new int[]{x, y};
                }
            }
        } while (true);
    }

    public int[] generateObstacleCoordinate() {
        do {
            int x = (int) (Math.random() * map.length);
            int y = (int) (Math.random() * map.length);

            if (x == 0 || y == 0 || x == map.length - 1 ||
                    y == map.length - 1) {
                if (searchThisCoordinateSprite(x, y) == null) {
                    return new int[]{x, y};
                }
            } else {
                if (searchThisCoordinateSprite(x + 1, y) == null &&
                        searchThisCoordinateSprite(x - 1, y) == null &&
                        searchThisCoordinateSprite(x, y + 1) == null &&
                        searchThisCoordinateSprite(x, y - 1) == null) {
                    return new int[]{x, y};
                }
            }
        } while (true);
    }

    public void updateMap() {
        for (int i = sprites.size() - 1; i > 0; i--) {
            if (!sprites.get(i).getExist()) {
                sprites.remove(i);
            }
        }

        for (Sprite sprite : sprites) {
            map[sprite.getX()][sprite.getY()] = sprite.getPatten();
        }
    }

    public boolean checkCollision(Direction direction, Role role) {
        return collisionStrategy.checkCollision(direction, role);
    }

    public void useTreasureChest(int x, int y, Role role) {
        for (TreasureChest treasureChest : treasureChests) {
            if (treasureChest.getX() == x &&
                    treasureChest.getY() == y) {

                System.out.println(role.getName() + " 獲得了 " + treasureChest.getName() +
                        " 效果為 " + treasureChest.getStatusName());

                role.setState(treasureChest.useTreasureChest());
                role.getState().setRole(role);
                treasureChests.remove(treasureChest);
                treasureChest.setExist(false);
                map[x][y] = " ";
                break;
            }
        }
    }

    public Sprite searchThisCoordinateSprite(int x, int y) {
        for (Sprite sprite : sprites) {
            if (sprite.getX() == x && sprite.getY() == y) {
                return sprite;
            }
        }
        return null;
    }

    public void removeDeadMonster() {
        for (int i = monsters.size() - 1; i > 0; i--) {
            if (monsters.get(i).isDead()) {
                delay(1000);
                System.out.println(monsters.get(i).getName() + " 已死亡 ");
                map[monsters.get(i).getX()][monsters.get(i).getY()] = " ";
                monsters.get(i).setExist(false);
                monsters.remove(i);
            }
        }
    }

    public void checkHeroIsDead() {
        for (Role hero : heroes) {
            if (hero.isDead()) {
                hero.setExist(false);
                gameOver = true;
            }
        }
    }

    public void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.getMessage();
        }
    }

    public List<Role> getHeroes() {
        return heroes;
    }

    public List<Role> getMonsters() {
        return monsters;
    }

    public String[][] getMap() {
        return map;
    }

    public int getRound() {
        return round;
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    public void setCollisionStrategy(CollisionStrategy collisionStrategy) {
        this.collisionStrategy = collisionStrategy;
    }
}
