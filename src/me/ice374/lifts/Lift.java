package me.ice374.lifts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Lift extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
    
    @EventHandler
    public void onUseElevator(PlayerInteractEvent event) 
    {
        Player player = event.getPlayer();
        if(event.getClickedBlock().getType().equals(Material.STONE_BUTTON)) 
        {
            Location loc = event.getPlayer().getLocation();
            Block standOn = event.getPlayer().getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY()-1, loc.getBlockZ());

            if(Material.SPONGE.equals(standOn.getType())) 
            {
                Block signBlock = event.getPlayer().getWorld().getBlockAt(event.getClickedBlock().getLocation().getBlockX(), event.getClickedBlock().getLocation().getBlockY()+1, event.getClickedBlock().getLocation().getBlockZ());

                if(signBlock.getState() instanceof Sign) 
                {
                    Sign sign = (Sign) signBlock.getState();

                    if (sign.getLine(0).contains("up")) {

                        sign.setLine(0, ChatColor.DARK_RED + "Elevator");
                        sign.setLine(2, ChatColor.GOLD + "[" + ChatColor.DARK_GRAY + "UP" + ChatColor.GOLD + "]");
                        sign.update(true);
                        
                        player.sendMessage(ChatColor.GREEN + "Elevator Setup!");

                    } 

                    else if (sign.getLine(0).equals(ChatColor.DARK_RED + "Elevator") 
                            && sign.getLine(2).equals(ChatColor.GOLD + "[" + ChatColor.DARK_GRAY + "UP" + ChatColor.GOLD + "]")) {

                        int i = standOn.getLocation().getBlockY();

                        while (i < 255) 
                        {
                            i++;
                            Block sponge = event.getPlayer().getWorld().getBlockAt(standOn.getLocation().getBlockX(),  i, standOn.getLocation().getBlockZ());

                            if (sponge.getType().equals(Material.SPONGE)) 
                            {
                                i=256;
                                Location tp = sponge.getLocation();
                                tp.setY(tp.getBlockY() + 1.5);
                                tp.setZ(tp.getBlockZ() + 0.5);
                                tp.setX(tp.getBlockX() + 0.5);
                                tp.setPitch(player.getLocation().getPitch());
                                tp.setYaw(player.getLocation().getYaw());

                                player.teleport(tp);

                            }
                        };
                        player.sendMessage(ChatColor.AQUA + "Going up");
                    }

                    // sign.setLine(0, ChatColor.DARK_PURPLE + "Elevator");
                    // sign.setLine(2, ChatColor.GOLD + "[ " + ChatColor.DARK_GRAY + "UP" + ChatColor.GOLD + " ]");

                    if (sign.getLine(0).contains("down")) {

                        sign.setLine(0, ChatColor.DARK_RED + "Elevator");
                        sign.setLine(2, ChatColor.GOLD + "[" + ChatColor.DARK_GRAY + "DOWN" + ChatColor.GOLD + "]");
                        sign.update(true);
                        
                        player.sendMessage(ChatColor.GREEN + "Elevator Setup!");

                    } 

                    else if (sign.getLine(0).equals(ChatColor.DARK_RED + "Elevator") 
                            && sign.getLine(2).equals(ChatColor.GOLD + "[" + ChatColor.DARK_GRAY + "DOWN" + ChatColor.GOLD + "]")) {


                        int i = standOn.getLocation().getBlockY();

                        while (i > 0) {
                            i--;
                            Block sponge = event.getPlayer().getWorld().getBlockAt(standOn.getLocation().getBlockX(),  i, standOn.getLocation().getBlockZ());

                            if (sponge.getType().equals(Material.SPONGE)) {
                                i=0;
                                Location tp = sponge.getLocation();
                                tp.setY(tp.getBlockY() + 1.5);
                                tp.setZ(tp.getBlockZ() + 0.5);
                                tp.setX(tp.getBlockX() + 0.5);
                                tp.setPitch(player.getLocation().getPitch());
                                tp.setYaw(player.getLocation().getYaw());

                                player.teleport(tp);

                            }
                        };
                        player.sendMessage(ChatColor.AQUA +"Going down");
                    }
                }
            }
        }
    }
}