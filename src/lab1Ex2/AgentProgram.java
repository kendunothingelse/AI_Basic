package lab1Ex2;

import java.util.Random;

import lab1Ex2.Environment.LocationState;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		// TODO
		Random rd = new Random();
		Action[] move = { Environment.MOVE_LEFT, Environment.MOVE_RIGHT, Environment.MOVE_UP, Environment.MOVE_DOWN };
		Action ac = move[rd.nextInt(move.length)];

		if (p.getLocationState() == LocationState.DIRTY) {
			ac = Environment.SUCK_DIRT;
		} else {
			switch (p.getAgentLocation()) {
			case Environment.LOCATION_A:
				if (ac.equals(Environment.MOVE_LEFT)) {
					p.setAgentLocation(Environment.LOCATION_B);
				} else if (ac.equals(Environment.MOVE_DOWN)) {
					p.setAgentLocation(Environment.LOCATION_C);
				}
				break;
			case Environment.LOCATION_B:
				if (ac.equals(Environment.MOVE_RIGHT)) {
					p.setAgentLocation(Environment.LOCATION_A);
				} else if (ac.equals(Environment.MOVE_DOWN)) {
					p.setAgentLocation(Environment.LOCATION_D);
				}
				break;
			case Environment.LOCATION_C:
				if (ac.equals(Environment.MOVE_LEFT)) {
					p.setAgentLocation(Environment.LOCATION_D);
				} else if (ac.equals(Environment.MOVE_UP)) {
					p.setAgentLocation(Environment.LOCATION_A);
				}
				break;
			case Environment.LOCATION_D:
				if (ac.equals(Environment.MOVE_RIGHT)) {
					p.setAgentLocation(Environment.LOCATION_C);
				} else if (ac.equals(Environment.MOVE_UP)) {
					p.setAgentLocation(Environment.LOCATION_B);
				}
				break;
			default:
				ac = NoOpAction.NO_OP;
				break;
			}
		}

		return ac;

	}
}