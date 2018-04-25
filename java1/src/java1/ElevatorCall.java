package java1;

public class ElevatorCall {

	private ElevatorDoorsListener listener;
	private int originLevel;
	
	public ElevatorCall(ElevatorDoorsListener listener, int personLevel) {
		this.listener = listener;
		this.originLevel = personLevel;
	}

	public ElevatorDoorsListener getListener() {
		return listener;
	}
	
	public int getOriginLevel() {
		return originLevel;
	}

	public String toString() {
		return listener.toString();
	}
	
}
