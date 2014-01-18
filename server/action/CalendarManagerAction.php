<?php

require_once("action/dao/CalendarDAO.php");

class CalendarManagerAction {
	
	public function __construct() {
	}
	
	public function syncCalendar($user_id, $events_array) {
		//$events = json_decode($events_array);
		return CalendarDAO::syncCalendar($user_id, $events_array);
	}
}