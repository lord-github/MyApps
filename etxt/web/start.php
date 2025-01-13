<?php
// Текущая дата
$currentDate = date("Y-m-d");

// Начало недели (понедельник)
$startOfWeek = date("Y-m-d", strtotime("monday this week", strtotime($currentDate)));

// Конец недели (воскресенье)
$endOfWeek = date("Y-m-d", strtotime("sunday this week", strtotime($currentDate)));

// Вывод результата
echo "Начало недели: " . $startOfWeek . "\n";
echo "Конец недели: " . $endOfWeek . "\n";
?>