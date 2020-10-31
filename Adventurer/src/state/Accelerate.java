package state;

public class Accelerate extends State {

    public Accelerate() {
        super.setName("加速");
        super.setCount(3);
    }

    @Override
    public void move() {
        getRole().makeSelection();
        effect();
    }

    @Override
    public void effect() {
        if (getCount() != 0) {
            System.out.println("=======加速=======");
            System.out.println("獲得多一次的行動機會");
            getRole().makeSelection();
            setCount(getCount() - 1);
        } else {
            getRole().setState(new Normal(getRole()));
        }
    }

}
