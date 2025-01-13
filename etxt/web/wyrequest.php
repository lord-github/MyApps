<?php 
include 'connect.php' ;
 $uid=$_GET['uid'];
 $ip=$_GET['ser'];
 $sql = "INSERT INTO `wywodrequest` ( `num`,`ser`,`status`) VALUES ( '$uid','$ip','0');";

if ($conn->query($sql) === TRUE) {
  echo "Hasaba alyndy";
} else {
  echo "Näsazlyk ýüze çykdy: ";
}


 ?>