package com.cloudteammanager.dal.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CloudTeamManagerDatabase extends SQLiteOpenHelper {

	public CloudTeamManagerDatabase(Context context, int version) {
		super(context, "cloud_team_manager", null, version);
	}

	public void createTables(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE PLAYERS " +
				"(" +
				"ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
				"NETWORK_ID INTEGER, " +
				"CALIMACIL_ID INTEGER, " +
				"USERNAME TEXT, " +
				"EXPERIENCE_POINTS INTEGER, " +
				"CREATION_DATE INTEGER, " +
				"LAST_CONNECTION INTEGER, " +
				"LAST_CHARACTER_ID INTEGER, " +
				"IS_ADMIN INTEGER, " +
				"IS_OWNER INTEGER, " +
				"REMEMBER_ME INTEGER, " +
				"OTK TEXT, " +
				"RESULT TEXT, " +
				"TIME_PLAYED INTEGER" +
				");");
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		createTables(db);
	}
	 	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Drop all the tables and refresh the content with network values
		/*
		db.execSQL("DROP TABLE IF EXISTS UTILS");
		db.execSQL("DROP TABLE IF EXISTS TOURNAMENTS_REMEMBER");
		db.execSQL("DROP TABLE IF EXISTS QUESTS");
		db.execSQL("DROP TABLE IF EXISTS CHARACTERS_QUESTS");
		db.execSQL("DROP TABLE IF EXISTS DEVICES_INFO");
		db.execSQL("DROP TABLE IF EXISTS AI_SPELLS");
		db.execSQL("DROP TABLE IF EXISTS AI");
		db.execSQL("DROP TABLE IF EXISTS CLASSES");
		db.execSQL("DROP TABLE IF EXISTS SPELLS");
		db.execSQL("DROP TABLE IF EXISTS STANCES");
		db.execSQL("DROP TABLE IF EXISTS GESTURES");
		db.execSQL("DROP TABLE IF EXISTS TECHTREES");
		db.execSQL("DROP TABLE IF EXISTS CHARACTERS");
		db.execSQL("DROP TABLE IF EXISTS CLASSES");
		db.execSQL("DROP TABLE IF EXISTS PLAYERS");
		db.execSQL("DROP TABLE IF EXISTS RESOURCES");
		db.execSQL("DROP TABLE IF EXISTS GAME_SETTINGS");
		db.execSQL("DROP TABLE IF EXISTS STANDALONE");*/
		onCreate(db);
	}
	
	public void updateStaticTables(SQLiteDatabase db) {
		/*
		db.execSQL("DROP TABLE IF EXISTS TOURNAMENTS_REMEMBER");
		db.execSQL("DROP TABLE IF EXISTS QUESTS");
		db.execSQL("DROP TABLE IF EXISTS AI_SPELLS");
		db.execSQL("DROP TABLE IF EXISTS AI");
		db.execSQL("DROP TABLE IF EXISTS SPELLS");
		db.execSQL("DROP TABLE IF EXISTS GESTURES");
		db.execSQL("DROP TABLE IF EXISTS TECHTREES");
		db.execSQL("DROP TABLE IF EXISTS STANCES");
		db.execSQL("DROP TABLE IF EXISTS CLASSES");
		db.execSQL("DROP TABLE IF EXISTS RESOURCES");
		
		createStaticTables(db);*/
	}
	
	public void emptyTable(SQLiteDatabase db, String tableName) {
		db.delete(tableName, null, null);
	}
}