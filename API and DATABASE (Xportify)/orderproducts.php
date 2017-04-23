<?php
require "conn.php";
$orderno=$_POST['orderno'];
$user_id=$_POST['user_id'];
$product_id=$_POST['product_id'];
$username=$_POST['username'];
$first_name=$_POST['first_name'];
$middle_name=$_POST['middle_name'];
$last_name=$_POST['last_name'];
$contactno=$_POST['contactno'];
$productname=$_POST['productname'];
$quantity=$_POST['quantity'];
$price=$_POST['price'];
$address=$_POST['address'];
$city=$_POST['city'];
$country=$_POST['country'];




	
		$sql_query ="insert into tbl_orderproducts values('','$user_id','$product_id','$username','$first_name','$middle_name','$last_name','$contactno','$productname','$quantity','$price','$address','$city','$country');";		

		if(mysqli_query($conn,$sql_query))
		{
	
			$data['status']="success";
			$data['message']="Order sent";
			

			echo json_encode($data);
			}
			

		else
		{
			$data['status']="failed";
			$data['message']="Failed.";
			echo json_encode($data);
		} 	 	

		
		
	

 
?>


