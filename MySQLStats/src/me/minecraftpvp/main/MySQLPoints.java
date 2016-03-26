package me.minecraftpvp.main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MySQLPoints {
	
	public static boolean isUserExists(UUID uuid){
		try{
		PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Punkte FROM GeldSystem WHERE UUID = ?");
		ps.setString(1, uuid.toString());
		ResultSet rs = ps.executeQuery();
		return rs.next();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
		
		}
	
	public static void update(UUID uuid, int amount, boolean remove, String playername){
		int geld = getGeld(uuid);
		
		if(remove){
			amount -= geld;
		}else{
			amount += geld;
		}
		if(isUserExists(uuid)){
			try{
		PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE GeldSystem SET Geld = ? WHERE UUID = ?");
		ps.setInt(1, amount);
		ps.setString(2, uuid.toString());
		ps.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}else{
			try{
		PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO GeldSystem (UUID,Spielername,Geld) VALUES (?,?,?)");
		ps.setString(1, uuid.toString());
		ps.setString(2, playername);
		ps.setInt(3, geld);
		ps.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void delete(UUID uuid){
		if(isUserExists(uuid)){
			try {
				PreparedStatement ps = MySQL.getConnection().prepareStatement("DELETE + FROM GeldSystem WHERE UUID = ?");
				ps.setString(1, uuid.toString());
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("[MySQL] Dieser Spieler ist nicht in der Datenbank eingetragen!");
		}
	}
	
	public static Integer getGeld(UUID uuid){
		try{
		PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Geld FROM GeldSystem WHERE UUID = ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				return rs.getInt("Geld");
				}
			
		}catch(SQLException e){
				e.printStackTrace();
			}
		return -1;
		}
		
	}


