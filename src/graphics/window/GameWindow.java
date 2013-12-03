package graphics.window;

import static constants.Constants.HOST;
import static constants.Constants.PORT;
import static constants.Constants.TILESIZE;
import game_objects.GameObject;
import game_objects.Map;
import game_objects.Player;
import graphics.core.GameGraphics;
import graphics.input.GameKeyListener;
import graphics.objects.Drawable;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;

import networking.client.PlayerClient;

import constants.Constants;

;

public class GameWindow extends JFrame implements Drawable {

	private int width = TILESIZE * Constants.WIDTH;
	private int height = TILESIZE * Constants.HEIGHT;

	/**
   * 
   */
	private static final long serialVersionUID = 5593300460625998050L;

	private BufferStrategy bStrategy;
	private GameGraphics game;

	private boolean gameRunning = true;

	private long lastFpsTime;

	private int fps;

	private GameKeyListener keyListener;

	public GameWindow(final GameGraphics game) {
		super("Bomber Java");
		setUndecorated(true);

		this.game = game;

		setPreferredSize(new Dimension(width, height));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);

		keyListener = new GameKeyListener();

		addKeyListener(keyListener);
		game.setKeyInput(keyListener);

		createBufferStrategy(2);
		bStrategy = this.getBufferStrategy();
	}

	public void gameLoop() {
		long lastLoopTime = System.nanoTime();
		final int TARGET_FPS = 28;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

		// keep looping round til the game ends
		while (gameRunning) {
			// work out how long its been since the last update, this
			// will be used to calculate how far the entities should
			// move this loop
			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double) OPTIMAL_TIME);

			// update the frame counter
			lastFpsTime += updateLength;
			fps++;

			// update our FPS counter if a second has passed since
			// we last recorded
			if (lastFpsTime >= 1000000000) {
				// System.out.println("(FPS: " + fps + ")");
				lastFpsTime = 0;
				fps = 0;
			}

			// update the game logic
			doGameUpdates(delta);

			// draw on memory
			draw(bStrategy.getDrawGraphics());

			// draw everyting
			render();

			// we want each frame to take 10 milliseconds, to do this
			// we've recorded when we started the frame. We add 10 milliseconds
			// to this and then factor in the current time to give
			// us our final value to wait for
			// remember this is in ms, whereas our lastLoopTime etc. vars are in
			// ns.
			long sleep = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
			try {
				if (sleep > 0)
					Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void render() {
		bStrategy.show();
	}

	private void doGameUpdates(double delta) {
		game.update(delta);
	}

	@Override
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.green);
		g2d.fillRect(0, 0, width, height);
		g2d.setColor(Color.black);
		game.draw(g2d);
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		GameGraphics game = null;

		try {
			game = new GameGraphics();
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(1);
		}
		
		PlayerClient client;
		
		client = new PlayerClient(new Socket(HOST, PORT), game);
		client.listen();
		game.setMessageListener(client);
		
		Map map = new Map(game);
		game.setMap(map);
		
		Player player = new Player(game, 10, 10, 1);
		game.setPlayer(player);
		game.addObject(player);

		GameWindow window = new GameWindow(game);
		window.gameLoop();
	}
}
