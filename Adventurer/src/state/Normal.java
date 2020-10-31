package state;


import role.Role;

public class Normal extends State {
    public Normal(Role role) {
        super.setName("普通");
        super.setRole(role);
    }

    @Override
    public void move() {
        getRole().makeSelection();
    }

    @Override
    public void effect() {

    }
}
