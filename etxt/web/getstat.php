<?php 
include 'connect.php' ;
$id=$_GET['md'];
$mass=array();
$sql="SELECT status  FROM `tempreq` where md=".$id;
$stmt=$conn->prepare($sql);
// echo $sql;
$stmt->execute();
$stmt->bind_result($t1);

while ($stmt->fetch()) {
	// code...

	$temp=[
		'stat'=>$t1
];
array_push($mass, $temp);
}
	echo json_encode($mass);


 ?>