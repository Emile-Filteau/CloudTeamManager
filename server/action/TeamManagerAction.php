<?php

require_once("action/dao/TeamDAO.php");

class TeamManagerAction {
	
	public function __construct() {
	}
	
	public function getUserTeams($user_id) {
		return TeamDAO::getUserTeams($user_id);
	}
	
	public function createTeam($team_name, $user_id) {
		return TeamDAO::createTeam($team_name, $user_id);
	}
}