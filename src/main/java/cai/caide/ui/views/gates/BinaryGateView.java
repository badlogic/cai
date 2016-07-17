package cai.caide.ui.views.gates;

import cai.caide.components.Component;
import cai.caide.ui.ComponentView;
import cai.caide.ui.WorkspaceUI;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public abstract class BinaryGateView<T extends Component> extends ComponentView<T> {
	final String label;
	final GlyphLayout layout = new GlyphLayout();

	public BinaryGateView(WorkspaceUI ui, T gate, String label) {
		super(ui, gate);
		this.label = label;
	}

	@Override
	public void render(float delta, SpriteBatch batch, ShapeRenderer renderer) {
		batch.end();

		float x = component.getPosition().x, y = component.getPosition().y;
		float w = component.getWidth(), h = component.getHeight();

		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.setColor(1, 1, 1, 1);
		renderer.rect(component.getPosition().x, component.getPosition().y, component.getWidth(), component.getHeight());
		renderer.setColor(0.5f, 0.7f, 1, 1);
		renderer.circle(x - 5, y + 10, 5);
		renderer.circle(x - 5, y + h - 10, 5);
		renderer.circle(x + w + 5, y + h / 2, 5);
		renderer.end();

		batch.begin();

		BitmapFont font = ui.getFont(16);
		layout.setText(font, label, Color.BLACK, w, Align.center, false);
		font.draw(batch, layout, x, y + h / 2 + layout.height / 2);
	}

	@Override
	public void keyDown(int key) {

	}

	@Override
	public void keyUp(int key) {

	}

	@Override
	public void typed(char c) {

	}

	@Override
	public void mouseMove(float x, float y) {

	}

	@Override
	public void mouseDrag(float x, float y) {

	}

	@Override
	public void mouseClick(float x, float y) {

	}

	@Override
	public void mouseScroll(float scroll) {

	}

	@Override
	public Rectangle getBounds() {
		return null;
	}

	@Override
	public Array<Rectangle> getInputSlotBounds() {
		return null;
	}

	@Override
	public Array<Rectangle> getOutputSlotBounds() {
		return null;
	}
}
