<?php
	require 'connect_to_database.inc.php';
	$query = "select `id`,`guest1`,`guest2`,`guest3`,`guest4`,`room_no`,`checkin`,`checkout` from `guests` where `scope`='0'";
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
		<h2 style="text-align:center">Guests</h2>
		<table>
			<caption>Guest List</caption>
		  <tr>
			<th>Id</th>
			<th>Guest-1</th>
			<th>Guest-2</th>
			<th>Guest-3</th>
			<th>Guest-4</th>
			<th>Room No.</th>
			<th>Ckeck in</th>
			<th>Check out</th>
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
<script>
					var today = new Date();
					var dd = today.getDate();
					var mm = today.getMonth()+1;

					var yyyy = today.getFullYear();
					if(dd<10){
						dd='0'+dd
					} 
					if(mm<10){
						mm='0'+mm
					} 
					var today = yyyy+'-'+mm+'-'+dd;
					document.cookie="current_date"+"="+today;
</script>
