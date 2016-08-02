package me.treva.sql.tasks;

import me.treva.sql.Main;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

/**
 * Project: MySQL
 * Created by Treva on 02/08/2016.
 * GitHub: www.github.com/Trevaa
 */
public class LoadData extends BukkitRunnable {

    private UUID uuid;
    private String name;

    public LoadData(UUID uuid, String name){
        this.uuid = uuid;
        this.name = name;

    }

    public void run() {
        try {
            PreparedStatement statement = Main.getInstnace().getConnection().prepareStatement("SELECT * FROM 'player_data' WHERE 'uuid' =?");
            //noinspection JpaQueryApiInspection
            statement.setString(1, uuid.toString());
            ResultSet resultset = statement.executeQuery();
            boolean doesPlayerHaveData = resultset.next();
            if (doesPlayerHaveData) {
                return;
            }else {
                PreparedStatement preparedstatement = Main.getInstnace().getConnection().prepareStatement(
                        "INSERT INTO 'player_data' ('uuid', 'name') VALUES (?, ?) ");
                preparedstatement.executeUpdate();
                resultset.close();
                statement.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
