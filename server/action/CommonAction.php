<?php
session_start();

abstract class CommonAction {
	public static $VISIBILITY_PUBLIC = 0;
	public static $VISIBILITY_MEMBER = 1;
	public static $VISIBILITY_ADMIN = 2;
	
	public $pageVisibility;
	
	public function __construct($pageVisibility) {
		$this->pageVisibility = $pageVisibility;
	}
	
	public function execute() {
		
		if(isset($_GET["logout"])) {
			session_unset();
			session_destroy();
			session_start();
		}
		
		if(!isset($_SESSION["visibility"])) {
			$_SESSION["visibility"] = CommonAction::$VISIBILITY_PUBLIC;
		}
		if($this->pageVisibility > $_SESSION["visibility"]) {
			header("location:tournaments.php");
			exit;
		}
		
		$this->executeAction();
	}
	
	abstract protected function executeAction();
	
	public function isConnected() {
		$isConnected = false;
		if($_SESSION["visibility"] > CommonAction::$VISIBILITY_PUBLIC) {
			$isConnected = true;
		}
		return $isConnected;
	}
	
	function isAdmin() {
		$isAdmin = false;
		if($_SESSION["visibility"] >= CommonAction::$VISIBILITY_ADMIN) {
			$isAdmin = true;
		}
		return $isAdmin;
	}
	
	function getCurrentPage() {
		$pageURL = 'http';
		$pageURL .= "://";
		if ($_SERVER["SERVER_PORT"] != "80") {
			$pageURL .= $_SERVER["SERVER_NAME"].":".$_SERVER["SERVER_PORT"].$_SERVER["REQUEST_URI"];
		} else {
			$pageURL .= $_SERVER["SERVER_NAME"].$_SERVER["REQUEST_URI"];
		}
		$pos = strpos($pageURL, "?");
		$pageURL = substr($pageURL, 0, $pos);
		return $pageURL;
	}
	
	public function isValid($str) {
		$valid = true;
		if(empty($str))
			$valid = false;
		if(ctype_space($str))
			$valid = false;
		return $valid;
	}
}