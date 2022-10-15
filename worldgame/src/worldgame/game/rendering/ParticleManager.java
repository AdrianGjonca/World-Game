package worldgame.game.rendering;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import worldgame.game.World;

public class ParticleManager {
	
	public List<Particle> particles = new LinkedList<Particle>();
	public List<Integer> toremove = new LinkedList<Integer>();
	
	World world;
	WorldSpace world_space;
	public ParticleManager(World world, WorldSpace world_space) {
		this.world = world;
		this.world_space = world_space; 
	}
	
	public void draw(Graphics g) {
		if(particles.size() > 0) {
			int i = 0;
			for(Particle particle : particles) {
				particle.draw(g, world_space);
				if(particle.retired) {
					toremove.add(i);
				}
				i++;
			}
			
			int offS = 0;
			for(int a : toremove) {
				particles.remove(a - offS);
				offS++;
			}
			toremove.clear();
		}
	}
	
	
}