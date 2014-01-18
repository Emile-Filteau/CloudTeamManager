<?php

require_once("action/dao/CalendarDAO.php");

class CalendarManagerAction {
	
	public function __construct() {
	}
	
	public function syncCalendar($user_id, $events_array) {
		return CalendarDAO::syncCalendar($user_id, $events_array);
	}
	
	public function getTeamEvents($team_id) {
		return CalendarDAO::getTeamEvents($team_id);
	}
}