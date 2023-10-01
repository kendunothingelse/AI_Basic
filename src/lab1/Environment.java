package lab1;

import java.util.Random;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("MOVE_DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";
//	public static final String[] LOCATION = {"C","D"};

	public static final String[] AGENT_LOC = { LOCATION_A, LOCATION_B, LOCATION_C, LOCATION_D };

	// enum
	// state
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
		Random rd = new Random();
		int index = rd.nextInt(Environment.AGENT_LOC.length);// Agent do a random move
		if (action == Environment.SUCK_DIRT) {
			envState.setLocationState(envState.getAgentLocation(), LocationState.CLEAN);
		}
		if (action == MOVE_LEFT) {
			envState.setAgentLocation(LOCATION_A);
		} else {
			envState.setAgentLocation(LOCATION_C);
		}
		if (action == MOVE_RIGHT) {
			envState.setAgentLocation(LOCATION_B);
		} else {
			envState.setAgentLocation(LOCATION_D);
		}
		if (action == MOVE_UP) {
			envState.setAgentLocation(LOCATION_A);
		} else {
			envState.setAgentLocation(LOCATION_B);
		}
		if (action == MOVE_DOWN) {
			envState.setAgentLocation(LOCATION_C);
		} else {
			envState.setAgentLocation(LOCATION_D);
		}
//set agent location
		switch (index) {
		case 0:
			envState.setAgentLocation(Environment.LOCATION_A);
			break;
		case 1:
			envState.setAgentLocation(Environment.LOCATION_B);
			break;
		case 2:
			envState.setAgentLocation(Environment.LOCATION_C);
			break;
		case 3:
			envState.setAgentLocation(Environment.LOCATION_D);
			break;
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

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_C) == LocationState.CLEAN
						&& (es.getLocationState(LOCATION_D) == LocationState.CLEAN)))
			isDone = true;// if all squares are clean, then agent do not need to do any action
		es.display();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
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
