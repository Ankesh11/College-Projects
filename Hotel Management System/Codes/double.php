<?php
	require 'connect_to_database.inc.php';
	$query = "select `room_no`,`occupied`,`occupant_name` from `rooms` where `type`='double_size'";
	$run=mysql_query($query);
?>	
<!doctype html>
<html>
	<head>
		<style>
			table {
				font-family: arial, sans-serif;
				border-collapse: collapse;
				width: 100%;
			}
			th{
				background-color:green;
			}
			td, th {
				border: 1px solid #dddddd;
				text-align: center;
				padding: 8px;
			}

			tr:nth-child(even) {
				background-color: cadetblue;
			}
		</style>
	</head>
	
	<body>
		<h2 style="text-align:center">Double Bed Rooms</h2>
		<table>
			<caption>Room Details</caption>
		  <tr>
			<th>Room No.</th>
			<th>Status</th>
			<th>Occupant Name</th>
		  </tr>
		  <?php
				while($row=mysql_fetch_assoc($run)){
				?>
			<tr>
				<?php
					foreach($row as $val){
						if($val=='0')
							$val="Vacant";
						if($val=='1')
							$val="Occupied";
					?>
					<td><?php echo $val; ?></td><?php } ?>
			</tr>
				<?php } ?>
		</table>
	</body>
</html>