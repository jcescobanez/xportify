<?php
require "conn.php";
$sql_query = "select * from tbl_products_distributor;";
$result = mysqli_query($conn, $sql_query);
$rows = array();
  while($r = mysqli_fetch_array($result))
   {
    array_push($rows,array("product_id"=>$r['product_id'],"country"=>$r['country'],"productname"=>$r['productname'],"productdesc"=>$r['productdesc'],"quantity"=>$r['quantity'],""=>$r['productdesc'],"price"=>$r['price']));
    $data['status']='$success';
    
			
  }
  echo json_encode($rows);

?>