<?php 
include 'connect.php' ;
 $uid=$_GET['uid'];
 $ser=$_GET['ser'];
 $sql = "Update  `users` set money=money-".$ser." where ustb=".$uid;

if ($conn->query($sql) === TRUE) {
  echo "Hasaba alyndy";
} else {
  echo "Näsazlyk ýüze çykdy: ";
}


 ?>