<?php
require_once("action/dao/Connection.php");

class CalendarDAO {
	
	public static function syncCalendar($user_id, $events) {
		$connection = Connection::getConnection();
		
		foreach($events as $event) {
		
			$statement = $connection->prepare('INSERT INTO calendar_events 
			(user_id, START_DATE, END_DATE, name, description, priority) VALUES (:user_id, :START_DATE, :END_DATE, :name, :description, :priority)'); 
			$statement->bindParam(":user_id", $user_id);
			$statement->bindParam(":START_DATE", $events["start_date"]);
			$statement->bindParam(":END_DATE", $events["end_date"]);
			$statement->bindParam(":name", $events["name"]);
			$statement->bindParam(":description", $events["description"]);
			$statement->bindParam(":priority", 0);
			
			$statement->setFetchMode(PDO::FETCH_ASSOC);
			$statement->execute();
		}
	}
}