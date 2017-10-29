<script>
		
</script>
<?php
	require 'connect_to_database.inc.php';
	if(isset($_POST['submit'])){
		$foods = array();
		$total=0;
		if(isset($_POST['guest_id'])){
			$id = $_POST['guest_id'];
			$query = "select * from `guests` where `id`='$id' and `scope`='1'";
			$run = mysql_query($query);
			if(@mysql_num_rows($run)==1){
				$row = mysql_fetch_assoc($run);
				$g_total = $row['total'];
				$beverage = array();
				$bquantity = array();
				$chinese = array();
				$chquantity = array();
				$continental = array();
				$coquantity = array();
				
				if(isset($_POST['beverage']) && isset($_POST['bquantity']) && !empty($_POST['beverage']) && !empty($_POST['bquantity'])){
					$beverage = $_POST['beverage'];
					$quantity = $_POST['bquantity'];
					foreach($quantity as $q){
						if(!empty($q)){
							array_push($bquantity,$q);
						}
					}
					$size = sizeof($beverage);
					for($i = 0;$i<$size; $i++){
						$f=trim($beverage[$i]);
						$q=trim($bquantity[$i]);
						$query = "select * from `foods` where `name`='$f'";
						$run = mysql_query($query);
						$row=mysql_fetch_assoc($run);
						$price = $row['price'];
						$cost = $price * $q;
						$total+=$cost;
						$foods += array($f.'-'.$q.': '=>$cost);
					}
				}
				if(isset($_POST['chinese']) && isset($_POST['chquantity'])){
					$chinese = $_POST['chinese'];
					$quantity = $_POST['chquantity'];
					foreach($quantity as $q){
						if(!empty($q)){
							array_push($chquantity,$q);
						}
					}
					$size = sizeof($chinese);
					for($i = 0;$i<$size; $i++){
						$f=$chinese[$i];
						$q=$chquantity[$i];
						$query = "select * from `foods` where `name`='$f'";
						$run = mysql_query($query);
						$row=mysql_fetch_assoc($run);
						$price = $row['price'];
						$cost = $price * $q;
						$total+=$cost;
						$foods += array($f.'-'.$q.': '=>$cost);
					}
				}
				if(isset($_POST['continental']) && isset($_POST['coquantity'])){
					$continental = $_POST['continental'];
					$quantity = $_POST['coquantity'];
					foreach($quantity as $q){
						if(!empty($q)){
							array_push($coquantity,$q);
						}
					}
					$size = sizeof($continental);
					for($i = 0;$i<$size; $i++){
						$f=$continental[$i];
						$q=$coquantity[$i];
						$query = "select * from `foods` where `name`='$f'";
						$run = mysql_query($query);
						$row=mysql_fetch_assoc($run);
						$price = $row['price'];
						$cost = $price * $q;
						$total+=$cost;
						$foods += array($f.'-'.$q.': '=>$cost);
					}
				}
				$g_total += $total;
				$query = "update `guests` set `total`='$g_total' where `id`='$id'";
				if(mysql_query($query)){
					echo 'Order Placed.';
				}
				else{
					echo 'error!';
				}
			}else{
				echo 'Invalid guest id';
			}
		}
	}
?>
<!doctype html>
<html>
	<head>
		<style>
			.list,textarea{
				float:left;
				width:250px;
				margin:2px;
				background-color:white;
				padding:10px;
				border-radius:5px;
				height:80%;
				line-height:30px;
				font-family:sans-serif;;
			}
			.items{
				float:left;
				margin:5px;
			}
			.quantity{
				float:right;
				border-radius:10px;
			}
			#textarea{
				float:left;
				width:20px;
				padding:10px;
				border-radius:5px;
				height:80%;
				line-height:20px;
				font-family:sans-serif;
			}
		</style>
	</head>
	<body>
		<form action="order_foods.php" method="POST">
			<div class="list">
			<h3>Beverage</h3><hr>
			<?php
				$query = "select * from `foods` where `type`='beverage'";
				$run = mysql_query($query);
				while($row = mysql_fetch_assoc($run)){
					$food = $row['name'];
					$price = $row['price'];
			?>
				<input class="items" type="checkbox" name="beverage[]" value=<?php echo $food; ?>><?php echo $food.'-Rs'.$price; ?></input>
				<input class="quantity" type="number" name="bquantity[]" size="1" min="0" max="5"><br><?php } ?>
				
			</div>
			<div class="list">
			<h3>Chinese</h3><hr>
			<?php
				$query = "select * from `foods` where `type`='chinese'";
				$run = mysql_query($query);
				while($row = mysql_fetch_assoc($run)){
					$food = $row['name'];
					$price = $row['price'];
			?>
				<input class="items" type="checkbox" name="chinese[]" value=<?php echo $food; ?>><?php echo $food.'-Rs'.$price; ?>
				<input class="quantity" type="number" name="chquantity[]" size="1" min="0" max="5"><br><?php } ?>
				
			</div>
			<div class="list">
			<h3>Continental</h3><hr>
			<?php
				$query = "select * from `foods` where `type`='continental'";
				$run = mysql_query($query);
				while($row = mysql_fetch_assoc($run)){
					$food = $row['name'];
					$price = $row['price'];
			?>
				<input class="items" type="checkbox" name="continental[]" value=<?php echo $food; ?>><?php echo $food.'-Rs'.$price; ?>
				<input class="quantity" type="number" name="coquantity[]" size="1" min="0" max="5"><br><?php } ?>
				
			</div>
			<div id="textarea">
				<h4>Receipt</h4>
				<textarea id="tarea" rows="20" cols="15"><?php if(isset($_POST['submit'])){
					$query = "select * from `guests` where `id`='$id'";
					$run = mysql_query($query);
					$row=mysql_fetch_assoc($run);
					$name = $row['guest1'];
					$room_no = $row['room_no'];
					echo 'Date: '.$_COOKIE['current_date']."\n"; echo 'Time: '.$_COOKIE['current_time']."\n";
					echo 'Name: '.$name."\n"; echo 'Room no: '.$room_no."\n"; echo 'Id: '.$id."\n";
					echo "---------------------------------\n";
					foreach($foods as $a=>$b){
						echo $a.$b."\n";
					}
					echo "---------------------------------\n";
					echo 'Total: '.$total;
				} ?></textarea><br>
				<input type="button" value="Print" style="margin-left:180px;background-color:cadetblue;" onclick="document.getElementById('tarea').innerHTML='';">
			</div>
			<div style="clear:both;margin-left:300px;">
			Guest Id: <input type="text" name="guest_id" size="3" autocomplete="off">
				<input type="submit" value="Order" name="submit" style="background-color:cadetblue;">
			</div>
		</form>
	</body>
</html>

<?php
	
?>