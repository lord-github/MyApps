<?php 
include 'connect.php' ;
$id=$_GET['id'];
$mass=array();
$sql="SELECT sum(ser) as toleg from `wywodrequest` where num=".$id;
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