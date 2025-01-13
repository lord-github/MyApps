<?php 
include 'connect.php' ;
 $md=$_GET['md'];
 $uid=$_GET['uid'];
 $sql = "INSERT INTO `tempreq` ( `md`,`uid`,`status`) VALUES ( '$md','$uid','0');";

if ($conn->query($sql) === TRUE) {
  echo "Hasaba alyndy";
} else {
  echo "Näsazlyk ýüze çykdy: ";
}


 ?>