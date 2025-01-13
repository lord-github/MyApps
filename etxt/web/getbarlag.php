<?php 
include 'connect.php' ;
$id=$_GET['id'];
$mass=array();
$sql="SELECT count(tb) as sany  FROM `finsh` where tassyklanan=0 and  userid=".$id;
$stmt=$conn->prepare($sql);
// echo $sql;
$stmt->execute();
$stmt->bind_result($t1);

while ($stmt->fetch()) {
	
	$temp=[
		'sany'=>$t1
];
array_push($mass, $temp);
}
	echo json_encode($mass);


 ?>