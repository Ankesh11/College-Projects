<?php
	require 'connect_to_database.inc.php';
	$query = "select * from `collections` order by `date` desc";
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
		<h2 style="text-align:center">Daily Collections</h2>
		<table>
		  <tr>
			<th>Id</th>
			<th>Date</th>
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
