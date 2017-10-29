<script>

</script>
<?php
	require 'connect_to_database.inc.php';
	$query = "select * from `rooms` where `occupied`='0'";
	$run = mysql_query($query);
	$available_rooms = mysql_num_rows($run);
	$occupied_rooms = 30 - $available_rooms;
	$query = "select * from `rooms` where `occupied`='0' and `type`='regular'";
	$run = mysql_query($query);
	$regular_rooms = mysql_num_rows($run);
	$query = "select * from `rooms` where `occupied`='0' and `type`='double_size'";
	$run = mysql_query($query);
	$double_rooms = mysql_num_rows($run);
	$query = "select * from `rooms` where `occupied`='0' and `type`='king_size'";
	$run = mysql_query($query);
	$king_rooms = mysql_num_rows($run);
	
	$query = "select `no_of_person` from `guests` where `scope`='1'";
	$run = mysql_query($query);
	$no_of_guests = mysql_num_rows($run);
	$no_of_person = 0;
	while($row = mysql_fetch_assoc($run)){
		$no_of_person += $row['no_of_person'];
	}
	$current_date = $_COOKIE['current_date'];
	$query = "select * from `guests` where `checkout`='$current_date' and `scope`='1'";
	$run = mysql_query($query);
	$checking_out_today = mysql_num_rows($run);
?>
<!doctype html>
<html>
	<head>
		<style>
			#rooms{
				width:450px;
				border-radius:7px;
				float:left;
				margin:20px;
				background-color:white;
			}
			#guests{
				width:450px;
				float:left;
				border-radius:7px;
				margin:20px;
				margin-left:50px;
				background-color:white;
			}
			table {
				font-family: arial, sans-serif;
				border-collapse: collapse;
				width: 100%;
			}
			td, th {
				text-align: left;
				padding: 5px;
			}
		</style>
	</head>
	<body>
		<div id="rooms">
			<table>
				<caption><h3>Rooms</h3></caption>
				<tr>
					<td>Total no. of rooms:</td>
					<td>30</td>
				</tr>
				<tr>
					<td>Occupied Rooms:</td>
					<td><?php echo $occupied_rooms; ?></td>
				</tr>
				<tr>
					<td>Available Rooms:</td>
					<td><?php echo $available_rooms; ?></td>
				</tr>
				<tr>
					<td>Available Regular Rooms:</td>
					<td><?php echo $regular_rooms; ?></td>
				</tr>
				<tr>
					<td>Available Double-bed Rooms:</td>
					<td><?php echo $double_rooms; ?></td>
				</tr>
				<tr>
					<td>Available King-sized bed Rooms:</td>
					<td><?php echo $king_rooms; ?></td>
				</tr>
			</table>
		</div>
		
		<div id="guests">
			<table>
				<caption><h3>Guests</h3></caption>
				<tr>
					<td>Total no. of guests:</td>
					<td><?php echo $no_of_guests; ?></td>
				</tr>
				<tr>
					<td>Total no. of person:</td>
					<td><?php echo $no_of_person; ?></td>
				</tr>
				<tr>
					<td>checking out today:</td>
					<td><?php echo $checking_out_today; ?></td>
				</tr>
			</table>
		</div>
	</body>
</html>