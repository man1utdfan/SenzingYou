package model.levels.sky;

import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.util.List;

import model.Camera;
import model.User;
import model.entities.HostileEntity;

public class Bird extends HostileEntity
{
	private double baseY; 

	public Bird(List<User> users)
	{
		super(users);

		baseY = Math.random()
				* (Camera.VIEW_HEIGHT - getDimensions().getHeight() * 4)
				+ getDimensions().getHeight();
		position.setLocation(0, baseY);
		velocity = new Point2D.Double(Math.random() * 0.2 + 0.01, 0.0);
	}

	@Override
	public int getReward()
	{
		return 100;
	}

	@Override
	public Point2D getRotationPoint()
	{
		return new Point2D.Double(0, 0);
	}

	@Override
	public Dimension2D getDimensions()
	{
		return new Dimension(40, 60);
	}

	@Override
	public String[] getImageNames()
	{
		return new String[] { "sky/bird.png" };
	}

	@Override
	public void update(double time)
	{
		super.update(time);
		position.setLocation(position.getX() + velocity.getX() * time, baseY + Math.sin(position.getX() / Camera.VIEW_WIDTH * 2 * Math.PI) * getDimensions().getHeight());

	}

	@Override
	public String getHitSoundName()
	{
		return null;
	}
	
	@Override
	public boolean isMirrored()
	{
		return false;
	}
}
