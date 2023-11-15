import java.util.*;
import Fsm.Event;
import Fsm.FSM;
import Fsm.Transition;
import Fsm.FsmException;

public class Main {
        static Map<String, MyState> mapState;
        static Map<String, MyEvent> mapEvent;

        public static void main(String[] args) {
                mapState = new HashMap<>();
                mapEvent = new HashMap<>();
                buildState(mapState);
                buildEvent(mapEvent);

                FSM myFSM = new FSM("myFSM", mapState.get("LISTEN"));
                addTran(myFSM);

                Scanner sc = new Scanner(System.in);
                while (sc.hasNext()) {
                        String s1 = sc.next();
                        if (s1.equals("exit"))
                                break;
                        try {
                                Event curEvent = mapEvent.get(s1);
                                myFSM.doEvent(curEvent);
                        } catch (FsmException e) {
                                System.out.print(e.toString());
                        }
                }
        }

        private static void buildState(Map<String, MyState> mapState) {
                mapState.put("CLOSED", new MyState("CLOSED"));
                mapState.put("LISTEN", new MyState("LISTEN"));
                mapState.put("SYN_SENT", new MyState("SYN_SENT"));
                mapState.put("SYN_RCVD", new MyState("SYN_RCVD"));
                mapState.put("ESTABLISHED", new MyState("ESTABLISHED"));
                mapState.put("FIN_WAIT_1", new MyState("FIN_WAIT_1"));
                mapState.put("CLOSE_WAIT", new MyState("CLOSE_WAIT"));
                mapState.put("FIN_WAIT_2", new MyState("FIN_WAIT_2"));
                mapState.put("CLOSING", new MyState("CLOSING"));
                mapState.put("LAST_ACK", new MyState("LAST_ACK"));
                mapState.put("TIME_WAIT", new MyState("TIME_WAIT"));
        }

        private static void buildEvent(Map<String, MyEvent> mapEvent) {
                mapEvent.put("PASSIVE", new MyEvent("PASSIVE"));
                mapEvent.put("ACTIVE", new MyEvent("ACTIVE"));
                mapEvent.put("SYN", new MyEvent("SYN"));
                mapEvent.put("SYNACK", new MyEvent("SYNACK"));
                mapEvent.put("ACK", new MyEvent("ACK"));
                mapEvent.put("RDATA", new MyEvent("RDATA"));
                mapEvent.put("SDATA", new MyEvent("SDATA"));
                mapEvent.put("FIN", new MyEvent("FIN"));
                mapEvent.put("CLOSE", new MyEvent("CLOSE"));
                mapEvent.put("TIMEOUT", new MyEvent("TIMEOUT"));
        }

        private static void addTran(FSM myfsm) {
                // Transition(State cs, Event evt, State ns, Action act)
                Transition t1 = new Transition(mapState.get("LISTEN"), mapEvent.get("CLOSE"), mapState.get("CLOSED"),
                                new MyAction());
                Transition t2 = new Transition(mapState.get("SYN_SENT"), mapEvent.get("CLOSE"), mapState.get("CLOSED"),
                                new MyAction());
                Transition t3 = new Transition(mapState.get("CLOSE_WAIT"), mapEvent.get("CLOSE"),
                                mapState.get("LAST_ACK"),
                                new MyAction());
                Transition t4 = new Transition(mapState.get("SYN_RCVD"), mapEvent.get("CLOSE"),
                                mapState.get("FIN_WAIT_1"),
                                new MyAction());
                Transition t5 = new Transition(mapState.get("ESTABLISHED"), mapEvent.get("CLOSE"),
                                mapState.get("FIN_WAIT_1"),
                                new MyAction());
                Transition t6 = new Transition(mapState.get("LISTEN"), mapEvent.get("SYN"), mapState.get("SYN_RCVD"),
                                new MyAction());
                Transition t7 = new Transition(mapState.get("SYN_SENT"), mapEvent.get("SYN"), mapState.get("SYN_RCVD"),
                                new MyAction());
                Transition t8 = new Transition(mapState.get("LAST_ACK"), mapEvent.get("ACK"), mapState.get("CLOSED"),
                                new MyAction());
                Transition t9 = new Transition(mapState.get("CLOSING"), mapEvent.get("ACK"), mapState.get("TIME_WAIT"),
                                new MyAction());
                Transition t10 = new Transition(mapState.get("FIN_WAIT_1"), mapEvent.get("ACK"),
                                mapState.get("FIN_WAIT_2"),
                                new MyAction());
                Transition t11 = new Transition(mapState.get("SYN_RCVD"), mapEvent.get("ACK"),
                                mapState.get("ESTABLISHED"),
                                new MyAction());
                Transition t12 = new Transition(mapState.get("FIN_WAIT_2"), mapEvent.get("FIN"),
                                mapState.get("TIME_WAIT"),
                                new MyAction());
                Transition t13 = new Transition(mapState.get("FIN_WAIT_1"), mapEvent.get("FIN"),
                                mapState.get("CLOSING"),
                                new MyAction());
                Transition t14 = new Transition(mapState.get("ESTABLISHED"), mapEvent.get("FIN"),
                                mapState.get("CLOSE_WAIT"),
                                new MyAction());
                Transition t15 = new Transition(mapState.get("TIME_WAIT"), mapEvent.get("TIMEOUT"),
                                mapState.get("CLOSED"),
                                new MyAction());
                Transition t16 = new Transition(mapState.get("CLOSED"), mapEvent.get("ACTIVE"),
                                mapState.get("SYN_SENT"),
                                new MyAction());
                Transition t17 = new Transition(mapState.get("SYN_SENT"), mapEvent.get("SYNACK"),
                                mapState.get("ESTABLISHED"),
                                new MyAction());
                Transition t18 = new Transition(mapState.get("ESTABLISHED"), mapEvent.get("RDATA"),
                                mapState.get("ESTABLISHED"),
                                new MyAction());
                Transition t19 = new Transition(mapState.get("ESTABLISHED"), mapEvent.get("SDATA"),
                                mapState.get("ESTABLISHED"),
                                new MyAction());
                Transition t20 = new Transition(mapState.get("CLOSED"), mapEvent.get("PASSIVE"), mapState.get("LISTEN"),
                                new MyAction());
        }
}