<?php
require_once("action/dao/Connection.php");

class CalendarDAO {
	
	public static function syncCalendar($user_id, $events) {
		$connection = Connection::getConnection();
		
		$statement = $connection->prepare('DELETE FROM calendar_events WHERE user_id = :user_id'); 
		$statement->bindParam(":user_id", $user_id);
		
		$statement->setFetchMode(PDO::FETCH_ASSOC);
		$statement->execute();
		
		$priority = 0;
		$event_type = 0;
		foreach($events as $event) {
		
			$statement = $connection->prepare('INSERT INTO calendar_events 
			(user_id, event_type, START_DATE, END_DATE, name, priority) VALUES (:user_id, :event_type, :START_DATE, :END_DATE, :name, :priority)'); 
			$statement->bindParam(":user_id", $user_id);
			$statement->bindParam(":event_type", $event_type);
			$statement->bindParam(":START_DATE", $event["start_date"]);
			$statement->bindParam(":END_DATE", $event["end_date"]);
			$statement->bindParam(":name", $event["name"]);
			$statement->bindParam(":priority", $priority);
			
			$statement->setFetchMode(PDO::FETCH_ASSOC);
			$statement->execute();
		}
	}
	
	public static function getTeamEvents($team_id) {
		$connection = Connection::getConnection();
		$now = time();
		$then = $now + 21 * 24 * 60 * 60 * 1000;
		$statement = $connection->prepare('SELECT * FROM calendar_events 
		WHERE user_id in (SELECT user_id FROM team_members WHERE team_id = :team_id)
		AND
		START_DATE >= :now
		AND
		END_DATE <= :after');
		
		$statement->bindParam(":team_id", $team_id);
		$statement->bindParam(":now", $now);
		$statement->bindParam(":after", $then);
		
		$statement->setFetchMode(PDO::FETCH_ASSOC);
		$statement->execute();
		$dispos = array();
		while($row = $statement->fetch()) {
			array_push($dispos, $row);
		}
		
		Connection::closeConnection();
		return $dispos;
	}
}