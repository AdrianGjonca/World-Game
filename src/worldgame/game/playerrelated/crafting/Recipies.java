package worldgame.game.playerrelated.crafting;

public class Recipies {
	static String[][][] recipies = {
			{{"Wood", "Coal", "__"}, {"Torch"}},
			{{"Leaves", "__", "__"}, {"Wood"}},
			{{"Wood", "Wood", "Wood"}, {"Stairs"}}
			};
	public static String getCraftables(String a, String b, String c) {
		for(String [][] r : recipies) {
			if(can(a,b,c,r[0])) return r[1][0];
		}
		
		
		return "__";
	}
	
	static boolean can(String a, String b, String c, String[] r) {
		if(a == r[0] && b == r[1] && c == r[2]) return true;
		if(a == r[2] && b == r[0] && c == r[1]) return true;
		if(a == r[1] && b == r[2] && c == r[0]) return true;
		if(a == r[1] && b == r[0] && c == r[2]) return true;
		if(a == r[2] && b == r[1] && c == r[0]) return true;
		if(a == r[0] && b == r[2] && c == r[1]) return true;
		return false;
	}
	
}
