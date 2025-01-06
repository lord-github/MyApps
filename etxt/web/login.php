<?php 
include 'connect.php' ;
$l=$_GET['l'];
$p=$_GET['p'];
$mass=array();
$sql="SELECT fio,tip,ustb FROM `users` where login='".$l."' and pass='".$p."'";
$stmt=$conn->prepare($sql);
// echo $sql;
$stmt->execute();
$stmt->bind_result($t1,$t2,$t3);

while ($stmt->fetch()) {
	// code...

	$temp=[
		'fio'=>$t1, 
		'tip'=>$t2,
		'tb'=>$t3
];
array_push($mass, $temp);
}
	echo json_encode($mass);


 ?>