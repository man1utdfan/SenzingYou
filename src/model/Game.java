package model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.entities.Entity;
import control.Song;
import control.levels.Level;

public class Game
{
	private List<Entity> entities;
	private Camera camera;
	private Image background;
	private Song song;
	private Image ground;
	private boolean levelMenu;
	private Level level;
	private List<Drive> drives;

	public Game()
	{
		this.entities = Collections.synchronizedList(new ArrayList<Entity>());
		this.drives = new ArrayList<Drive>();
		
		for (char a = 'A'; a <= 'I'; a++)
		{
			drives.add(new Drive(a));
		}
		
		background = null;
		camera = new Camera();
	}

	public void clearRoom()
	{
		entities.clear();
	}

	public List<Entity> getEntities()
	{
		return entities;
	}

	public void addEntity(Entity entity)
	{
		entities.add(entity);
	}

	public void removeEntity(Entity entity)
	{
		entities.remove(entity);
	}

	public Camera getCamera()
	{
		return camera;
	}

	public Image getBackground()
	{
		return background;
	}

	public void setBackground(Image background)
	{
		this.background = background;
	}

	public Image getGround()
	{
		return ground;
	}

	public void setGround(Image ground)
	{
		this.ground = ground;
	}

	public Song getSong()
	{
		return song;
	}

	public void setSong(Song song)
	{
		this.song = song;
	}

	public boolean isLevelMenu()
	{
		return levelMenu;
	}

	public void setLevelMenu(boolean levelMenu)
	{
		this.levelMenu = levelMenu;
	}

	public Level getLevel()
	{
		return level;
	}

	public void setLevel(Level level)
	{
		this.level = level;
	}
	
	public List<Drive> getJustConnectedDrives()
	{
		List<Drive> justConnected = new ArrayList<Drive>();
		
		for (Drive drive : drives)
		{
			if (drive.wasJustConnected())
			{
				justConnected.add(drive);
			}
		}
		
		return justConnected;
	}
}
