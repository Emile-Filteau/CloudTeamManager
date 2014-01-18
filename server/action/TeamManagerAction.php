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
	
	public function getTeamMembers($team_id) {
		return TeamDAO::getTeamMembers($team_id);
	}
	
	public function addMemberToTeam($team_id, $username) {
		return TeamDAO::addMemberToTeam($team_id, $username);
	}
}