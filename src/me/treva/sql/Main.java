package me.treva.sql;

import me.treva.sql.tasks.LoadData;
import me.treva.sql.tasks.PushData;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Project: MySQL
 * Created by Treva on 02/08/2016.
 * GitHub: www.github.com/Trevaa
 */
public class Main extends JavaPlugin implements Listener {

    private static Main instnace;
    private Connection connection;
    private String connectionString = "jdbc:mysql://127.0.0.1:3306/?user=root&password=Applepie117&autoReconnect=true";

    public static Main getInstnace() {
        return instnace;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void onEnable() {
        instnace = this;
        getLogger().info("Plugin has loaded in. Connecting to database..");
        openConnection();
    }
    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent e){
        LoadData loadData = new LoadData(e.getUniqueId(), e.getName());
        loadData.runTaskAsynchronously(this);
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        PushData pushData = new PushData(e.getPlayer().getUniqueId(), e.getPlayer().getName());
        pushData.runTaskAsynchronously(this);
    }

    private void openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(connectionString);
            PreparedStatement statement = connection.prepareStatement("USE test;");
            statement.executeUpdate();
            PreparedStatement statement1 = connection.prepareStatement("CREATE TABLE IF NOT EXISTS player_data(uuid VARCHAR(36), name VARCHAR(17)");
            statement1.executeUpdate();
            Bukkit.getLogger().info("[MySQL] The connection to MySQL has been made!");


        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
