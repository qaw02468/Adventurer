package state;

public class Teleport extends State {

    public Teleport() {
        super.setName("瞬身");
        super.setCount(1);
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
            int[] coordinate = getRole().getRandomCoordinate();

            System.out.println("=======傳送=======");

            getRole().getMap()[getRole().getX()][getRole().getY()] = " ";
            getRole().setX(coordinate[0]);
            getRole().setY(coordinate[1]);
            getRole().setState(new Normal(getRole()));
        }
    }

}
