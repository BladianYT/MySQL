package me.treva.sql.tasks;

import me.treva.sql.Main;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.PreparedStatement;
import java.util.UUID;

/**
 * Project: MySQL
 * Created by Treva on 02/08/2016.
 * GitHub: www.github.com/Trevaa
 */
public class PushData extends BukkitRunnable {

    private UUID uuid;
    private String name;

    public PushData(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;

    }

    public void run() {
        try {
            PreparedStatement statement = Main.getInstnace().getConnection().prepareStatement(
                    "UPDATE 'player_data' SET 'name' = ?,  WHERE 'uuid' = ?");
            statement.setString(1, name);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
