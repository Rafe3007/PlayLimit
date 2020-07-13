package com.thebatz.PlayLimit;

import java.util.Calendar;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LimitListener implements Listener{
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		if(Manager.hasTimer(player)) {
			if(Manager.getInfo(player).isActive()) {
				if(Manager.getInfo(player).isStrict()) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.HOUR, Manager.getInfo(player).getBanHours());
					Bukkit.getBanList(Type.NAME).addBan(player.getName(), ChatColor.RED + "PlayLimit has been reached!", cal.getTime(), null);
				}
			}
			Manager.removePlayer(player);
		}
	}

}
