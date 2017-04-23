<?php


require "conn.php";
$username=$_POST['username'];
$password=$_POST['password'];


$sql_query = "select * from tbl_user_buyer where username like '$username' and password like '$password';";

$result = mysqli_query($conn,$sql_query);


if (mysqli_num_rows($result)>0) {
			
	$row = mysqli_fetch_assoc($result);
				

		
	$data['status']="success";
	$data['message']="User logged in successfully.";
	//$data['user_id']=$row["user_id"];
	$data['first_name']=$row["first_name"];
        $data['last_name']=$row["last_name"];
        $data['middle_name']=$row["middle_name"];
        $data['email_add']=$row["email_add"];
	$data['username']=$row["username"];
$data['contactno']=$row["contactno"];
$data['country']=$row["country"];
$data['address']=$row["address"];
$data['city']=$row["city"];



	echo json_encode($data);
	



}

else
{
	$data['status']="failed";
	$data['message']="Invalid Username/Password.";

	echo json_encode($data);

}

?><?php


