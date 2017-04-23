<?php
require "conn.php";
$user_id=$_POST['user_id'];
$sql_query = "select * from tbl_orderproducts where user_id like '$user_id';";
$result = mysqli_query($conn, $sql_query);
$rows = array();
  while($r = mysqli_fetch_array($result))
   {
    array_push($rows,array("first_name"=>$r['first_name'],"middle_name"=>$r['middle_name'],"last_name"=>$r['last_name'],"contactno"=>$r['contact'],"address"=>$r['address'],"city"=>$r['city'],"country"=>$r['country'],"product_id"=>$r['product_id'],"productname"=>$r['productname'],"quantity"=>$r['quantity'],"price"=>$r['price'],));
    $data['status']='$success';
    
			
  }
  echo json_encode($rows);

?>