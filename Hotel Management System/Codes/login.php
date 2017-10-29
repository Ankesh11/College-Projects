<?php
	session_start();
	function loggedin(){
		if(isset($_SESSION['user_id']) && !empty($_SESSION['user_id']))
			return true;
		else
			return false;
	}
	if(loggedin())
		header('Location: home.php');
?>
<!doctype html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="style1.css">
		<title>Login</title>
	</head>
	
	<body id="body">
		<nav>
			
			<form action="login.php" method="POST" id="login_form">
				<input type="text" name="lemail" maxlength="30" placeholder="Email">
				<input type="password" name="lpassword" maxlength="15" placeholder="Password">
				<input type="submit" value="Login" name="login">
			</form>
			<p id="login_error"></p>
		</nav>
		<hr>
		<div id="signup">
			
			<form action="login.php" method="POST" id="signup_form">
				<h2>Register here</h2>
				<p id="error"><p>
				<input type="text" name="uname" maxlength="15" placeholder="Name"><br>
				<input type="email" name="uemail" maxlength="30" placeholder="Email"><br>
				<input type="password" name="upassword" maxlength="15" placeholder="Password"><br>
				<input type="password" name="uc_password" maxlength="15" placeholder="Retype Password"><br>
				<input type="text" name="umobile" maxlength="10" placeholder="Mobile"><br>
				<textarea name="uaddress" placeholder="Address" rows="3" cols="20"></textarea><br>
				<input type="radio" name="usex" value="male">Male<input type="radio" name="sex" value="female">Female<br>
				<input type="submit" value="Signup" name="signup">
			</form>
		</div>
	</body>
</html>

<?php
	require 'connect_to_database.inc.php';
	if(isset($_POST['signup'])){
		if(isset($_POST['uname']) && isset($_POST['uemail']) && isset($_POST['upassword']) && isset($_POST['uc_password']) && 
		   isset($_POST['umobile']) && isset($_POST['uaddress']) && isset($_POST['usex'])){
			$name = $_POST['uname'];
			$email = $_POST['uemail'];
			$password = $_POST['upassword'];
			$c_password = $_POST['uc_password'];
			$mobile = $_POST['umobile'];
			$address = $_POST['uaddress'];
			$sex = $_POST['usex'];
			if(!empty($name)&&!empty($email)&&!empty($password)&&!empty($c_password)&&!empty($mobile)&&!empty($address)&&!empty($sex)){
				if($password != $c_password){
					?>
					<script>
						document.getElementById('error').innerHTML = "Password don\'t match";
					</script>
					<?php
				}
				else{
					$hash_pass = md5($password);
					$query = "insert into `users`(`name`,`email`,`password`,`mobile`,`address`,`sex`) values('$name','$email','$hash_pass','$mobile','$address','$sex')";
					if($query_run = mysql_query($query)){
						?>
						<script>
							document.getElementById('error').style.color = "green";
							document.getElementById('error').innerHTML = "Registered successfully!";
						</script>
						<?php
					}
					else{
						?>
						<script>
							document.getElementById('error').innerHTML = "Error in registration. Try again!";
						</script>
						<?php
					}
				}
			}
			else{
				?>
				<script>
					document.getElementById('error').innerHTML = "Please enter in all fields";
				</script>
				<?php
			}
		}
		else{
				?>
				<script>
					document.getElementById('error').innerHTML = "Please enter in all fields";
				</script>
				<?php
		}
	}
?>

<?php
	if(isset($_POST['login'])){
		if(isset($_POST['lemail']) && isset($_POST['lpassword'])){
			$email  = $_POST['lemail'];
			$password = $_POST['lpassword'];
			if(!empty($email) && !empty($password)){
				$hash_pass = md5($password);
				$query = "select * from `users` where `email`='$email' and `password`='$hash_pass'";
				if($query_run = mysql_query($query)){
					$num_rows = mysql_num_rows($query_run);
					if($num_rows == 1){
						$user_id = mysql_result($query_run,0,'id');
						$_SESSION['user_id']= $user_id;
						header('Location: home.php');
					}
				}
				else{
					?>
					<script>
						document.getElementById("login_error").innerHTML = "Email/Password don\'t match!";
					</script>
					<?php
				}
			}
			else{
				?>
				<script>
						document.getElementById("login_error").innerHTML = "Please enter in the fields";
				</script>
				<?php
			}
		}
	}
?>
