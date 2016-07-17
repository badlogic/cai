package cai.caide.ui;

import cai.caide.components.Component;
import cai.caide.components.Connection;
import cai.caide.components.Workspace;
import cai.caide.ui.views.gates.AndGateView;
import cai.caide.ui.views.gates.NandGateView;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.ObjectMap;

public class WorkspaceUI extends InputAdapter implements Workspace.WorkspaceListener, Disposable {
	final SpriteBatch batch;
	final ShapeRenderer shapeRenderer;
	final OrthographicCamera camera;
	final Array<Component> components = new Array<>();
	final ObjectMap<Component, ComponentView> views = new ObjectMap<>();
	final ObjectMap<Class<?>, ComponentViewFactory> viewFactories = new ObjectMap<>();
	final IntMap<BitmapFont> fonts = new IntMap<BitmapFont>();

	ComponentView selectedView;

	public WorkspaceUI() {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		registerViewFactories();
	}

	private void registerViewFactories() {
		registerViewFactory(new AndGateView.AndGateViewFactory());
		registerViewFactory(new NandGateView.NandGateViewFactory());
	}

	public void registerViewFactory(ComponentViewFactory<? extends Component> factory) {
		viewFactories.put(factory.getComponentType(), factory);
	}

	public void resize(int width, int height) {
		camera.setToOrtho(false);
	}

	public void render(float delta) {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		batch.begin();
		for (Component c: components) {
			ComponentView v = views.get(c);
			if (v != null) {
				v.render(delta, batch, shapeRenderer);
			}
		}
		batch.end();
	}

	@Override
	public boolean keyDown(int keycode) {
		return super.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {
		return super.keyUp(keycode);
	}

	@Override
	public boolean keyTyped(char character) {
		return super.keyTyped(character);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return super.touchUp(screenX, screenY, pointer, button);
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return super.touchDragged(screenX, screenY, pointer);
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return super.mouseMoved(screenX, screenY);
	}

	@Override
	public boolean scrolled(int amount) {
		return super.scrolled(amount);
	}

	@Override
	public void addedComponent(Component c) {
		components.add(c);
		ComponentViewFactory factory = viewFactories.get(c.getClass());
		if (factory != null) {
			views.put(c, factory.newComponentView(this, c));
		}
	}

	@Override
	public void removedComponent(Component c) {
		components.removeValue(c, true);
		views.remove(c);
	}

	@Override
	public void addedConnection(Connection c) {
	}

	@Override
	public void removedConnection(Connection c) {
	}

	@Override
	public void dispose() {
		batch.dispose();
		shapeRenderer.dispose();
		for (BitmapFont f: fonts.values()) {
			f.dispose();
		}
	}

	public BitmapFont getFont(int size) {
		BitmapFont font = fonts.get(size);
		if (font == null) {
			FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("apple2.ttf"));
			FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
			param.size = size;
			font = gen.generateFont(param);
			gen.dispose();
			fonts.put(size, font);
		}
		return font;
	}
}
