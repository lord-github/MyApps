<?php 
include 'connect.php' ;

if (isset($_GET['id'])) {
$id=$_GET['id'];
} else {
	$id=0;
}
$mass=array();
$sql="SELECT tipzadac.name_zadaca,t1.* from (SELECT yumuslist.tipID,g,a,y,tassyklanan,otmena,finsh.tb as terb FROM `finsh` INNER JOIN yumuslist on yumuslist.tb=zaid where userid=".$id.")t1 INNER JOIN tipzadac on tipzadac.tb=tipID GROUP by terb,y,a,g desc;";
$stmt=$conn->prepare($sql);
// echo $sql;
$stmt->execute();
$stmt->bind_result($t1,$t2,$t3,$t4,$t5,$t6,$t7,$t8);

while ($stmt->fetch()) {
	
	$temp=[
		'name'=>$t1,
		'tip'=>$t2,
		'g'=>$t3,
		'a'=>$t4,
		'y'=>$t5,
		'tassyklanan'=>$t6,
		'otmena'=>$t7,
		'terb'=>$t8
];
array_push($mass, $temp);
}
	echo json_encode($mass);


 ?>