import Fsm.Action;
import Fsm.FSM;
import Fsm.Event;

class MyAction extends Action {
    int countR = 0;
    int countS = 0;

    public void execute(FSM fsm, Event event) {
        // This method is invoked whenever the corresponding transition occurs.
        String evt = event.getName();
        switch (evt) {
            case "RDATA":
                countR++;
                System.out.print("DATA received " + countR);
                break;
            case "SDATA":
                countS++;
                System.out.print("DATA sent " + countS);
                break;
            default:
                System.out.print("Event " + evt + " received, current State is " + fsm.currentState().getName());
        }
    }
}