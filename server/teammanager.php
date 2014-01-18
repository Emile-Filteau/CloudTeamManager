<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);

require_once("action/TeamManagerAction.php");
$action = new TeamManagerAction();

$paramString = stripslashes($_POST["params"]);
$params = json_decode($paramString, true);
if(isset($_POST["action"])) {
	if($_POST["action"] == "getUserTeams") {
		echo(json_encode($action->getUserTeams($params["user_id"])));
	} else if($_POST["action"] == "createTeam") {
		echo(json_encode($action->createTeam($params["team_name"], $params["user_id"])));
	} else if($_POST["action"] == "getTeamMembers") {
		echo(json_encode($action->getTeamMembers($params["team_id"])));
	} else if($_POST["action"] == "addMemberToTeam") {
		echo(json_encode($action->addMemberToTeam($params["team_id"], $params["user_id"])));
	}
}