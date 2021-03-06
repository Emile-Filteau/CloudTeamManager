<?php
require_once("action/dao/Connection.php");

class TaskDAO {
	
	public static function getTeamTasks($team_id) {
		$connection = Connection::getConnection();
		
		$statement = $connection->prepare('SELECT task.*, users.username FROM task, users WHERE task.team_id = :teamId AND users.id = task.member_id'); 
		
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
	
	public static function createTask($team_id, $name, $estimated_time, $member_id) {
		$connection = Connection::getConnection();
		
		$statement = $connection->prepare('INSERT INTO task (team_id, member_id, name, estimated_time, took_time, status) 
		VALUES (:team_id, :member_id, :name, :estimated_time, :took_time, :status)'); 
		$took = 0;
		$status = 1;
		$statement->bindParam(":team_id", $team_id);
		$statement->bindParam(":member_id", $member_id);
		$statement->bindParam(":name", $name);
		$statement->bindParam(":estimated_time", $estimated_time);
		$statement->bindParam(":took_time", $took);
		$statement->bindParam(":status", $status);
		
		$statement->setFetchMode(PDO::FETCH_ASSOC);
		$statement->execute();
		
		Connection::closeConnection();
	}
}