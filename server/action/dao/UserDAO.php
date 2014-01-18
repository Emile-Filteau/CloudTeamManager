<?php
require_once("action/dao/Connection.php");

class UserDAO {
	
	public static function authenticate($username, $password) {
		$connection = Connection::getConnection();
		$password = sha1($password);
		$statement = $connection->prepare('SELECT * FROM users WHERE username = :paramUsername AND password = :paramPassword'); 
		
		$statement->bindParam(":paramUsername", $username);
		$statement->bindParam(":paramPassword", $password);
		
		$statement->setFetchMode(PDO::FETCH_ASSOC);
		$statement->execute();
		$user = null;
		if($row = $statement->fetch()) {  
			$user = $row;
			
			//Remove the PASSWORD field
			$passIndex = array_search('password', array_keys($user));
			array_splice($user, $passIndex, 1);
		}
		Connection::closeConnection();
		return $user;
	}
	
	public static function register($username, $password, $email) {
		$connection = Connection::getConnection();
		$password = sha1($password);
		
		//Add the user to the database
		$statement = $connection->prepare('INSERT INTO users(username, password, email) VALUES (:username, :password, :email)');
		
		$statement->bindParam(":username", $username);
		$statement->bindParam(":password", $password);
		$statement->bindParam(":email", $email);
		$statement->execute();
		
		//Get the last inserted ID
		$statement = $connection->prepare('SELECT MAX(ID) "id" FROM users');
		$statement->setFetchMode(PDO::FETCH_ASSOC);
		$statement->execute();
		$row = $statement->fetch();
		
		//Get the user you just inserted and return it
		$statement = $connection->prepare('SELECT * FROM users WHERE id = :id');
		$statement->bindParam(":id", $row['id']);
		$statement->setFetchMode(PDO::FETCH_ASSOC);
		$statement->execute();
		$user = null;
		if($row = $statement->fetch()) {  
			$user = $row;
			$passIndex = array_search('password', array_keys($user));
			array_splice($user, $passIndex, 1);
		}
		Connection::closeConnection();
		return $user;
	}
	
}