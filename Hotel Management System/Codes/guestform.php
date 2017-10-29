<?php
	require 'connect_to_database.inc.php';
?>
<!doctype html>
<html>
	<head>
		<style>
			#guest_form{
				margin:15px;
				font-family:verdana;
				font-size:15px;
				font-size:24px;
			}
			#guest_form input,textarea,select{
				font-size:24px;
				font-family:sans-serif;
				border-radius:5px;
				margin:10px;
			}
			#button{
				background-color:cadetblue;
			}
		</style>
	</head>
	<body>
		<form action="guestform.php" method="POST" id="guest_form">
				<p id="error" style="font-size:14px"><p>
				Guest1<input type="text" name="fn1" maxlength="30" placeholder="Firstname" required="required">
				<input type="text" name="ln1" maxlength="30" placeholder="Lastname"><br>
				Guest2<input type="text" name="fn2" maxlength="30" placeholder="Firstname">
				<input type="text" name="ln2" maxlength="30" placeholder="Lastname"><br>
				Guest3<input type="text" name="fn3" maxlength="30" placeholder="Firstname">
				<input type="text" name="ln3" maxlength="30" placeholder="Lastname"><br>
				Guest4<input type="text" name="fn4" maxlength="30" placeholder="Firstname">
				<input type="text" name="ln4" maxlength="30" placeholder="Lastname"><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" name="mobile" maxlength="10" placeholder="Mobile" required="required">
				<input type="text" name="id_no" maxlength="20" placeholder="Id Card No." required="required"><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<textarea name="address" placeholder="Address" rows="3" cols="22" required="required"></textarea><br>
				Check in:<input type="date"  name="checkin" value="<?php echo $_COOKIE['current_date']; ?>" readonly>
				Check out:<input type="date" name="checkout" required min="<?php echo $_COOKIE['current_date']; ?>"><br>
				Select Room:
				<select required="required" name="room_no">
					<?php
						$query = "select `room_no` from `rooms` where `occupied`='0'";
						$run = mysql_query($query);
							while($row=mysql_fetch_assoc($run)){
								foreach($row as $val){
									$t= substr($val,0,1);
									if($t=='R')
										$type="Regular";
									if($t=='D')
										$type="Double Bed";
									if($t=='K')
										$type="King Size";
								?>
								<option value=<?php echo $val; ?> > <?php echo $type.'-'.$val; ?></option><?php } ?>
						</tr>
							<?php } ?>
				</select><br>
				<input type="submit" value="Add Guest" name="add_guest" id="button" style="margin-left:450px;">
		</form>
	</body>
</html>

<?php
	if(isset($_POST['add_guest'])){
		if(isset($_POST['fn1']) && isset($_POST['ln1']) && isset($_POST['mobile']) && isset($_POST['id_no']) && 
		isset($_POST['address']) && isset($_POST['checkin']) && isset($_POST['checkout']) && isset($_POST['room_no'])){
			$guest1 = $_POST['fn1'].' '.$_POST['ln1'];
			$guest2 = $_POST['fn2'].' '.$_POST['ln2'];
			$guest3 = $_POST['fn3'].' '.$_POST['ln3'];
			$guest4 = $_POST['fn4'].' '.$_POST['ln4'];
			$mobile = $_POST['mobile'];
			$id_no = $_POST['id_no'];
			$address = $_POST['address'];
			$checkin = $_POST['checkin'];
			$checkout = $_POST['checkout'];
			$room_no = trim($_POST['room_no']);
			$no_of_person = 0;
			$s = substr($room_no,0,1);
			if($s == 'R')
				$charge = 1000;
			else if($s == 'D')
				$charge = 2000;
			else if($s == 'K')
				$charge = 3000;
			
			$din = time();
			$dout = strtotime($checkout);
			$n_days = ceil(($dout-$din)/(60 * 60 * 24) + 1);
			$total =$charge*$n_days;
			$sum_total = $total;
			if(!empty($guest1) && !empty($mobile) && !empty($id_no) && !empty($address) && !empty($checkin) && !empty($checkout)
				&& !empty($room_no)){
				if(!empty($_POST['fn1']) && empty($_POST['fn2']) && empty($_POST['fn3']) && empty($_POST['fn4']))
					$no_of_person = 1;
				if(!empty($_POST['fn1']) && !empty($_POST['fn2']) && empty($_POST['fn3']) && empty($_POST['fn4']))
					$no_of_person = 2;
				if(!empty($_POST['fn1']) && !empty($_POST['fn2']) && !empty($_POST['fn3']) && empty($_POST['fn4']))
					$no_of_person = 3;
				if(!empty($_POST['fn1']) && !empty($_POST['fn2']) && !empty($_POST['fn3']) && !empty($_POST['fn4']))
					$no_of_person = 4;
				
				if(empty($_POST['fn2']) && empty($_POST['fn3']) && empty($_POST['fn4']))
					$occupant_names = $guest1;
				if(empty($_POST['fn3']) && empty($_POST['fn4']))
					$occupant_names = $guest1.'|'.$guest2;
				if(empty($_POST['fn4']))
					$occupant_names = $guest1.'|'.$guest2.'|'.$guest3;
				if(!empty($_POST['fn4']))
					$occupant_names = $guest1.'|'.$guest2.'|'.$guest3.'|'.$guest4;
				$query1 = "insert into `guests`(`guest1`,`guest2`,`guest3`,`guest4`,`mobile`,`id_no`,`address`,`checkin`,`checkout`,`room_no`,`no_of_person`,`charges`,`total`,`scope`) 
				values('$guest1','$guest2','$guest3','$guest4','$mobile','$id_no','$address','$checkin','$checkout','$room_no','$no_of_person','$total','$sum_total','1')";
				$query2 = "update rooms set `occupied`='1',`occupant_name`='$occupant_names',`checkin`='$checkin',`checkout`='$checkout' where `room_no`='$room_no'";
				if((mysql_query($query1) and mysql_query($query2)) or die(mysql_error())){
					?>
					<script>
						document.getElementById('error').innerHTML = "Done"
					</script>
					<?php
				}
				else{
					?>
					<script>
						document.getElementById('error').innerHTML = "Error"
					</script>
					<?php
				}
				
			}
		}
		else 
			echo 'error';
	}
?>
