<?php

require_once("action/dao/UserDAO.php");

class UserManagerAction {
	
	public function __construct() {
	}
	
	public function authenticate($username, $password) {
		return UserDAO::authenticate($username, $password);
	}
	
	public function register($username, $password, $email) {
		return UserDAO::register($username, $password, $email);
	}
}