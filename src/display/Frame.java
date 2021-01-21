package display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import Main.Vector;

public class Frame extends JFrame
{
	private static final long serialVersionUID = 1L;
	public static BufferedImage screen;
	public static BufferedImage snowflake;
	public static int frameCount = 0;
	public static Vector particle;
	public static float spread = 2;
	public static boolean done = false;

	public Frame(int width, int height)
	{
		particle = new Vector(width / 2, 0);
		this.setSize(width, height);
		screen = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		snowflake = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = screen.getGraphics();
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, screen.getWidth(), screen.getHeight());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void moveParticle()
	{
		particle.d -= 1f;
		particle.a += (Math.random() - 0.5) * spread;
		if (particle.a < 0)
			particle.a = 0;
		particle.calcCartesian();
		if (checkCollision())
		{
			for (int i = 0; i < 6; i++)
			{
				snowflake.setRGB(500 + (int) particle.x, 500 + (int) particle.y, Color.WHITE.getRGB());
				particle.a *= -1;
				particle.calcCartesian();
				snowflake.setRGB(500 + (int) particle.x, 500 + (int) particle.y, Color.WHITE.getRGB());
				particle.a *= -1;
				particle.a += 60;
				particle.calcCartesian();
			}

			particle = new Vector(screen.getWidth() / 2, 0);
			done = checkCollision();
		}
	}

	public boolean checkCollision()
	{
		if (particle.d <= 1)
			return true;
		int x = (int) particle.x;
		int y = (int) particle.y;
		return (used(x - 1, y - 1) || used(x - 1, y) || used(x - 1, y + 1) || used(x, y - 1) || used(x, y + 1)
				|| used(x + 1, y - 1) || used(x + 1, y) || used(x + 1, y + 1));
	}

	public boolean used(int x, int y)
	{
		x += 500;
		y += 500;
		if (!(x >= 0 && y >= 0 && x < screen.getWidth() && y < screen.getHeight()))
		{
			return false;
		}

		Color c = new Color(snowflake.getRGB(x, y));
		return c.getRed() > 50;
	}

	@Override
	public void paint(Graphics fin)
	{
		fin.drawImage(screen, 0, 0, null);
	}
}
