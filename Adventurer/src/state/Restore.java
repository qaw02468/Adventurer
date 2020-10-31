package state;

public class Restore extends State {

    public Restore() {
        super.setName("恢復");
        super.setCount(5);
    }

    @Override
    public void move() {
        effect();
        getRole().makeSelection();
    }

    @Override
    public void effect() {
        if (getCount() != 0 && getRole().getHp() == getRole().getOriginalHp()) {
            System.out.println("=======恢復=======");

            getRole().setHp(getRole().getHp() + 30);
            setCount(getCount() - 1);
        } else {
            getRole().setState(new Normal(getRole()));
        }
    }


}
