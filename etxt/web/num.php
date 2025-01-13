<?php 
include 'connect.php' ;
$mass=array();
$sql="SELECT num  as num  FROM `number`";
$stmt=$conn->prepare($sql);
// echo $sql;
$stmt->execute();
$stmt->bind_result($t1);

while ($stmt->fetch()) {
	// code...

	$temp=[
		'num'=>$t1
];
array_push($mass, $temp);
}
	echo json_encode($mass);


 ?>