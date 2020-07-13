package com.thebatz.PlayLimit;

import java.util.ArrayList;
import org.bukkit.entity.Player;

public class Manager {
	
	private static ArrayList<PlayerInfo> pInfo;
	
	public Manager() {
		pInfo = new ArrayList<>();
	}
	
	public static boolean hasTimer(Player player) {
		for(PlayerInfo pi: pInfo) {
			if(pi.getPlayer() == player)
				return true;
		}
		return false;
	}
	
	public static void addPlayer(Player player, int minutes) {
		PlayerInfo p = new PlayerInfo(player, false, minutes);
		pInfo.add(p);
	}
	
	public static void removePlayer(Player player) {
		for(int i = 0; i < pInfo.size(); i++) {
			if(pInfo.get(i).getPlayer() == player) {
				pInfo.remove(i);
			}
		}
	}
	
	public static PlayerInfo getInfo(Player player) { 
		for(PlayerInfo pi: pInfo) {
			if(pi.getPlayer() == player)
				return pi;
		}
		
		return null;
	}
	
	public static int getMinutes(Player player) {
		for(PlayerInfo pi : pInfo) {
			if(pi.getPlayer() == player)
				return pi.getTimer().getMinutes();
		}
		return -1;
	}

}
