<?php
$hostname="mysql5.000webhost.com";
$database="a1050813_ageaits";
$username="a1050813_its";
$password="agea85203";
$localhost=  mysql_connect($hostname,$username,$password)or trigger_error(mysql_error(),E_USER_ERROR);
mysql_select_db($database);
//$nombre=$_POST[''];
$nombre="JosePepe";
$query="insert into tblusuarios(regNombre) values('$nombre')";
$query=  mysql_query($nombre) or die(mysql_error());
mysql_close($localhost);
?>
