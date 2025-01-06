<?php
$targetDir = "uploads/";

foreach ($_FILES as $file) {
    $targetFile = $targetDir . basename($file["name"]);
    if (move_uploaded_file($file["tmp_name"], $targetFile)) {
        echo "The file " . htmlspecialchars($file["name"]) . " has been uploaded.\n";
    } else {
        echo "Error uploading " . htmlspecialchars($file["name"]) . ".\n";
    }
}
?>