

<?php 
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    // Получение данных из формы
    $username = trim($_POST['username']);
    $password = trim($_POST['password']);

    
    $servername = "localhost";
    $db_username = "root"; // Замените на ваше имя пользователя базы данных
    $db_password = ""; // Замените на ваш пароль базы данных
    $dbname = "etxt";

    
    $conn = new mysqli($servername, $db_username, $db_password, $dbname);

    // Проверка соединения
    if ($conn->connect_error) {
        die("Ошибка подключения: " . $conn->connect_error);
    }


    $sql = "SELECT * FROM users WHERE login ='".$username."' and pass='".$password."'"; 

        $result = $conn->query($sql);

        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                echo  $row['apikey'];
            }
        }else {
            header("Location: index.php?error=Ulanyjy ady ýa-da açar sözi ýalňyş");
            exit();
        }

}
else
{
            header("Location: index.php?error=Howp");
            exit();
        }
 ?>