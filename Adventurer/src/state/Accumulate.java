package state;

public class Accumulate extends State {

    public Accumulate() {
        super.setName("蓄力");
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
            System.out.println("=======蓄力中=======");
            setCount(getCount() - 1);
        } else {
            State status = new Erupt();

            status.setRole(getRole());
            super.getRole().setState(status);

        }

    }


}
