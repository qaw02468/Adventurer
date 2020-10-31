package adventure;

import collisionstrategy.CollisionImpl1;
import collisionstrategy.CollisionStrategy;

import java.util.Scanner;

public class Main {
    private static Scanner input = new Scanner(System.in);
    private static AdventurerSystem adventurerSystem = new AdventurerSystem();
    private static CollisionStrategy collisionStrategy = new CollisionImpl1(adventurerSystem);

    public static void main(String[] args) {
        gameStart();
    }

    public static void gameStart() {
        int inputMapSize;
        String playerName;

        System.out.println("==========歡迎遊玩冒險者==========");
        System.out.println("請選擇地圖大小");

        inputMapSize = input.nextInt();
        System.out.println("請輸入玩家名稱");
        playerName = input.next();

        adventurerSystem.setCollisionStrategy(collisionStrategy);
        adventurerSystem.start(inputMapSize, playerName);
        System.out.println("==========遊戲結束！==========");
        System.out.println("玩家總共堅持了 " + adventurerSystem.getRound() + " 回合");

    }
}
