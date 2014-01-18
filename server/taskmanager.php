<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);

require_once("action/TaskManagerAction.php");
$action = new TaskManagerAction();

$paramString = stripslashes($_POST["params"]);
$params = json_decode($paramString, true);
if(isset($_POST["action"])) {
	if($_POST["action"] == "getTeamTasks") {
		echo(json_encode($action->getUserTeams($params["team_id"])));
	}
}