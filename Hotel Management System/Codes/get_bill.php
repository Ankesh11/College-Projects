<script>

</script>
<?php
	require 'connect_to_database.inc.php';
	$current_date = $_COOKIE['current_date'];
	$current_time = $_COOKIE['current_time'];
	if(isset($_POST['get'])){
		if(isset($_POST['getby']) && !empty($_POST['getby']) && isset($_POST['source']) && !empty($_POST['source'])){
			$getby = $_POST['getby'];
			$source = $_POST['source'];
			if($getby == 'id'){
				$query = "select * from `guests` where `id`='$source' and `scope`='1'";
				$run = mysql_query($query);
				$row = mysql_fetch_assoc($run);
				$name = $row['guest1'];
				$id = $row['id'];
				$checkin = $row['checkin'];
				$checkout = $row['checkout'];
				$room_no = $row['room_no'];
				$charges = $row['charges'];
				$total = $row['total'];
				$extra = $total-$charges;
			}
			else if($getby == 'mobile'){
				$query = "select * from `guests` where `mobile`='$source' and `scope`='1'";
				$run = mysql_query($query);
				$row = mysql_fetch_assoc($run);
				$name = $row['guest1'];
				$id = $row['id'];
				$checkin = $row['checkin'];
				$checkout = $row['checkout'];
				$room_no = $row['room_no'];
				$charges = $row['charges'];
				$total = $row['total'];
				$extra = $total-$charges;
			}
			else{
				echo 'Invalid entry.';
			}
		}else{
			echo 'Plz enter in fields.';
		}
	}
	if(isset($_POST['checkout'])){
		if(isset($_POST['id']) && !empty($_POST['id'])){
			$id = $_POST['id'];
			$query = "select * from `guests` where `id`='$id' and `scope`='1'";
			$run = mysql_query($query);
			if(mysql_num_rows($run)==1){
				$row = mysql_fetch_assoc($run);
				$name = $row['guest1'];
				$id = $row['id'];
				$checkin = $row['checkin'];
				$checkout = $row['checkout'];
				$room_no = $row['room_no'];
				$charges = $row['charges'];
				$total = $row['total'];
				$extra = $total-$charges;
				
				$query = "select * from `collections` where `date`='$current_date'";
				$run = mysql_query($query);
				if(mysql_num_rows($run)==0){
					$query = "insert into `collections`(`date`,`amount`) values('$current_date','$total')";
					mysql_query($query);
				}
				else if(mysql_num_rows($run)==1){
					$row = mysql_fetch_assoc($run);
					$amount = $row['amount'] + $total;
					$query = "update `collections` set `amount`='$amount' where `date`='$current_date'";
					mysql_query($query);
				}
				$query = "update `guests` set `checkout`='$current_date',`scope`='0' where `id`='$id'";
				mysql_query($query);
				$query = "update `rooms` set `occupied`='0',`occupant_name`='',`checkin`=NULL,`checkout`=NULL where `room_no`='$room_no'";
				mysql_query($query);
				
			}else{
				echo 'Invalid entry.';
			}
		}
	}
?>
<?php
	if(isset($_POST['submit'])){
		if(isset($_POST['id']) && isset($_POST['feedback'])){
			$id= $_POST['id'];
			$feedback = $_POST['feedback'];
			if(!empty($id) && $feedback!=NULL){
				$query = "select `feedback` from `guests` where `id`='$id' and `feedback`= 'NULL'";
				$run = mysql_query($query);
				if(mysql_num_rows($run)==0){
					echo 'feedback already set';
				}else{
				$query = "update `guests` set `feedback`='$feedback' where `id`='$id'";
				mysql_query($query);
				}
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
				height:250px;
				padding:20px;
				margin:20px;
				float:left;
			}
			#div input,select{
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
			#bill{
				float:left;
				width:225px;
				margin:10px;
				margin-left:50px;
				padding:10px;
				border-radius:5px;
				height:80%;
				line-height:20px;
				font-family:sans-serif;
			}
			#field{
				border-radius:5px;
			}
		</style>
	</head>
	<body>
		<div id="div"><center>
			<h3>Get Bill</h3>
			<form action="get_bill.php" method="POST" autocomplete="off">
				<select name="getby">
					<option value="id">Id</option>
					<option value="mobile">Mobile</option>
				</select><br>
				<input type="text" name="source" placeholder="Id or Mobile"><br>
				<input id="button" type="submit" value="Get" name="get">
			</form></center>
		</div>
		
		<div id="bill">
			<h2>Bill</h2>
			<textarea id="field" rows="25" cols="30"><?php if(isset($_POST['get'])){
				echo 'Date: '.$current_date."\n"; echo 'Time: '.$current_time."\n";
				echo 'Name: '.$name."\n"; echo 'Id: '.$id."\n";
				echo 'Checkin: '.$checkin."\n";
				echo 'Checkout: '.$checkout."\n";
				echo 'Room no: '.$room_no."\n";
				echo "-----------------------------\n";
				echo 'Room cost: '.$charges."\n";
				echo 'Fooding: '.$extra."\n";
				echo "-----------------------------\n";
				echo 'Total: '.$total."\n";
				echo "\n\n\n-----------THANKS------------";
			}
			else if(isset($_POST['checkout'])){
				echo 'Date: '.$current_date."\n"; echo 'Time: '.$current_time."\n";
				echo 'Name: '.$name."\n"; echo 'Id: '.$id."\n";
				echo 'Checkin: '.$checkin."\n";
				echo 'Checkout: '.$checkout."\n";
				echo 'Room no: '.$room_no."\n";
				echo "-----------------------------\n";
				echo 'Room cost: '.$charges."\n";
				echo 'Fooding: '.$extra."\n";
				echo "-----------------------------\n";
				echo 'Total: '.$total."\n";
				echo "\n\n\n-----------THANKS------------";
			}?></textarea><br>
				<button style="float:right;background-color:cadetblue;" onclick="window.location.href='payment.php'">Print & Pay</button>
		</div>
		<div style="float:left;margin-left:50px;padding:10px;">
			<h2>Feedback</h2>
			<form action="get_bill.php" method="POST" autocomplete="off">
				Id:<input type="text" id="id" name="id" size="3"><br>
				<textarea rows="8" name="feedback" cols="35"></textarea><br>
				<input id="button" type="submit" value="Submit" name="submit" style="float:right;margin:10px;"	>
			</form>
		</div>
	</body>
</html>

