<?php

require_once("action/dao/MeetingDAO.php");

class TaskManagerAction {
	
	public function __construct() {
	}
	
	public function getTeamMeetings($team_id) {
		return MeetingDAO::getTeamMeetings($team_id);
	}
}