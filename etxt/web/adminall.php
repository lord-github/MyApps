<?php 
include 'connect.php' ;
 $currentDate = date("Y-m-d");

  $g=date("d",strtotime($currentDate));
  $a=date("m",strtotime($currentDate));
  $y=date("Y",strtotime($currentDate));
  
  
$startOfWeek = date("Y-m-d", strtotime("monday this week", strtotime($currentDate)));

$endOfWeek = date("Y-m-d", strtotime("sunday this week", strtotime($currentDate)));

 if (isset($_GET['id'])) {
 $id=$_GET['id'];
 } else {
  $id="0";
 }
 
 if (isset($_GET['type'])) {
 $type=$_GET['type'];
 } else {
  $type="none";
 }

 if ($type=='all') {
 $sql = "SELECT tipzadac.name_zadaca,t2.* from (SELECT yumuslist.tipID,t1.* from (SELECT users.fio,`finsh`.* FROM `finsh` INNER join users on users.ustb=userid where   zaid in (SELECT yumuslist.tb from yumuslist where yumuslist.zakazid=".$id.") )t1 INNER JOIN yumuslist on yumuslist.tb=zaid)t2 INNER join tipzadac on tipzadac.tb=tipid";
 }
 if ($type=='month') {
  
 $sql = "SELECT tipzadac.name_zadaca,t2.* from (SELECT yumuslist.tipID,t1.* from (SELECT users.fio,`finsh`.* FROM `finsh` INNER join users on users.ustb=userid where   zaid in (SELECT yumuslist.tb from yumuslist where yumuslist.zakazid=".$id.") and a=".$a." and y=".$y.")t1 INNER JOIN yumuslist on yumuslist.tb=zaid)t2 INNER join tipzadac on tipzadac.tb=tipid";
 }

 if ($type=='year') {
  
 $sql = "SELECT tipzadac.name_zadaca,t2.* from (SELECT yumuslist.tipID,t1.* from (SELECT users.fio,`finsh`.* FROM `finsh` INNER join users on users.ustb=userid where   zaid in (SELECT yumuslist.tb from yumuslist where yumuslist.zakazid=".$id.")  and y=".$y." )t1 INNER JOIN yumuslist on yumuslist.tb=zaid)t2 INNER join tipzadac on tipzadac.tb=tipid";
 }

if ($type=='day') {
  
 $sql = "SELECT tipzadac.name_zadaca,t2.* from (SELECT yumuslist.tipID,t1.* from (SELECT users.fio,`finsh`.*  FROM `finsh` INNER join users on users.ustb=userid where    zaid in (SELECT yumuslist.tb from yumuslist where yumuslist.zakazid=".$id.")  and y=".$y." and a=".$a." and g=".$g." )t1 INNER JOIN yumuslist on yumuslist.tb=zaid)t2 INNER join tipzadac on tipzadac.tb=tipid";
 } 
if ($type!='none') {
  // code...

$mass=array();
$stmt=$conn->prepare($sql);
// echo $sql;
$stmt->execute();
$stmt->bind_result($t1,$t2,$t3,$t4,$t5,$t6,$t7,$t8,$t9,$t10,$t11,$t12);

while ($stmt->fetch()) {
  // code...

  $temp=[
    'name_zadaca'=>$t1,
    'tipID'=>$t2,
    'fio'=>$t3,
    'tb'=>$t4,
    'zaid'=>$t5,
    'userid'=>$t6,
    'otmen'=>$t7,
    'tass'=>$t8,
    'g'=>$t9,
    'a'=>$t10,
    'y'=>$t11,
    'toleg_y'=>$t12
];
array_push($mass, $temp);
}
  echo json_encode($mass);
}



 ?>