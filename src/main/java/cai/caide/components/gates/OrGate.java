package cai.caide.components.gates;

import cai.caide.components.Workspace;

/**
 * Created by badlogic on 17/07/16.
 */
public class OrGate extends BinaryGate {
	public OrGate(Workspace workspace) {
		super(workspace);
	}

	@Override
	protected boolean eval(boolean i1, boolean i2) {
		return i1 || i2;
	}
}
