package com.thebatz.PlayLimit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	private static Main instance;
	
	@Override
	public void onEnable() {
		Main.instance = this;
		new Manager();
		getCommand("limit").setExecutor(new LimitCommand());
		Bukkit.getPluginManager().registerEvents(new LimitListener(), this);
	}
	
	public static Main getInstance() { return instance; }
	

}
