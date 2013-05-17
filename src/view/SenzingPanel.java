package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.CameraData;
import model.Game;
import model.entities.Entity;

public class SenzingPanel extends JPanel implements ActionListener
{
	private static final  long serialVersionUID = 1L;
	private Game game;

	public SenzingPanel(Game game)
	{
		super();
		this.game = game;
		new Timer(1000/30, this).start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;


		CameraData cameraData = game.getCameraData();
		
		if (cameraData != null)
		{
			g2.drawImage(cameraData.getImage(), null, 0, 0);
//			for(Hand hand: game.getCameraData().getHands()){
//				try
//				{
//					if(hand.getPosition() != null){
//						if(hand.getPosition().getZ() != 0){
//							hand.setPosition(game.getCameraData().getDepthGenerator().convertRealWorldToProjective(hand.getPosition()));
//						}
//						
//						Point2D p2 = new Point2D.Double(hand.getPosition().getX(), hand.getPosition().getY());
//						g2.setColor(Color.RED);
//						g2.drawArc((int)p2.getX(), (int)p2.getY(), 20, 20, 0, 360);
//					}
//				} catch (StatusException e)
//				{
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
		}
		
		for (Entity entity : game.getEntities())
		{
			g2.fill(AffineTransform.getRotateInstance(entity.getRotation(), entity.getBounds().getWidth() / 2, entity.getBounds().getHeight() / 2).createTransformedShape(entity.getBounds()));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}
}
