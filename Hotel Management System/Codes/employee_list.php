<?php
	require 'connect_to_database.inc.php';
	$query = "select `id`,`name`,`email`,`mobile`,`address` from `users`";
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
		<h2 style="text-align:center">Employee</h2>
		<form action="employee_list.php" method="POST" style="float:right">
			<span id="msg" style="margin-right:50px;color:red;"></span>
			Employee Id:<input type="text" name="empid" required size="3" autocomplete="off">
			<input type="submit" value="Remove" name="remove" style="background-color:cadetblue;margin-right:100px;">
		</form>
		<table>
			<caption>Employee List</caption>
		  <tr>
			<th>Id</th>
			<th>Name</th>
			<th>E-Mail</th>
			<th>Mobile</th>
			<th>Address</th>
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
<?php
	if(isset($_POST['remove'])){
		if(isset($_POST['empid']) && !empty($_POST['empid'])){
			$id = $_POST['empid'];
			$query = "delete from `users` where `id`='$id'";
			if(mysql_query($query)){
				?>
					<script>
						document.getElementById('msg').innerHTML="User Removed";
					</script>
				<?php
			}else{
				?>
					<script>
						document.getElementById('msg').innerHTML="No such user";
					</script>
				<?php
			}
		}else{
			?>
					<script>
						document.getElementById('msg').innerHTML="Enter user id";
					</script>
				<?php
		}
	}
?>