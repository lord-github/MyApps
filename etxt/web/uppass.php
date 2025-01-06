<?php 
include 'connect.php' ;
 $uid=$_GET['uid'];
 $pass=$_GET['pass'];
 $sql = "Update  `users` set pass=".$pass." where ustb=".$uid;

if ($conn->query($sql) === TRUE) {
  echo "Hasaba alyndy";
} else {
  echo "Näsazlyk ýüze çykdy: ";
}


 ?>