<?php
require_once("action/dao/Connection.php");

class UserDAO {
	
	public static function getTeamMeetings($team_id) {
		$connection = Connection::getConnection();
		$statement = $connection->prepare('SELECT * FROM meetings WHERE team_id = :team_id'); 
		$statement->bindParam(":team_id", $team_id);
		
		$statement->setFetchMode(PDO::FETCH_ASSOC);
		$statement->execute();
		
		$meetings = array();
		if($row = $statement->fetch()) {  
			array_push($meetings, $row);
		}
		Connection::closeConnection();
		return $meetings;
	}
	
}