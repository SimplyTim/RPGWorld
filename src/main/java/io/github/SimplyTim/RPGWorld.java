package io.github.SimplyTim;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;


public class RPGWorld extends JavaPlugin{
	
	// This resets every time plugin is loaded, so I need some persistance for this.
	public static HashMap<UUID, RPGPlayer>RPGs = new HashMap<UUID, RPGPlayer>();	// This would map Player to RPGPlayer.
	
    // Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	getServer().getPluginManager().registerEvents(new Listeners(), this);
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    }
}
