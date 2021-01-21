package Main;

public class Vector
{
	public float x, y, a, d;

	public Vector(float x, float y)
	{
		this.x = x;
		this.y = y;
		calcPolar();
	}

	public Vector(float a, float d, boolean b)
	{
		this.a = a;
		this.d = d;
		calcCartesian();
	}

	public void calcPolar()
	{
		d = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		a = (float) Math.toDegrees(Math.atan2(x, y)) - 90;
	}

	public void calcCartesian()
	{
		x = (float) Math.cos(Math.toRadians(a)) * d;
		y = (float) Math.sin(Math.toRadians(a)) * d;
	}
}
