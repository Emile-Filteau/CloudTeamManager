<?php

require_once("action/dao/TeamDAO.php");

class TeamManagerAction {
	
	public function __construct() {
	}
	
	public function getUserTeams($user_id) {
		return TeamDAO::getUserTeams($user_id);
	}
}