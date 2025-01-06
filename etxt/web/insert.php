<?php 
include 'connect.php' ;
date_default_timezone_set("Asia/Ashgabat"); 
$timestamp = time();
$wagt=date('Y-m-d H:i:s', $timestamp); 
 

 $name=$_GET['name'];
 $zakazid=$_GET['zakazid'];
 $tipID=$_GET['tipID'];
 $jemi=$_GET['jemi'];
 $bir_baha=$_GET['bir_baha'];
 $jyns=$_GET['jyns'];
 $dusun=$_GET['dusun'];
 $tesgor=$_GET['tesgor'];
 $simsan=$_GET['simsan'];
 $dili=$_GET['dili'];
 
 $sql = "INSERT INTO `yumuslist` ( `zakazid`, `tipID`,`nameof`,`jemi`,`ostalos`,`bir_baha`,`jyns`,`dusun`,`tesgor`,`simsan`,`dili`,`timestp`,`act`) VALUES ( '$zakazid',  '$tipID', '$name', '$jemi', '$jemi', '$bir_baha', '$jyns', '$dusun',  '$tesgor', '$simsan', '$dili',  '$wagt',0);";
 

if ($conn->query($sql) === TRUE) {
  echo "Hasaba alyndy";
} else {
  echo "Error: " . $sql . "<br>" . $conn->error;
}


 ?>