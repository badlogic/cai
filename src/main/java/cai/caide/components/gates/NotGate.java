package cai.caide.components.gates;

import cai.caide.components.Component;
import cai.caide.components.Slot;
import cai.caide.components.Workspace;
import cai.caide.components.types.Bit;
import com.badlogic.gdx.utils.Array;

public class NotGate extends Component {
	final Array<Slot> inputs = new Array<Slot>();
	final Array<Slot> outputs = new Array<Slot>();
	final Slot i = new Slot(this, Bit.class, "input");
	final Slot o = new Slot(this, Bit.class, "output");

	final boolean[] values = new boolean[2];

	public NotGate(Workspace workspace) {
		super(workspace);
		inputs.add(i);
		inputs.add(o);
	}

	@Override
	public Array<Slot> getInputs() {
		return inputs;
	}

	@Override
	public Array<Slot> getOutputs() {
		return outputs;
	}

	@Override
	public boolean isSlotActive(Slot slot) {
		if (slot == i) return values[0];
		if (slot == o) return values[1];
		return false;
	}

	@Override
	public void event(Component sourceComponent, Slot sourceSlot, Slot targetSlot, Object value) {
		boolean v = (Boolean) value;
		if (targetSlot == i) values[0] = v;
		boolean oldValue = values[1];
		values[1] = !v;
		if (oldValue != values[1]) {
			workspace.sendEvent(o, values[1]);
		}
	}

	@Override
	public void update(float delta) {

	}
}
