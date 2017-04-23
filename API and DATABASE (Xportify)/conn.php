<?php
	$server = "localhost";
	$username = "christiannocum";
	$password = "w6htqm7URqaL";
	$db = "codeblazedb";

$conn = mysqli_connect($server,$username,$password,$db);
if(!$conn)
{
	//echo"Connection Error!".mysqli_connect_error();
}
else
{
	//echo"Database Successfully Created";
}
?>