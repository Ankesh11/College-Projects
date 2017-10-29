<?php
	require 'connect_to_database.inc.php';
	$current_date = $_COOKIE['current_date'];
	$query1 = "select `id`,`room_no` from `guests` where `checkout` < '$current_date'";
	$run1= mysql_query($query1);
	while($row = mysql_fetch_assoc($run1)){
		$id = $row['id'];
		$room_no = $row['room_no'];
		$query2 = "update `guests` set `scope`='0' where `id` = '$id'";
		mysql_query($query2);
		$query3 = "update `rooms` set `occupied`='0',`occupant_name`='',`checkin`=NULL,`checkout`=NULL where `room_no`= '$room_no'";
		mysql_query($query3);
	}
?>