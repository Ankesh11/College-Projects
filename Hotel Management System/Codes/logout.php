<?php
	session_start();
	session_destroy();
	//$http_referer = $_SERVER['HTTP_REFERER'];
	header('Location: login.php');
?>