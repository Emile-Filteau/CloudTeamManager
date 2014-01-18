<?php
require_once("action/UserManagerAction.php");
$action = new UserManagerAction();
if(isset( $_POST["action"]) && isset( $_POST["params"])) {
	$params = json_decode($_POST["params"], true);
	if(isset($_POST["action"])) {
		if($_POST["action"] == "authenticate") {
			echo(json_encode($action->authenticate($params["username"], $params["password"])));
		}
		else if($_POST["action"] == "register") {
			echo(json_encode($action->register($params["username"], $params["password"], $params["email"])));
		}
	}
}