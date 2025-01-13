<?php 
include 'connect.php' ;
 $uid=$_GET['uid'];
 $v=$_GET['v'];
 $zid=$_GET['zid'];
 $sql = "Update  `users` set pass=".$pass." where ustb=".$uid;

if ($conn->query($sql) === TRUE) {
  echo "Hasaba alyndy";
} else {
  echo "Näsazlyk ýüze çykdy: ";
}


 ?>