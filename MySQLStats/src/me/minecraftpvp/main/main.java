package me.minecraftpvp.main;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{
	
	@Override
	public void onEnable(){
		MySQL.connect();
		
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS GeldSystem (UUID VARCHAR(100),Spielername VARCHAR(100), Geld INT(100))");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void onDisable(){
		MySQL.disconnect();
	}

}
