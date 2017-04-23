<?php
require "conn.php";
$country=$_POST['country'];
$sql_query = "select * from tbl_products_distributor where country like '$country';";
$result = mysqli_query($conn, $sql_query);
$rows = array();
  while($r = mysqli_fetch_array($result))
   {
    array_push($rows,array("user_id"=>$r['user_id'],"product_id"=>$r['product_id'],"country"=>$r['country'],"productname"=>$r['productname'],"productdesc"=>$r['productdesc'],"quantity"=>$r['quantity'],"price"=>$r['price']));
    $data['status']='$success';
    
			
  }
  echo json_encode($rows);

?>