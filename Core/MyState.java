package Core;

import Fsm.State;

public class MyState extends State {
    String name;

    public MyState(String name) {
        super(name);
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}