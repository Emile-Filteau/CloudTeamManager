<?php

class Connection {
	private static $connection;
	
	public static function getConnection() {
		if(!isset(Connection::$connection)) {
			Connection::$connection = new PDO("mysql:dbname=jumbalan_cloud_team_manager;host=localhost", "jumbalan_ctmuser", "asfa43fs42rtwsd");
		}
		return Connection::$connection;
	}
	
	public static function closeConnection() {
		Connection::$connection = null;
	}
}