<?php

class Connection {
	private static $connection;
	
	public static function getConnection() {
		if(!isset(Connection::$connection)) {
			Connection:$connection = new PDO("mysql:dbname=cloud_team_manager;host=localhost", "ctm_user", "asfa43fs42rtwsd");
		}
		return $connection;
	}
	
	public static function closeConnection() {
		Connection::$connection = null;
	}
}