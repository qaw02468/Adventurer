package attackstrategy;

import adventure.Direction;
import adventure.Obstacle;
import adventure.Sprite;
import role.Role;
import state.Erupt;
import state.Invincible;

public class AttackStraightLine implements AttackStrategy {
    private Role role;
    private boolean isObstacle;

    public AttackStraightLine(Role role) {
        this.role = role;
    }

    @Override
    public void attack(Direction direction) {
        if (role.getState() instanceof Erupt) {
            eruptAttack();
        } else {
            isObstacle = false;

            switch (direction) {
                case UP:
                    for (int i = role.getX() - 1; i > 0; i--) {
                        attackUpOrDown(i);
                        if (isObstacle) {
                            return;
                        }
                    }
                    break;
                case DOWN:
                    for (int i = role.getX() + 1; i < role.getMap().length; i++) {
                        attackUpOrDown(i);
                        if (isObstacle) {
                            return;
                        }
                    }
                    break;
                case LEFT:
                    for (int i = role.getY() - 1; i > 0; i--) {
                        attackLeftOrRight(i);
                        if (isObstacle) {
                            return;
                        }
                    }
                    break;
                case RIGHT:
                    for (int i = role.getY() + 1; i < role.getMap().length; i++) {
                        attackLeftOrRight(i);
                        if (isObstacle) {
                            return;
                        }
                    }
                    break;
            }
        }
    }

    public void eruptAttack() {
        for (Role attacked : role.getMonsters()) {
            int damage = 50;
            if (attacked.getState() instanceof Invincible) {
                damage = 0;
            }
            attacked.setHp(attacked.getHp() - damage);
            System.out.println(role.getName() + " 對 " + attacked.getName() + " 造成了" + damage + " 傷害 ");
        }
    }

    public void attackLeftOrRight(int i) {
        Sprite sprite = role.searchThisCoordinateSprite(role.getX(), i);
        checkAttackedIsRoleThenAttack(sprite);
    }

    private void attackUpOrDown(int i) {
        Sprite sprite = role.searchThisCoordinateSprite(i, role.getY());
        checkAttackedIsRoleThenAttack(sprite);
    }

    private void checkAttackedIsRoleThenAttack(Sprite sprite) {
        Role attacked;
        int damage;

        if (sprite == null) {
            return;
        }

        if (sprite instanceof Role) {
            attacked = (Role) sprite;
            if (attacked.getState() instanceof Invincible) {
                damage = 0;
            } else {
                damage = role.getDamage();
            }
            attacked.setHp(attacked.getHp() - damage);
            System.out.println(role.getName() + " 對 " + attacked.getName() + " 造成了" + damage + " 傷害 ");
        }

        if (sprite instanceof Obstacle) {
            isObstacle = true;
        }
    }
}
