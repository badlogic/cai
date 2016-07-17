package cai.caide.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public abstract class Component {
	protected final Vector2 position = new Vector2();
	protected float width, height;
	protected final Workspace workspace;

	public Component(Workspace workspace) {
		this.workspace = workspace;
		workspace.addComponent(this);
	}

	public abstract Array<Slot> getInputs();

	public abstract Array<Slot> getOutputs();

	public Vector2 getPosition() {
		return position;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public abstract boolean isSlotActive(Slot slot);

	public abstract void event(Component sourceComponent, Slot sourceSlot, Slot targetSlot, Object value);

	public abstract void update(float delta);
}
