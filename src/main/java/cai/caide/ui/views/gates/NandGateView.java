package cai.caide.ui.views.gates;

import cai.caide.components.gates.AndGate;
import cai.caide.components.gates.NandGate;
import cai.caide.ui.ComponentViewFactory;
import cai.caide.ui.WorkspaceUI;

public class NandGateView extends BinaryGateView<NandGate> {

	public NandGateView(WorkspaceUI ui, NandGate gate) {
		super(ui, gate, "NAND");
	}

	public static class NandGateViewFactory implements ComponentViewFactory<NandGate> {
		@Override
		public NandGateView newComponentView(WorkspaceUI ui, NandGate component) {
			return new NandGateView(ui, component);
		}

		@Override
		public Class<NandGate> getComponentType() {
			return NandGate.class;
		}
	}
}