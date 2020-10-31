package attackstrategy;

import adventure.Direction;
import adventure.Sprite;
import role.Role;
import state.Erupt;
import state.Invincible;

public class AttackAround implements AttackStrategy {
    private Role role;

    public AttackAround(Role role) {
        this.role = role;
    }

    @Override
    public void attack(Direction direction) {

        if (role.getState() instanceof Erupt) {
            eruptAttack();
        } else {
            switch (direction) {
                case UP:
                    normalAttack(role.searchThisCoordinateSprite(role.getX() - 1, role.getY()));
                    break;
                case DOWN:
                    normalAttack(role.searchThisCoordinateSprite(role.getX() + 1, role.getY()));
                    break;
                case LEFT:
                    normalAttack(role.searchThisCoordinateSprite(role.getX(), role.getY() - 1));
                    break;
                case RIGHT:
                    normalAttack(role.searchThisCoordinateSprite(role.getX(), role.getY() + 1));
                    break;
            }
        }

    }


    public void normalAttack(Sprite sprite) {
        int damage;
        Role attacked = null;

        if (sprite instanceof Role) {
            attacked = (Role) sprite;
        }

        if (role.getState() instanceof Erupt) {
            eruptAttack();
        } else {
            if (attacked.getState() instanceof Invincible) {
                damage = 0;
            } else {
                damage = role.getDamage();
            }
            attacked.setHp(attacked.getHp() - damage);

            System.out.println(role.getName() + " 對 " + attacked.getName() + " 造成了" + damage + " 傷害 ");
        }
    }

    public void eruptAttack() {
        int damage;

        for (Role attackedRole : role.getHeroes()) {
            damage = 50;
            if (attackedRole.getState() instanceof Invincible) {
                damage = 0;
            } else {
                attackedRole.setHp(attackedRole.getHp() - damage);

                System.out.println(role.getName() + " 對 " + attackedRole.getName() + " 造成了" + damage + " 傷害 ");
            }
        }
    }

}
