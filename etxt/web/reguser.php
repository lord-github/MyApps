<?php 
include 'connect.php' ;
date_default_timezone_set("Asia/Ashgabat"); 
$timestamp = time();
$wagt=date('Y-m-d H:i:s', $timestamp); 
 
function generateRandomString($length = 20) {
    $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_/*!.,';
    $charactersLength = strlen($characters);
    $randomString = '';
    for ($i = 0; $i < $length; $i++) {
        $randomString .= $characters[rand(0, $charactersLength - 1)];
    }
    return $randomString;
}


 $fio=$_GET['fio'];
 $login=$_GET['login'];
 $pass=$_GET['pass'];
 $tip=$_GET['tip'];
 $jns=$_GET['jns'];
 $money="0.0";
 $number=$_GET['number'];
 $apikey=generateRandomString(20);
 
 $sql = "INSERT INTO `users` ( `fio`,`login`, `tip`,`jns`,`money`,`number`,`apikey`,`pass`) VALUES ( '$fio','$login',  '$tip', '$jns', '$money', '$number', '$apikey','$pass');";

if ($conn->query($sql) === TRUE) {
  echo "Hasaba alyndy";
} else {
  echo "Näsazlyk ýüze çykdy: ";
}


 ?>