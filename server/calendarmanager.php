<?php
error_reporting(E_ALL);
ini_set('display_errors', 1);
require_once("action/CalendarManagerAction.php");
$action = new CalendarManagerAction();
$paramString = stripslashes($_POST["params"]);
$params = json_decode($paramString, true);
if(isset($_POST["action"])) {
	if($_POST["action"] == "syncCalendar") {;
		echo(json_encode($action->syncCalendar($params["user_id"], $params["events"])));
	} else if($_POST["action"] == "getTeamEvents") {;
		echo(json_encode($action->getTeamEvents($params["team_id"])));
	}
}