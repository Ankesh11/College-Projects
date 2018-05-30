<!DOCTYPE html>
<html>
<body>
	<?php 
		include 'dbcon.php';
		
		$Pos = $_GET["Position"];
		$Available = $_GET["Available"]; 
		$sql = "UPDATE parkinglot SET Available = " . $Available . " WHERE Position = " . $Pos;
		print $sql;
		$result = $conn->query($sql);

     	$conn->close();
        
	?>
</body>
</html>