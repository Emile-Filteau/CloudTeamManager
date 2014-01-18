<?php
require_once("action/dao/Connection.php");

class CalendarDAO {
	
	public static function syncCalendar($user_id, $events) {
		$connection = Connection::getConnection();
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
}