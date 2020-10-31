package state;

import role.Role;

public abstract class State {
    private Role role;
    private String name;
    private int count;

    public State() {

    }

    public abstract void move();

    public abstract void effect();

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
