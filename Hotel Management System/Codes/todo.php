<script>
</script>
<!doctype html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="status.css">
		<script src="script.js"></script>
	</head>
	<body>
		<div>
		<nav id="header">
			<ul>
				<li><a href="home.php">Home</a></li>
				<li><a href="status.php">Status</a></li>
				<li><a href="guests.php">Guests</a></li>
				<li><a href="foods.php">Foods</a></li>
				<li><a href="todo.php">To Do</a></li>
				<li><a href="admin_login.php">Administrator</a></li>
				<li style="float:right;"><a href="logout.php">Logout!</a></li>
				<li id="username" style="float:right;margin-top:8px;margin-right:10px;font-size:20px;color:aqua"></li>
				<li id="time" style="float:right;margin-top:8px;margin-right:40px;font-size:20px;color:yellow"><script> setInterval(currentTime,1000);</script></li>
			</ul>
		</nav>
		</div>
		<div id="side_nav">
			<ul>
				<li><a href="get_bill.php" target="frame"><strong>Get Bill</strong></a></li>
			</ul>
		</div>
		<div id="details">
			<iframe name="frame" id="frame" style="border-radius:10px;background-color:silver;" width="100%" height="100%" border="0" src="todo_index.php" frameBorder="0"></iframe>
		</div>
</body>
</html>

<?php
	require 'connect_to_database.inc.php';
	session_start();
	if(isset($_SESSION['user_id']) && !empty($_SESSION['user_id'])){
		$user_id=$_SESSION['user_id'];
		$query = "select `name` from `users` where `id`='$user_id'";
		$run = mysql_query($query);
		$name = mysql_result($run,0,'name');
		?>
		<script type="text/javascript">
			var name = '<?php echo $name; ?>';
			document.getElementById("username").innerHTML = name;
		</script>
		<?php
	}
	else{
		header('Location: logout.php');
	}
?>