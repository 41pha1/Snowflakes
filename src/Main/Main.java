package Main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import display.Frame;

public class Main
{
	public static Frame f;

	public static void main(String[] args)
	{

		f = new Frame(1000, 1000);

		while (true)
		{
			while (!Frame.done)
				f.moveParticle();
			Graphics2D g = Frame.screen.createGraphics();
			g.drawImage(Frame.snowflake, 0, 0, null);
			Frame.snowflake = new BufferedImage(Frame.screen.getWidth(), Frame.screen.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			Frame.done = false;
			f.repaint();
		}
	}
}
