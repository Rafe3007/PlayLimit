package com.thebatz.PlayLimit;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LimitCommand implements CommandExecutor{
	
	private String p = "playlimit.";
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			
			if(args.length == 0) {
				if(player.hasPermission(p + "limit") || player.hasPermission(p + "player")) {
					player.sendMessage(ChatColor.GRAY + "Usage: " + ChatColor.AQUA + "/limit [minutes]");
				} else {
					player.sendMessage(ChatColor.RED + "Sorry, you don't have permission...");
				}
			}
			
			// TODO
			// Modify so that it's just the commands they have: for expierence not nesesity
			else if(args.length == 1 && args[0].equalsIgnoreCase("help")) {
				player.sendMessage(ChatColor.AQUA + "----" + ChatColor.DARK_AQUA + "[" + ChatColor.WHITE + "PlayLimit" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + "----");
				if(player.hasPermission("PlayLimit.limit") || player.hasPermission("PlayLimit.player")) {
					player.sendMessage(ChatColor.AQUA + "/limit [minutes]" + ChatColor.WHITE + " Sets up the limit");
					player.sendMessage(ChatColor.AQUA + "/limit strict" + ChatColor.WHITE + " Toggles the mode of the timer.");
					player.sendMessage(ChatColor.AQUA + "/limit ban [hours]" + ChatColor.WHITE + " Sets up how many hours you will be temp banned");
					player.sendMessage(ChatColor.AQUA + "/limit 'start | begin'" + ChatColor.WHITE + " Begins the timer");
					player.sendMessage(ChatColor.AQUA + "/limit 'end | cancel'" + ChatColor.WHITE + " Ends the timer (not in strict mode)");
					player.sendMessage(ChatColor.AQUA + "/limit info" + ChatColor.WHITE + " Info about your timer");
					player.sendMessage(" ");
					player.sendMessage(ChatColor.RED + "Strict mode means you will be temporarily banned once the timer runs out.(defaulted to 4 hours) If you log out when the timer is on, you will be temp-banned.");
				} else {
					player.sendMessage(ChatColor.RED + "Sorry, you don't have permission...");
				}
			}
			
			
			else if(args.length == 1 && args[0].equalsIgnoreCase("strict")) {
				if(player.hasPermission(p + "strict") || player.hasPermission(p + "player")) {
					if(Manager.hasTimer(player)) {
						if(Manager.getInfo(player).isStrict())
							Manager.getInfo(player).setStrict(false);
						else 
							Manager.getInfo(player).setStrict(true);
						player.sendMessage(ChatColor.DARK_AQUA + "[" + ChatColor.AQUA + "PL" + ChatColor.DARK_AQUA + "] " +
								ChatColor.RED + "Strict mode set to: " + (Manager.getInfo(player).isStrict() ? ChatColor.GREEN + "true" : ChatColor.RED + "false"));
					} else {
						player.sendMessage(ChatColor.RED + "You don't have a limit!");
					}
					
				} else {
					player.sendMessage(ChatColor.RED + "Sorry, you don't have permission...");
				}
			}
			
			else if(args.length == 2 && args[0].equalsIgnoreCase("ban")) {
				if(player.hasPermission(p + "banLimit") || player.hasPermission(p + "player")) {
					if(Manager.hasTimer(player)) {
						try {
							int hour = Integer.parseInt(args[1]);
							Manager.getInfo(player).setBanHours(hour);
							player.sendMessage(ChatColor.AQUA + "Temp-ban time limit set to: " + ChatColor.WHITE + hour + ChatColor.AQUA + " hour(s)");
						} catch(NumberFormatException x) {
							player.sendMessage(ChatColor.RED + "Invalid format: /limit ban [number in hours]");
						}
					} else {
						player.sendMessage(ChatColor.RED + "You don't have a limit!");
					}
				} else {
					player.sendMessage(ChatColor.RED + "Sorry, you don't have permission...");
				}
			}
			
			else if(args.length == 1 && args[0].equalsIgnoreCase("start")) {
				if(player.hasPermission(p + "start") || player.hasPermission(p + "player")) {
					if(Manager.hasTimer(player)) {
						player.sendMessage(ChatColor.AQUA + "----" + ChatColor.DARK_AQUA + "[" + ChatColor.WHITE + "PlayLimit" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + "----");
						player.sendMessage(ChatColor.DARK_AQUA + " Limit: " + ChatColor.AQUA + Manager.getInfo(player).getMinutes());
						player.sendMessage(ChatColor.DARK_AQUA + " Strict: " + (Manager.getInfo(player).isStrict() ? ChatColor.GREEN + "true" : ChatColor.RED + "false"));
						player.sendMessage((Manager.getInfo(player).isStrict() ? ChatColor.DARK_AQUA + "    - Ban Time: " : ChatColor.GRAY + "    - Ban Time: ") + ChatColor.AQUA + Manager.getInfo(player).getBanHours() + " hour(s)");
						Manager.getInfo(player).startTimer();
						Manager.getInfo(player).setActive(true);
						player.sendMessage(" ");
						player.sendMessage(ChatColor.GREEN + "*Timer activated*");
					} else {
						player.sendMessage(ChatColor.RED + "You don't have a limit!");
					}
				} else {
					player.sendMessage(ChatColor.RED + "Sorry, you don't have permission...");
				}
				
			}
			
			else if(args.length == 1 && args[0].equalsIgnoreCase("begin")) {
				if(player.hasPermission(p + "start") || player.hasPermission(p + "player")) {
					if(Manager.hasTimer(player)) {
						player.sendMessage(ChatColor.AQUA + "----" + ChatColor.DARK_AQUA + "[" + ChatColor.WHITE + "PlayLimit" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + "----");
						player.sendMessage(ChatColor.DARK_AQUA + " Limit: " + ChatColor.AQUA + Manager.getInfo(player).getMinutes());
						player.sendMessage(ChatColor.DARK_AQUA + " Strict: " + (Manager.getInfo(player).isStrict() ? ChatColor.GREEN + "true" : ChatColor.RED + "false"));
						player.sendMessage((Manager.getInfo(player).isStrict() ? ChatColor.DARK_AQUA + "    - Ban Time: " : ChatColor.GRAY + "    - Ban Time: ") + ChatColor.AQUA + Manager.getInfo(player).getBanHours() + " hour(s)");
						Manager.getInfo(player).startTimer();
						Manager.getInfo(player).setActive(true);
						player.sendMessage(" ");
						player.sendMessage(ChatColor.GREEN + "*Timer activated*");
					} else {
						player.sendMessage(ChatColor.RED + "You don't have a limit!");
					}
				} else {
					player.sendMessage(ChatColor.RED + "Sorry, you don't have permission...");
				}
			}
			
			else if(args.length == 1 && args[0].equalsIgnoreCase("end")) {
				if(player.hasPermission(p + "cancel") || player.hasPermission(p + "player")) {
					if(Manager.hasTimer(player) && Manager.getInfo(player).isActive()) {
						if(!Manager.getInfo(player).isStrict()) {
							Manager.getInfo(player).stopTimer();
							Manager.removePlayer(player);
							player.sendMessage(ChatColor.RED + "*Timer deactivated*");
						} else {
							player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Strict Mode Active");
							player.sendMessage(ChatColor.RED + "Can't do this while in strict mode");
						}
					} else {
						player.sendMessage(ChatColor.RED + "You don't have an active limit!");
					}
				} else {
					player.sendMessage(ChatColor.RED + "Sorry, you don't have permission...");
				}
			}
			
			else if(args.length == 1 && args[0].equalsIgnoreCase("cancel")) {
				if(player.hasPermission(p + "cancel") || player.hasPermission(p + "player")) {
					if(Manager.hasTimer(player) && Manager.getInfo(player).isActive()) {
						if(!Manager.getInfo(player).isStrict()) {
							Manager.getInfo(player).stopTimer();
							Manager.removePlayer(player);
							player.sendMessage(ChatColor.RED + "*Timer deactivated*");
						} else {
							player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Strict Mode Active");
							player.sendMessage(ChatColor.RED + "Can't do this while in strict mode");
						}
					} else {
						player.sendMessage(ChatColor.RED + "You don't have an active limit!");
					}
				} else {
					player.sendMessage(ChatColor.RED + "Sorry, you don't have permission...");
				}
			}
			
			else if(args.length == 1 && args[0].equalsIgnoreCase("info")) {
				if(player.hasPermission(p + "info") || player.hasPermission(p + "player")) {
					if(Manager.hasTimer(player)) {
						player.sendMessage(ChatColor.AQUA + "----" + ChatColor.DARK_AQUA + "[" + ChatColor.WHITE + "PlayLimit" + ChatColor.DARK_AQUA + "]" + ChatColor.AQUA + "----");
						player.sendMessage(ChatColor.DARK_AQUA + " Limit: " + ChatColor.AQUA + Manager.getInfo(player).getMinutes() + " minute(s)");
						player.sendMessage(ChatColor.DARK_AQUA + " Strict: " + (Manager.getInfo(player).isStrict() ? ChatColor.GREEN + "true" : ChatColor.RED + "false"));
						player.sendMessage((Manager.getInfo(player).isStrict() ? ChatColor.DARK_AQUA + "    - Ban Time: " : ChatColor.GRAY + "    - Ban Time: ") + ChatColor.AQUA + Manager.getInfo(player).getBanHours() + " hour(s)");
						if(Manager.getInfo(player).isActive()) {
							player.sendMessage(ChatColor.DARK_AQUA + " Active: " + ChatColor.GREEN + "true");
							player.sendMessage(ChatColor.GRAY + "  " + Manager.getMinutes(player) + " minute(s) left");
						} else {
							player.sendMessage(ChatColor.DARK_AQUA + " Active: " + ChatColor.RED + "false");
						}
					} else {
						player.sendMessage(ChatColor.RED + "You don't have a limit!");
						player.sendMessage(ChatColor.GRAY + "/limit [number in minutes]" + ChatColor.AQUA + " to create limit");
					}
				} else {
					player.sendMessage(ChatColor.RED + "Sorry, you don't have permission...");
				}
			}
				
			else if(args.length == 1) {
				if(player.hasPermission(p + "limit") || player.hasPermission(p + "player")) {
					try {
						int min = Integer.parseInt(args[0]);
						if(Manager.hasTimer(player))
							Manager.removePlayer(player);
						Manager.addPlayer(player, min);
						player.sendMessage(ChatColor.AQUA + "Limit set to " + ChatColor.WHITE + min + ChatColor.AQUA + " minute(s)");
					} catch(NumberFormatException x) {
						player.sendMessage(ChatColor.RED + "Invalid format: /limit [number]");
					}
					
				} else {
					player.sendMessage(ChatColor.RED + "Sorry, you don't have permission...");
				}
			}
			
					
			else {
				if(player.hasPermission(p + "limit") || player.hasPermission(p + "player")) {
					player.sendMessage(ChatColor.GRAY + "Invalid Command:" + ChatColor.AQUA + " '/limit help'" + ChatColor.WHITE + " for more info");
				} else {
					player.sendMessage(ChatColor.RED + "Sorry, you don't have permission...");
				}
			}
			
		} else {
			System.out.println("Cannot use this command from console...");
		}
		
		return false;
	}

}
