<?php 
include 'connect.php' ;
if (isset($_GET['uid'])) {
 $uid=$_GET['uid'];
} else {
  $uid='0';
}
if (isset($_GET['zid'])) {
 $zid=$_GET['zid'];
} else {
  $zid='0';
}
if (isset($_GET['t'])) {
 $t=$_GET['t'];
} else {
 $t='0';
}
if (isset($_GET['o'])) {
 $o=$_GET['o'];
} else {
  $o='0';
}
 
if ($t!='0') {
$sql = "Update  `finsh` set  tassyklanan=".$t." where userid=".$uid." and zaid=".$zid;
} else {
$sql = "Update  `finsh` set  otmena=".$o."  where userid=".$uid." and zaid=".$zid;
}
 
echo $sql;
echo "\n";
if ($conn->query($sql) === TRUE) {
  echo "Hasaba alyndy";
} else {
  echo "Näsazlyk ýüze çykdy: ";
}


 ?>