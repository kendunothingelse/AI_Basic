package lab1Ex2;

import java.util.Random;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";
	int score = 0;

	// enum
	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;

	public Environment(LocationState locAState, LocationState locBState, LocationState locCState,
			LocationState locDState) {
		envState = new EnvironmentState(locAState, locBState, locCState, locDState);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		// TODO
		this.agent = agent;
		envState.setAgentLocation(location);
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		// TODO

		if (action == Environment.SUCK_DIRT) {
			envState.setLocationState(envState.getAgentLocation(), LocationState.CLEAN);
		} else {
			//Check which locations allow which actions
			switch (envState.getAgentLocation()) {
			case LOCATION_A:
				if (action == MOVE_RIGHT) {
					envState.setAgentLocation(LOCATION_B);
				} else if (action == MOVE_DOWN) {
					envState.setAgentLocation(LOCATION_C);
				}
				break;
			case LOCATION_B:
				if (action == MOVE_LEFT) {
					envState.setAgentLocation(LOCATION_A);
				} else if (action == MOVE_DOWN) {
					envState.setAgentLocation(LOCATION_D);
				}
				break;
			case LOCATION_C:
				if (action == MOVE_RIGHT) {
					envState.setAgentLocation(LOCATION_D);
				} else if (action == MOVE_UP) {
					envState.setAgentLocation(LOCATION_A);
				}
				break;
			case LOCATION_D:
				if (action == MOVE_LEFT) {
					envState.setAgentLocation(LOCATION_C);
				} else if (action == MOVE_UP) {
					envState.setAgentLocation(LOCATION_B);
				}
				break;
			}
		}
		return envState;
	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		// TODO
		String agentLocation = envState.getAgentLocation();
		LocationState state = envState.getLocationState(agentLocation);

		return new Percept(agentLocation, state);
	}

	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);
		/*Performance score:
		 * For agent SUCK: +500 points
		 * If the agent can't move: -100 points
		 * For other actions: -10 points*/
		if (anAction == SUCK_DIRT) {
			score += 500;
		} else if (agentLocation.equals(es.getAgentLocation())) {
			score -= 100;
		} else {
			score -= 10;
		}
		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN))
			isDone = true;// if both squares are clean, then agent do not need to do any action
		es.display();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("Score: " + score + " points");
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;
		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}
}
