package me.treva.sql;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Project: MySQL
 * Created by Treva on 02/08/2016.
 * GitHub: www.github.com/Trevaa
 */
public class DeathEvent implements Listener {
    public void onDeath(PlayerDeathEvent e) {

        Player p = e.getEntity();

    }
}
