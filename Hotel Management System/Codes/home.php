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
	if(!isset($_COOKIE['current_date'])){
		$_COOKIE['current_date']='';
	}
	$current_date = $_COOKIE['current_date'];
	$query1 = "select `id`,`room_no` from `guests` where `checkout`<'$current_date'";
	$run1= mysql_query($query1);
	while($row = mysql_fetch_assoc($run1)){
		$id = $row['id'];
		$room_no = $row['room_no'];
		$query2 = "update `guests` set `scope`='0' where `id` = '$id'";
		mysql_query($query2);
		$query3 = "update `rooms` set `occupied`='0',`occupant_name`='',`checkin`=NULL,`checkout`=NULL where `room_no`= '$room_no'";
		mysql_query($query3);
	}
?>
<script>
</script>
<!doctype html>
<html>
	<head>
		<style>
			#home_div{
				background-image:url("home.jpg");
				width:100%;
				height:87%;
				border-radius:5px;
				backgound-repeat:no-repeat;
				background-position:fixed;
			}
			footer{
				height:40px;
				background-color:darkgray;
				border-radius:5px;
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
			
		</div>
		<footer>
		
		</footer>
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