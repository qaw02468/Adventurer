package state;

public class Poisoning extends State {

    public Poisoning() {
        super.setName("中毒");
        super.setCount(3);
    }

    @Override
    public void move() {
        effect();
        getRole().makeSelection();
    }

    @Override
    public void effect() {
        if (getCount() != 0) {
            System.out.println("=======中毒=======");

            getRole().setHp(getRole().getHp() - 15);
            setCount(getCount() - 1);
        } else {
            getRole().setState(new Normal(getRole()));
        }

    }

}
