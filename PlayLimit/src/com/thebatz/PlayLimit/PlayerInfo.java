package com.thebatz.PlayLimit;

import org.bukkit.entity.Player;

public class PlayerInfo {
	
	private Player player; // ID
	private boolean strict, active;
	private int minutes, banHours;
	private pTimer timer;
	
	public PlayerInfo(Player player, boolean strict, int min) {
		this.player = player;
		this.strict = strict;
		this.active = false;
		this.minutes = min;
		this.banHours = 4;
		this.timer = new pTimer(this);
	}

	public Player getPlayer() { return player; }
	public boolean isStrict() { return strict; }
	public boolean isActive() { return active; }
	public int getMinutes() { return minutes; }
	public pTimer getTimer() { return timer; }
	public int getBanHours() { return banHours; }
	
	public void setMinutes(int min) { minutes = min; }
	public void setStrict(boolean flag) { strict = flag; }
	public void startTimer() { timer.begin(); }
	public void stopTimer() { timer.cancel(); }
	public void setActive(boolean flag) { active = flag; } 
	public void setBanHours(int hour) { banHours = hour; }

}
