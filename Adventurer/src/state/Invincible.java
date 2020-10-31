package state;

public class Invincible extends State {

    public Invincible() {
        super.setName("無敵");
        super.setCount(2);
    }

    @Override
    public void move() {
        effect();
        getRole().makeSelection();
    }

    @Override
    public void effect() {
        if (getCount() != 0) {
            System.out.println("=======無敵=======");

            setCount(getCount() - 1);
        } else {
            getRole().setState(new Normal(getRole()));
        }
    }

}
