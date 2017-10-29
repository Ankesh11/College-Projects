
<!doctype html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="nav.css">
	</head>
	<body>
<nav id="header">
	<ul>
		<li><a href="">Home</a></li>
		<li><a href="">About</a></li>
		<li><a href="">Status</a></li>
		<li><a href="">Guests</a></li>
		<li><a href="">Administrator</a></li>
		<li><a href="">Contact</a></li>
		<li style="float:right;"><a href="logout.php">Logout!</a></li>
		<li id="username" style="float:right;margin-top:8px;margin-right:10px;font-size:20px;color:blue"></li>
	</ul>
	
</nav>
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