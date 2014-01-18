<?php

require_once("action/dao/TaskDAO.php");

class TaskManagerAction {
	
	public function __construct() {
	}
	
	public function getTeamTasks($team_id) {
		return TaskDAO::getTeamTasks($team_id);
	}
}