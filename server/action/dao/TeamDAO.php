<?php
require_once("action/dao/Connection.php");

class TeamDAO {
	
	public static function getUserTeams($user_id) {
		$connection = Connection::getConnection();
		
		$statement = $connection->prepare('SELECT * FROM teams WHERE id IN (SELECT team_id FROM team_members WHERE user_id = :paramId)'); 
		
		$statement->bindParam(":paramId", $user_id);
		
		$statement->setFetchMode(PDO::FETCH_ASSOC);
		$statement->execute();
		$teams = array();
		while($row = $statement->fetch()) {  
			array_push($teams, $row);
		}
		Connection::closeConnection();
		return $teams;
	}
	
	public static function createTeam($team_name, $user_id) {
		$connection = Connection::getConnection();
		
		//Add the user to the database
		$statement = $connection->prepare('INSERT INTO teams(name) VALUES (:name)');
		
		$statement->bindParam(":name", $team_name);
		$statement->execute();
		
		//Get the last inserted ID
		$statement = $connection->prepare('SELECT MAX(ID) "id" FROM teams');
		$statement->setFetchMode(PDO::FETCH_ASSOC);
		$statement->execute();
		$row = $statement->fetch();
		
		$statement2 = $connection->prepare('INSERT INTO team_members(user_id, team_id) VALUES (:user_id, :team_id)');
		
		$statement2->bindParam(":user_id", $user_id);
		$statement2->bindParam(":team_id", $row['id']);
		$statement2->execute();
		
		//Get the user you just inserted and return it
		$statement = $connection->prepare('SELECT * FROM teams WHERE id = :id');
		$statement->bindParam(":id", $row['id']);
		$statement->setFetchMode(PDO::FETCH_ASSOC);
		$statement->execute();
		$team = null;
		if($row = $statement->fetch()) {  
			$team = $row;
		}
		Connection::closeConnection();
		return $team;
	}
	
	public static function getTeamMembers($team_id) {
		$connection = Connection::getConnection();
		
		$statement = $connection->prepare('SELECT id, username, email FROM users WHERE id IN (SELECT user_id FROM team_members WHERE team_id = :paramId)'); 
		
		$statement->bindParam(":paramId", $team_id);
		
		$statement->setFetchMode(PDO::FETCH_ASSOC);
		$statement->execute();
		$users = array();
		while($row = $statement->fetch()) {
			array_push($users, $row);
		}
		Connection::closeConnection();
		return $users;
	}
	
	public static function addMemberToTeam($team_id, $username) {
		$connection = Connection::getConnection();
		
		$statement2 = $connection->prepare('SELECT * FROM users WHERE username = :username');
		$statement2->bindParam(":username", $username);
		$statement2->setFetchMode(PDO::FETCH_ASSOC);
		$statement2->execute();
		$row = $statement2->fetch();
		$user = $row;
		//Remove the PASSWORD field
		$passIndex = array_search('password', array_keys($user));
		array_splice($user, $passIndex, 1);
		
		$statement = $connection->prepare('INSERT INTO team_members(user_id, team_id) VALUES (:user_id, :team_id)');

		$statement->bindParam(":user_id", $row['id']);
		$statement->bindParam(":team_id", $team_id);
		
		$statement->setFetchMode(PDO::FETCH_ASSOC);
		$statement->execute();
		Connection::closeConnection();
		return $user;
	}
	
}