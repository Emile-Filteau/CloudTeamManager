<?php
require_once("action/dao/Connection.php");

class TaskDAO {
	
	public static function getTeamTasks($team_id) {
		$connection = Connection::getConnection();
		
		$statement = $connection->prepare('SELECT * FROM task WHERE team_id = :teamId'); 
		
		$statement->bindParam(":teamId", $team_id);
		
		$statement->setFetchMode(PDO::FETCH_ASSOC);
		$statement->execute();
		$tasks = array();
		while($row = $statement->fetch()) {  
			array_push($tasks, $row);
		}
		Connection::closeConnection();
		return $tasks;
	}
	
}