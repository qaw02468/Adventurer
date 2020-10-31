package state;

public class Confusion extends State {

    public Confusion() {
        super.setName("混亂");
        super.setCount(3);
    }

    @Override
    public void move() {
        effect();
    }

    @Override
    public void effect() {
        int randomSelect = (int) (Math.random() * 2);

        if (getCount() != 0) {
            System.out.println("=======混亂=======");

            if (randomSelect == 0) {
                getRole().onlyMoveUpOrDown();
            } else {
                getRole().onlyMoveRightOrLeft();
            }
            setCount(getCount() - 1);
        } else {
            getRole().setState(new Normal(getRole()));
        }
    }

}
