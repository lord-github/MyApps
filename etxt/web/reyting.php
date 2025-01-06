<?php 
include 'connect.php' ;
$id=$_GET['id'];
$mass=array();
$sql="SELECT count(tb) as sany  FROM `finsh` where userid=7 and tassyklanan=".$id." and toleg_stat=0";
$stmt=$conn->prepare($sql);
// echo $sql;
$stmt->execute();
$stmt->bind_result($t1);

while ($stmt->fetch()) {
	// code...

	$temp=[
		'rey'=>$t1
];
array_push($mass, $temp);
}
	echo json_encode($mass);


 ?>