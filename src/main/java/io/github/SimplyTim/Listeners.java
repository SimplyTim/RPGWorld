package io.github.SimplyTim;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
public class Listeners implements Listener {
	
	Material[] swordTypes = new Material[] {
	Material.DIAMOND_SWORD, 
	Material.IRON_SWORD, 
	Material.GOLDEN_SWORD, 
	Material.STONE_SWORD, 
	Material.WOODEN_SWORD};
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent evt) {
		Player p = evt.getPlayer();
		
		// If a new player joins, they are added to the RPGs HashMap.
		//if (!p.hasPlayedBefore()) {
			RPGPlayer newPlayer = new RPGPlayer(p);
			RPGWorld.RPGs.put(p.getUniqueId(), newPlayer);
		//}
		int currLvl = RPGWorld.RPGs.get(p.getUniqueId()).getSwordsLevel();
		p.sendMessage("Your current sword level is " + currLvl + ".");
	}
	
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent evt) {
		if (evt.getDamager() instanceof Player && !evt.getDamager().equals(null)) {
			Player p = (Player) evt.getDamager();
			Material inHand = p.getInventory().getItemInMainHand().getType();

			for (Material m: swordTypes) {
				if (inHand != null && inHand == m) {
					double addDmg = RPGWorld.RPGs.get(p.getUniqueId()).getExtraSwordDamageToMobs();
					evt.setDamage(evt.getDamage() + addDmg);
					p.sendMessage(ChatColor.AQUA + "You did " + evt.getDamage() +  " damage with the sword.");
					break;
				}
			}
		}
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent evt) {
		LivingEntity dead = evt.getEntity();
		Player killer = dead.getKiller();
		if(killer != null) {
			RPGPlayer rpgKiller = RPGWorld.RPGs.get(killer.getUniqueId());
			rpgKiller.mobKill(5);
	        killer.sendMessage(ChatColor.AQUA + "You gained some experience with the sword.");
	    }
	}
}
