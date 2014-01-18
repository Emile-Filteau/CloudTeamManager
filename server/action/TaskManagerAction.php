<?php

require_once("action/dao/TaskDAO.php");

class TaskManagerAction {
	
	public function __construct() {
	}
	
	public function getTeamTasks($team_id) {
		return TaskDAO::getTeamTasks($team_id);
	}
	
	public function createTask($team_id, $name, $estimated_time, $member_id) {
		return TaskDAO::createTask($team_id, $name, $estimated_time, $member_id);
	}
}