<?php
	$mysql_host = "localhost";
	$mysql_user = "root";
	$mysql_pass = "";
	$mysql_db = "db1";
	
	class serverException extends Exception{
		public function showSpecific(){
			return 'Error on '.$this->getLine().' in '.$this->getFile().'<br>';
		}
	}
	class databaseException extends Exception{
		public function showSpecific(){
			return 'Error on '.$this->getLine().' in '.$this->getFile().'<br>';
		}
	}
	try{
		if(!@mysql_connect($mysql_host,$mysql_user,$mysql_pass)){
			throw new serverException('Couldn\'t connect to server<br>');
		}
		else if(!@mysql_select_db($mysql_db)){
			throw new databaseException('Couldn\'t select database<br>');
		}
		else{
			//echo 'Connected!';
		}
	}
	catch(serverException $e){
		echo 'Error: '.$e->getMessage().'<br>';
		echo $e->showSpecific();
	}
	catch(databaseException $e){
		echo 'Error: '.$e->getMessage().'<br>';
		echo $e->showSpecific();
	}
?>