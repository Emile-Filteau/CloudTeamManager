<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);
require_once("action/UserManagerAction.php");
$action = new UserManagerAction();
$paramString = stripslashes($_POST["params"]);
$params = json_decode($paramString, true);
if(isset($_POST["action"])) {
	if($_POST["action"] == "getTeamMeetings") {
		echo(json_encode($action->getTeamMeetings($params["team_id"])));
	}
}