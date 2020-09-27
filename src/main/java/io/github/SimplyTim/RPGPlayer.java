package io.github.SimplyTim;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RPGPlayer {
	
	private Player p;	// Holds the actual Player information (from spigot)
	private int[] swords = new int[3];	// An array representing sword stats. swords[0] is the current level of the player. swords[1] is the current experience the player has in swords. swords[2] is the experience the player needs to level up swords.
	
	// Constructor, gets and sets player information.
	public RPGPlayer(Player p) {
		this.p = p;
		this.swords[0] = 0;
		this.swords[1] = 0;
		this.swords[2] = 15;

	}
	
	// Returns the extra damage a player will deal based on their sword level.
	public double getExtraSwordDamageToMobs() {
		return ((double)this.swords[0]);
		
	}
	
	public int getSwordsLevel() {
		return this.swords[0];
	}
	
	public int getSwordsExp() {
		return this.swords[1];
	}
	
	public int getRemainingExp() {
		return this.swords[2] - this.swords[1];
	}
	
	public Player getPlayer() {
		return this.p;
	}
	
	public void mobKill(int tier) {
		this.swords[1] = this.swords[1] + tier;
		while (this.swords[1] >= this.swords[2]) {
			this.swords[0]++;
			p.sendMessage(ChatColor.GOLD + "You feel more comfortable with a sword in hand.");
			this.swords[1] = this.swords[1] - this.swords[2];
			this.swords[2] *= 1.2;
		}
	}
}

