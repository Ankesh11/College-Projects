<?php
	require 'connect_to_database.inc.php';
	$query = "select `id`,`guest1`,`checkin`,`checkout`,`total` from `guests` where `scope`='1'";
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
				background-color: white;
			}
		</style>
	</head>
	
	<body>
		<h2 style="text-align:center">Guests</h2>
		
		<table>
			<caption>Guest List</caption>
		  <tr>
			<th>Guest Id</th>
			<th>Guest-1</th>
			<th>Ckeck in</th>
			<th>Check out</th>
			<th>Account</th>
		  </tr>
		  <?php
				while($row=mysql_fetch_assoc($run)){
				?>
			<tr>
				<?php
					foreach($row as $val){
						
					?>
					<td><?php echo $val; ?></td><?php } ?>
			</tr>
				<?php } ?>
		</table>
	</body>
</html>
