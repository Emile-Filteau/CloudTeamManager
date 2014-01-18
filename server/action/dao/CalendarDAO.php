<?php
require_once("action/dao/Connection.php");

class CalendarDAO {
	
	public static function syncCalendar($user_id, $events) {
		$connection = Connection::getConnection();
		$priority = 0;
		foreach($events as $event) {
		
			$statement = $connection->prepare('INSERT INTO calendar_events 
			(user_id, START_DATE, END_DATE, name, description, priority) VALUES (:user_id, :START_DATE, :END_DATE, :name, :description, :priority)'); 
			$statement->bindParam(":user_id", $user_id);
			$statement->bindParam(":START_DATE", $event["start_date"]);
			$statement->bindParam(":END_DATE", $event["end_date"]);
			$statement->bindParam(":name", $event["name"]);
			$statement->bindParam(":description", $event["description"]);
			$statement->bindParam(":priority", $priority);
			
			$statement->setFetchMode(PDO::FETCH_ASSOC);
			$statement->execute();
		}
	}
}