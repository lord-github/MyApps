<?php 
include 'connect.php' ;
$id=$_GET['id'];
$mass=array();
$sql="SELECT IFNULL(SUM(yumuslist.bir_baha),0) as toleg from yumuslist where yumuslist.tb in (SELECT `zaid` FROM `finsh` where userid=".$id." and toleg_stat=0);";
$stmt=$conn->prepare($sql);
// echo $sql;
$stmt->execute();
$stmt->bind_result($t1);

while ($stmt->fetch()) {
	// code...

	$temp=[
		'toleg'=>$t1
];
array_push($mass, $temp);
}
	echo json_encode($mass);


 ?>