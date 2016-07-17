package cai.caide.components.gates;

import cai.caide.components.Component;
import cai.caide.components.Slot;
import cai.caide.components.Workspace;
import cai.caide.components.types.Bit;
import com.badlogic.gdx.utils.Array;

public abstract class BinaryGate extends Component {
	final Array<Slot> inputs = new Array<>();
	final Array<Slot> outputs = new Array<>();

	final Slot i1 = new Slot(this, Bit.class, "input1");
	final Slot i2 = new Slot(this, Bit.class, "input2");
	final Slot o = new Slot(this, Bit.class, "output");

	final boolean[] values = new boolean[3];

	public BinaryGate(Workspace workspace) {
		super(workspace);
		inputs.add(i1);
		inputs.add(i2);
		outputs.add(o);
		width = 80;
		height = 50;
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
		if (slot == i1) return values[0];
		if (slot == i2) return values[1];
		if (slot == o) return values[2];
		return false;
	}

	@Override
	public void event(Component sourceComponent, Slot sourceSlot, Slot targetSlot, Object value) {
		boolean v = (Boolean) value;
		if (targetSlot == i1) values[0] = v;
		if (targetSlot == i2) values[1] = v;
		boolean old = values[2];
		values[2] = eval(values[0], values[1]);
		if (values[2] != old) {
			workspace.sendEvent(o, values[2]);
		}
	}

	protected abstract boolean eval(boolean i1, boolean i2);

	@Override
	public void update(float delta) {
	}
}
