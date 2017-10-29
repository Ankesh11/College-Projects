<script>

</script>
<?php
	require 'connect_to_database.inc.php';
	if(isset($_POST['add'])){
		if(isset($_POST['name']) && isset($_POST['type']) && isset($_POST['price'])){
			$name = $_POST['name'];
			$type = $_POST['type'];
			$price = $_POST['price'];
			if(!empty($name) && !empty($type) && !empty($price)){
				$query = "insert into `foods`(`name`,`type`,`price`) values('$name','$type','$price')";
				mysql_query($query);
				echo 'Item added.';
			}
		}
	}
	
	if(isset($_POST['remove'])){
		if(isset($_POST['name'])){
			$name = $_POST['name'];
			if(!empty($name)){
				$query = "delete from `foods` where `name`='$name'";
				mysql_query($query);
				echo 'Item removed.';
			}
		}
	}
?>
<!doctype html>
<html>
	<head>
		<style>
			#div{
				background-color:white;
				border-radius:5px;
				font-family:verdana;
				font-size:24px;
				width:350px;
				height:325px;
				padding:20px;
				margin:70px;
				float:left;
			}
			input,select{
				font-size:24px;
				font-family:sans-serif;
				border-radius:5px;
				margin:10px;
			}
			h3{
				text-align:center;
			}
			#button{
				background-color:cadetblue;
			}
		</style>
	</head>
	<body>
		<div id="div"><center>
			<h3>Add Item</h3>
			<form action="add_or_remove" method="POST" autocomplete="off">
				<input type="text" name="name" placeholder="Item Name"><br>
				Type:<select name="type">
					<option value="beverage">Beverage</option>
					<option value="chinese">Chinese</option>
					<option value="continental">Continental</option>
				</select><br>
				<input type="text" name="price" placeholder="Price" size="3"><br>
				<input id="button" type="submit" value="Add" name="add">
			</form></center>
		</div>
		
		<div id="div"><center>
			<h3>Remove Item</h3>
			<form action="add_or_remove" method="POST" autocomplete="off">
				<input type="text" name="name" placeholder="Item Name"><br>
				<input id="button" type="submit" value="Remove" name="remove">
			</form></center>
		</div>
	</body>
</html>