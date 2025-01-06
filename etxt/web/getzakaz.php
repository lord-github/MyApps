<?php 
include 'connect.php' ;
$id=$_GET['id'];
$mass=array();
$sql="SELECT `tipID`,`nameof`,`jemi`,`ostalos`,`timestp` FROM `yumuslist` where zakazid=".$id;
$stmt=$conn->prepare($sql);
// echo $sql;
$stmt->execute();
$stmt->bind_result($t1,$t2,$t3,$t4,$t5);

while ($stmt->fetch()) {
	// code...

	$temp=[
		'tipID'=>$t1, 
		'nameof'=>$t2,
		'jemi'=>$t3, 
		'ostalos'=>$t4,
		'timestp'=>$t5
];
array_push($mass, $temp);
}
	echo json_encode($mass);


 ?>