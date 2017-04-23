<?php
require "conn.php";
$user_id=$_POST['user_id'];
$first_name=$_POST['first_name'];
$middle_name=$_POST['middle_name'];
$last_name=$_POST['last_name'];
$contactno=$_POST['contactno'];
$address=$_POST['address'];
$city=$_POST['city'];
$country=$_POST['country'];
$country_code=$_POST['country_code'];
$profile_img=$_POST['profile_img'];
$username=$_POST['username'];
$password=$_POST['password'];
$email_add=$_POST['email_add'];



$sql_query = "select username from tbl_user_buyer where username like '$username';";
	$result = mysqli_query($conn,$sql_query);

	if(mysqli_num_rows($result)>0)
	{
		$row = mysqli_fetch_assoc($result);

		$data['status']="failed";
		$data['message']="Failed to Register. Username already exists.";

		echo json_encode($data);
	}

	else
	{
	
		$sql_query ="insert into tbl_user_buyer	
		 values('','$first_name','$middle_name','$last_name','$contactno','$address','$city','$country','$country_code','','$username','$password','$email_add');";

		if(mysqli_query($conn,$sql_query))
		{
			
			$data['status']="success";
			$data['message']="Your registration is successful.";

			echo json_encode($data);
		}
		else{
			$data['status']="failed";
			$data['message']="Failed to Register.";

			echo json_encode($data);
		}
	}







 
?>


