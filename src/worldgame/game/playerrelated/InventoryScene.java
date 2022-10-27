package worldgame.game.playerrelated;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import worldgame.engine.core.IndividualInputObj;
import worldgame.engine.core.threading.GamePortal;
import worldgame.engine.core.threading.SceneThread;
import worldgame.engine.resourcecontroll.ResourceLoader;
import worldgame.game.block.BlockTypeManager;
import worldgame.game.playerrelated.crafting.Recipies;
import worldgame.game.rendering.TextGraphics;

public class InventoryScene extends SceneThread {

	Inventory inventory;
	static BufferedImage inventoryBackground = ResourceLoader.easyLoad("/Sprites/UI/InventoryBack.png");
	static BufferedImage inventoryCraftBackground = ResourceLoader.easyLoad("/Sprites/UI/InventoryCraftBack.png");
	static BufferedImage placement_cursor = ResourceLoader.easyLoad("/Sprites/PlaceCursor.png");
	static BufferedImage cross = ResourceLoader.retrieveTileset(ResourceLoader.easyLoad("/Sprites/ErrorTile.png"))[2];
	
	int state = 0;
	IndividualInputObj right;
	IndividualInputObj left;
	IndividualInputObj up;
	IndividualInputObj down;
	
	IndividualInputObj enter;
	public InventoryScene(Graphics image_graphics, GamePortal portal, Inventory inventory) {
		super(image_graphics, portal);
		this.inventory = inventory;
		
		IndividualInputObj right = new IndividualInputObj(KeyEvent.VK_RIGHT);
		portal.portal_window.listened_to_buttons.add(right);
		this.right = right;
		this.right.hasBeenReleased = true;
		
		IndividualInputObj left = new IndividualInputObj(KeyEvent.VK_LEFT);
		portal.portal_window.listened_to_buttons.add(left);
		this.left = left;
		this.left.hasBeenReleased = true;
		
		IndividualInputObj up = new IndividualInputObj(KeyEvent.VK_UP);
		portal.portal_window.listened_to_buttons.add(up);
		this.up = up;
		this.up.hasBeenReleased = true;
		
		IndividualInputObj down = new IndividualInputObj(KeyEvent.VK_DOWN);
		portal.portal_window.listened_to_buttons.add(down);
		this.down = down;
		this.down.hasBeenReleased = true;
		
		IndividualInputObj enter = new IndividualInputObj(KeyEvent.VK_ENTER);
		portal.portal_window.listened_to_buttons.add(enter);
		this.enter = enter;
		this.enter.hasBeenReleased = true;
	}

