package lab1;

import lab1.Environment.LocationState;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		// TODO
		Action ac = null;
		if (p.getLocationState() == LocationState.DIRTY) {
			ac = Environment.SUCK_DIRT;
		} else {
			switch (p.getAgentLocation()) {
			case Environment.LOCATION_A:
				ac = Environment.MOVE_LEFT;
				break;
			case Environment.LOCATION_B:
				ac = Environment.MOVE_RIGHT;
				break;
			default:
				ac = NoOpAction.NO_OP;
				break;
			}
		}

		return ac;

	}
}