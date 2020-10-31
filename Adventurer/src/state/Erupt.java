package state;

public class Erupt extends State {

    public Erupt() {
        super.setName("爆發");
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
            setCount(getCount() - 1);
        } else {
            State teleport = new Teleport();
            teleport.setRole(getRole());
            getRole().setState(teleport);
        }

    }


}
