<?php 
include 'connect.php' ;
$ii=$_GET['uid'];
$mass=array();
$sql="SELECT `jemi`,`ostalos`,`timestp`,tipzadac.name_zadaca as ady FROM `yumuslist` INNER JOIN tipzadac on yumuslist.tipID=tipzadac.tb WHERE yumuslist.zakazid=".$ii;
$stmt=$conn->prepare($sql);
// echo $sql;
$stmt->execute();
$stmt->bind_result($t1,$t2,$t3,$t4);

while ($stmt->fetch()) {
	// code...

	$temp=[
		'jem'=>$t1,
		'galan'=>$t2,
		'wagt'=>$t3,
		'ady'=>$t4
];
array_push($mass, $temp);
}
	echo json_encode($mass);


 ?>