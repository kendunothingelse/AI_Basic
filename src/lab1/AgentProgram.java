package lab1;

import java.util.Random;

import javax.tools.DocumentationTool.Location;

import lab1.Environment.LocationState;

public class AgentProgram {
	//
	public Action execute(Percept p) {// location, status
		// TODO
		Action action = null;
		Random rd = new Random();
		int index = rd.nextInt(Environment.AGENT_LOC.length);// Agent do a random move
		if (p.getLocationState() == LocationState.DIRTY) {
			action = Environment.SUCK_DIRT;
		} else {
			switch (p.getAgentLocation()) {
			// A
			case Environment.LOCATION_A: {
				if (index == 1) {
					action = Environment.MOVE_RIGHT;
				} else if (index == 2) {
					action = Environment.MOVE_DOWN;
				}
			}
				break;
			// B
			case Environment.LOCATION_B: {
				if (index == 0) {
					action = Environment.MOVE_LEFT;
				} else if (index == 3) {
					action = Environment.MOVE_DOWN;
				}
			}
				break;
			// C
			case Environment.LOCATION_C: {
				if (index == 0) {
					action = Environment.MOVE_UP;
				} else if (index == 3) {
					action = Environment.MOVE_RIGHT;
				}
			}
				break;
			// D
			case Environment.LOCATION_D: {
				if (index == 1) {
					action = Environment.MOVE_UP;
				} else if (index == 2) {
					action = Environment.MOVE_LEFT;
				}
			}
				break;
			default:
				action = NoOpAction.NO_OP;
				break;
			}
			switch (index) {
			case 0:
				p.setAgentLocation(Environment.LOCATION_A);
				break;
			case 1:
				p.setAgentLocation(Environment.LOCATION_B);
				break;
			case 2:
				p.setAgentLocation(Environment.LOCATION_C);
				break;
			case 3:
				p.setAgentLocation(Environment.LOCATION_D);
				break;
			}
		}

		return action;
	}

}