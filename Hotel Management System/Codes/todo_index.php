<script>

</script>
<?php
	require 'connect_to_database.inc.php';
	$current_date = $_COOKIE['current_date'];
	$query = "select `id`,`guest1`,`guest2`,`guest3`,`guest4`,`room_no`,`checkin`,`checkout`,`total` from `guests` where `scope`='1' and `checkout`='$current_date'";
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
			h2{
				text-align:center;
			}
		</style>
	</head>
	
	<body>
		<form action="get_bill.php" method="POST" style="float:right">
			<span id="msg" style="margin-right:50px;"></span>
			Guest Id:<input type="text" name="id" required size="3" autocomplete="off">
			<input type="submit" value="Check out" name="checkout" style="background-color:cadetblue;margin-right:100px;">
		</form>
		<h2>Guests checking out today</h2>
		<table>
		  <tr>
			<th>Id</th>
			<th>Guest-1</th>
			<th>Guest-2</th>
			<th>Guest-3</th>
			<th>Guest-4</th>
			<th>Room No.</th>
			<th>Ckeck in</th>
			<th>Check out</th>
			<th>Amount</th>
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