	int a = -1;
	int b = -1;
	int c = -1;
	@Override
	public void update(float deltaTime, Graphics g) {
		if(state == 0) {
			drawInventory(deltaTime, g);
		}
		if(state >= 1) {
			if(up.hasBeenReleased && up.hasBeenPressed && up.held) {
				switch(state) {
					case 1:
						a--;
						if(a < -1) {
							a = inventory.items.size() - 1;
						}
						break;
					case 2:
						b--;
						if(b < -1) {
							b = inventory.items.size() - 1;
						}
						break;
					case 3:
						c--;
						if(c < -1) {
							c = inventory.items.size() - 1;
						}
						break;
				}
				up.hasBeenReleased = false;
				up.hasBeenPressed = false;
			}
			
			if(down.hasBeenReleased && down.hasBeenPressed && down.held) {
				switch(state) {
					case 1:
						a++;
						if(a > inventory.items.size() - 1) {
							a = -1;
						}
						break;
					case 2:
						b++;
						if(b > inventory.items.size() - 1) {
							b = -1;
						}
						break;
					case 3:
						c++;
						if(c > inventory.items.size() - 1) {
							c = -1;
						}
						break;
				}
				down.hasBeenReleased = false;
				down.hasBeenPressed = false;
			}
			
			drawCrafting(deltaTime, g);
			if(inventory.items.size() > 0) {
				if(a != -1) {
					__CraftUIIndividualIcon(g, a, 0);
				}
				
				if(b != -1) {
					__CraftUIIndividualIcon(g, b, 22);
				}
				
				if(c != -1) {
					__CraftUIIndividualIcon(g, c, 44);
				}
			}
			
			if(inventory.items.size() > 0) {
				if(!canCraft()) g.drawImage(cross, 88, 64, null);
			}else {
				g.drawImage(cross, 88, 64, null);
			}
			g.drawImage(placement_cursor, 19 + ((state - 1) * 22),64, null);
			
			String aitem = "__";
			String bitem = "__";
			String citem = "__";
			
			if(inventory.items.size() > 0) {
				if(a != -1) aitem = inventory.items.get(a).type;
				if(b != -1) bitem = inventory.items.get(b).type;
				if(c != -1) citem = inventory.items.get(c).type;
				
				String out = Recipies.getCraftables(aitem, bitem, citem);
				if(!out.equals("__")) {
					BufferedImage oimg = BlockTypeManager.blocks[BlockTypeManager.getBlockChar(out)].getTexture(0, 0, (char)0, 1);
					if(inventory.contains(out)) {
						ItemStack item = null;
						for(ItemStack possible : inventory.items) {
							if(possible.type.equals(out)) {
								item = possible;
							}
						}
						g.drawImage(LinkItemNameToIcon.icon(item.type), 129, 68, null);
						String toWrite = item.type.toUpperCase() + " X " + item.numberOf;
						for(int x = 0; x < toWrite.length(); x++) {
							g.drawImage(TextGraphics.getLetter(toWrite.charAt(x)), 138 + (x * 8), 67, null);
						}
					}
					g.drawImage(oimg, 110,64, null);
					
					if(canCraft() && enter.hasBeenReleased && enter.hasBeenPressed && enter.held) {
						if(a != -1) {
							inventory.items.get(a).numberOf--;
							if(inventory.items.get(a).numberOf == 0) {
								inventory.items.remove(a);
								a--;
							}
						}
						if(b != -1) {
							inventory.items.get(b).numberOf--;
							if(inventory.items.get(b).numberOf == 0) {
								inventory.items.remove(b);
								b--;
							}
						}
						if(c != -1) {
							inventory.items.get(c).numberOf--;
							if(inventory.items.get(c).numberOf == 0) {
								inventory.items.remove(c);
								c--;
							}
						}
						inventory.addBlock((char)BlockTypeManager.getBlockChar(out));
						enter.hasBeenPressed = false;
						enter.hasBeenReleased = false;
					}
				}
			}
		}
		
		if(right.hasBeenReleased && right.hasBeenPressed && right.held) {
			state++;
			if(state > 3) state = 3;
			right.hasBeenReleased = false;
			right.hasBeenPressed = false;
		}
		if(left.hasBeenReleased && left.hasBeenPressed && left.held) {
			state--;
			if(state < 0) state = 0;
			left.hasBeenReleased = false;
			left.hasBeenPressed = false;
		}
	}
	
	boolean canCraft() {
		if(a != -1 && b != -1 && c != -1) {
			if(a == b && a == c) {
				if(inventory.items.get(a).numberOf < 3) return false;
			}else if(a == b) {
				if(inventory.items.get(a).numberOf < 2) return false;
			}else if(a == c) {
				if(inventory.items.get(a).numberOf < 2) return false;
			}else if(b == c) {
				if(inventory.items.get(c).numberOf < 2) return false;
			}
		}
		
		if(a != -1 && b != -1) {
			if(a == b && inventory.items.get(a).numberOf < 2) return false;
		}
		
		if(a != -1 && c != -1) {
			if(a == c && inventory.items.get(a).numberOf < 2) return false;
		}
		
		if(b != -1 && c != -1) {
			if(b == c && inventory.items.get(c).numberOf < 2) return false;
		}
		return true;
		
	}
	
	void __CraftUIIndividualIcon(Graphics g, int a, int off) {
		String atype = inventory.items.get(a).type;
		BufferedImage aimg = BlockTypeManager.blocks[BlockTypeManager.getBlockChar(atype)].getTexture(0, 0, (char)0, 1);
		g.drawImage(aimg, 19 + off,64, null);
		String aText = inventory.items.get(a).numberOf + "";
		for(int x = 0; x < aText.length(); x++) {
			g.drawImage(TextGraphics.getLetter(aText.charAt(x)), 19 + off + (7 * x), 55, null);
		}
	}
	void drawInventory(float deltaTime, Graphics g) {
		g.drawImage(inventoryBackground, 0, 0, null);
		int i = 0;
		for(ItemStack item : inventory.items) {
			g.drawImage(LinkItemNameToIcon.icon(item.type), 1, 8 * i + 1, null);
			String toWrite = item.type.toUpperCase() + " X " + item.numberOf;
			for(int x = 0; x < toWrite.length(); x++) {
				g.drawImage(TextGraphics.getLetter(toWrite.charAt(x)), 11 + (x * 8), 8 * i + 1, null);
			}
			i++;
		}
	}
	
	void drawCrafting(float deltaTime, Graphics g) {
		g.drawImage(inventoryCraftBackground, 0, 0, null);
	}

}
