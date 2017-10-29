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

<?php
	require 'connect_to_database.inc.php';
	$current_date = $_COOKIE['current_date'];
	if(isset($_POST['login'])){
		if(isset($_POST['adname']) && isset($_POST['adpass'])){
			$adname = $_POST['adname'];
			$adpass = $_POST['adpass'];
			if(!empty($adname) && !empty($adpass)){
				if($adname == 'admin' && $adpass == 'admin'){
					header('Location: admin.php');
				}
				else{
					?>
						<script>
							document.getElementById('error').innerHTML= "Plz provide valid details"
						</script>
					<?php
				}
			}else{
				?>
					<script>
						document.getElementById('error').innerHTML= "Plz enter in fields"
					</script>
				<?php
			}
		}
	}
?>
<!doctype html>
<html>
	<head>
		<style>
			#home_div{
				background-color:#c0c0c0;
				width:100%;
				height:93.8%;
				border-radius:5px;
				backgound-repeat:no-repeat;
				background-position:fixed;
			}
			#box{
				background-color:#FF9200;
				border-radius:5px;
				font-family:verdana;
				font-size:24px;
				width:350px;
				height:325px;
				padding:20px;
				margin-left:470px;
				margin-top:100px;
				float:left;
			}
			input{
				font-size:24px;
				font-family:sans-serif;
				border-radius:5px;
				margin:10px;
			}
			#button{
				background-color:cadetblue;
			}
		</style>
		
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
		<div id="home_div">
			<center>
				<div id="box">
					<h3>Login as admin</h3>
					<form action="admin_login.php" method="POST" autocomplete="off">
						<input type="text" name="adname"><br>
						<input type="password" name="adpass"><br>
						<input id="button" type="submit" value="Login" name="login">
					</form>
					<p id="error" style="font-size:15px;color:red;"></p>
				</div>
			</center>
		</div>
</body>
</html>

<?php
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