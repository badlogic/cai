package cai.caide.ui;

import cai.caide.components.Component;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public abstract class ComponentView<T extends Component> {
	protected final WorkspaceUI ui;
	protected final T component;

	public ComponentView(WorkspaceUI ui, T component) {
		this.ui = ui;
		this.component = component;
	}

	public T getComponent() {
		return component;
	}

	public abstract void render(float delta, SpriteBatch batch, ShapeRenderer renderer);
	public abstract void keyDown(int key);
	public abstract void keyUp(int key);
	public abstract void typed(char c);
	public abstract void mouseMove(float x, float y);
	public abstract void mouseDrag(float x, float y);
	public abstract void mouseClick(float x, float y);
	public abstract void mouseScroll(float scroll);
	public abstract Rectangle getBounds();
	public abstract Array<Rectangle> getInputSlotBounds();
	public abstract Array<Rectangle> getOutputSlotBounds();
}
