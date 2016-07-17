package cai.caide.components.gates;

import cai.caide.components.Workspace;

public class NandGate extends BinaryGate {

	public NandGate(Workspace workspace) {
		super(workspace);
	}

	@Override
	protected boolean eval(boolean i1, boolean i2) {
		return !(i2 && i2);
	}
}
