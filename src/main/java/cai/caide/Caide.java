package cai.caide;

import cai.caide.components.Workspace;
import cai.caide.components.gates.AndGate;
import cai.caide.ui.WorkspaceUI;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Caide extends ApplicationAdapter {
	Workspace workspace;
	WorkspaceUI ui;
	InputMultiplexer multiplexer;

	FrameBuffer fb;
	TextureRegion fbRegion;
	ShaderProgram shader;
	SpriteBatch batch;

	@Override
	public void create() {
		ui = new WorkspaceUI();
		workspace = new Workspace();
		workspace.addListener(ui);

		AndGate gate = new AndGate(workspace);
		gate.getPosition().set(100, 100);

		multiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(multiplexer);
		multiplexer.addProcessor(ui);

		batch = new SpriteBatch();
	}

	@Override
	public void render() {
		float delta = Gdx.graphics.getDeltaTime();

		if (fb == null || fb.getWidth() != Gdx.graphics.getWidth() || fb.getHeight() != Gdx.graphics.getHeight()) {
			if (fb != null) fb.dispose();
			fb = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
			fbRegion = new TextureRegion(fb.getColorBufferTexture());
			fbRegion.flip(false, true);
		}

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		fb.begin();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		workspace.update(delta);
		ui.render(delta);

		fb.end();

		fb.getColorBufferTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
		setShader(batch);
		batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.begin();
		batch.draw(fbRegion, 0, 0);
		batch.end();

		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void setShader(SpriteBatch batch) {
		if (shader != null) shader.dispose();

		try {
			ShaderProgram s = new ShaderProgram(Gdx.files.internal("scanline.vert"), Gdx.files.internal("scanline.frag"));
			if (!s.isCompiled()) {
				System.out.println(s.getLog());
			} else {
				shader = s;
				shader.pedantic = true;
				shader.setUniformf("u_resolutionY", 1f);
				shader.setUniformf("u_scale", 1);
				batch.setShader(shader);
			}
		} catch (Exception e) {
		}

	}

	public static void main(String[] args) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(800, 600);
		config.setTitle("CAIDE");
		config.setResizable(true);
		new Lwjgl3Application(new Caide(), config);
	}
}
