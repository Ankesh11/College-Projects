<script>

</script>
<!doctype html>
<html>
	<head>
		<style>
			#pay{
				background-color:white;
				border-radius:5px;
				font-family:verdana;
				font-size:24px;
				width:350px;
				height:270px;
				padding:20px;
				margin:20px;
				margin-left:300px;
			}
			#pay input{
				font-size:24px;
				font-family:sans-serif;
				border-radius:5px;
				margin:10px;
			}
		</style>
	</head>
	<body>
	<p id="msg"></p><br>
		<div id="pay">
			<center>
			<form action="" method="POST">
				<input type="text" name="card_no" placeholder="Card number" size="16"><br>
				<input type="text" placeholder="MM-YYYY" size="7"><br>
				<input type="text" placeholder="CVV" size="3">
			</form>
			<input type="radio" name="payby">Cash &nbsp &nbsp &nbsp <input type="radio" name="payby">Card <br>
			<input type="button" value="Pay" onclick="document.getElementById('msg').innerHTML='Payment Successful'">
			</center>
		</div>
	</body>
</html>