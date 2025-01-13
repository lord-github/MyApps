<?php 
include 'connect.php' ;
$id=$_GET['id'];
$mass=array();
$sql="SELECT money as toleg from users where  ustb=".$id;
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