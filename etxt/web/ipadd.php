<?php 
include 'connect.php' ;
 $uid=$_GET['uid'];
 $ip=$_GET['ip'];
 $sql = "INSERT INTO `logggs` ( `uid`,`ip`) VALUES ( '$uid','$ip');";

if ($conn->query($sql) === TRUE) {
  echo "Hasaba alyndy";
} else {
  echo "Näsazlyk ýüze çykdy: ";
}


 ?>