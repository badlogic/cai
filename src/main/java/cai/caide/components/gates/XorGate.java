package cai.caide.components.gates;

import cai.caide.components.Workspace;

public class XorGate extends BinaryGate {
	public XorGate(Workspace workspace) {
		super(workspace);
	}

	@Override
	protected boolean eval(boolean i1, boolean i2) {
		return i1 ^ i2;
	}
}
