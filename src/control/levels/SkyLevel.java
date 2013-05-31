package control.levels;

import java.awt.Image;
import java.awt.Toolkit;

import model.Game;
import model.entities.Entity;
import model.entities.HostileEntity;
import model.entities.sky.Bird;
import model.entities.sky.Cloud;
import view.ground.GroundRenderer;
import view.ground.SkyGround;
import control.Climate;
import control.Hardware;

public class SkyLevel extends PunchLevel
{
	private GroundRenderer groundRenderer = new SkyGround();
	
	public SkyLevel(Game game)
	{
		super(game);
		Hardware.getInstance().setClimate(Climate.COLD);
	}

	public void update(double time)
	{
		super.update(time);
	}
	
	@Override
	public Entity getRandomEntity()
	{
		return new Cloud();
	}

	@Override
	public HostileEntity getRandomHostileEntity()
	{
		return new Bird(getGame().getCamera().getUsers());
	}

	@Override
	public int getEntitySpawnRate()
	{
		return 100;
	}

	@Override
	public int getHostileEntitySpawnRate()
	{
		return 300;
	}

	@Override
	public Image getBackground()
	{
		return Toolkit.getDefaultToolkit().getImage("./images/sky/background.jpg");
	}

	@Override
	public GroundRenderer getGroundRenderer()
	{
		return groundRenderer;
	}
}
