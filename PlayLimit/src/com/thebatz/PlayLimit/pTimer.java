package com.thebatz.PlayLimit;

import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.BanList.Type;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class pTimer extends BukkitRunnable{
	
	private Player player;
	private int minutes;
	
	public pTimer(PlayerInfo info) {
		this.player = info.getPlayer();
		this.minutes = info.getMinutes();
	}
	
	public void begin() {
		this.runTaskTimer(Main.getInstance(), 0L, 1200);
	}
	
	public void cancel() {
		minutes = 0;
	}
	
	@Override
	public void run() {
		if(minutes == 0) {
			this.cancel();
			if(Manager.hasTimer(player)) {
				if(Manager.getInfo(player).isStrict()) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.HOUR, Manager.getInfo(player).getBanHours()); 
					Bukkit.getBanList(Type.NAME).addBan(player.getName(), ChatColor.RED + "PlayLimit has been reached!", cal.getTime(), null);
				}
			}
			player.kickPlayer(ChatColor.RED + "You have reached your limit!!");
			Manager.removePlayer(player);
			return;
		}
		
		if(minutes == 15 || minutes == 5 || minutes == 1) {
			player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "PL" + ChatColor.DARK_AQUA + "] " +
					ChatColor.AQUA + "You have " + ChatColor.RED + minutes + ChatColor.AQUA + " remaining");
		}
		
		minutes--;
	}
	
	public int getMinutes() { return minutes; }

}
