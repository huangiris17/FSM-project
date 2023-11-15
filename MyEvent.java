import Fsm.Event;

public class MyEvent extends Event {
    String name;

    public MyEvent(String name) {
        super(name);
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}
