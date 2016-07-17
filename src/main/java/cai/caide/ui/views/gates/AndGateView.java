package cai.caide.ui.views.gates;

import cai.caide.components.gates.AndGate;
import cai.caide.ui.ComponentViewFactory;
import cai.caide.ui.WorkspaceUI;

public class AndGateView extends BinaryGateView<AndGate> {

	public AndGateView(WorkspaceUI ui, AndGate gate) {
		super(ui, gate, "AND");
	}

	public static class AndGateViewFactory implements ComponentViewFactory<AndGate> {
		@Override
		public AndGateView newComponentView(WorkspaceUI ui, AndGate component) {
			return new AndGateView(ui, component);
		}

		@Override
		public Class<AndGate> getComponentType() {
			return AndGate.class;
		}
	}
}
