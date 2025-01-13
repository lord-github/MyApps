<?php 
include 'connect.php' ;
$id=$_GET['uid'];
$zid=$_GET['zid'];
$mass=array();
$sql="SELECT files  FROM `stepbystep` where zaid=".$zid." and useriD=".$id." group by step order by step asc";
$stmt=$conn->prepare($sql);
// echo $sql;
$stmt->execute();
$stmt->bind_result($t1);

while ($stmt->fetch()) {
	// code...

	$temp=[
		'ff'=>$t1
];
array_push($mass, $temp);
}
	echo json_encode($mass);


 ?>