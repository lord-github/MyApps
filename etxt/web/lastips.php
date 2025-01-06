<?php 
include 'connect.php' ;
$uid=$_GET['uid'];
$mass=array();
$sql="SELECT ip FROM `logggs` where uid=".$uid." order by tb desc LIMIT 5;";
$stmt=$conn->prepare($sql);
// echo $sql;
$stmt->execute();
$stmt->bind_result($t1);

while ($stmt->fetch()) {
	// code...

	$temp=[
		'ips'=>$t1
];
array_push($mass, $temp);
}
	echo json_encode($mass);


 ?>