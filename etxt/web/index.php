
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Ulgama girmek</title>
    <style>
        body {
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #691010, #062151);
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .login-container {
            background: rgba(255, 255, 255, 0.9);
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 8px 16px rgba(0,0,0,0.3);
            width: 300px;
            text-align: center;
        }
        .login-container h2 {
            margin-bottom: 20px;
            color: #333;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 8px;
        }
        .login-container button {
            width: 95%;
            padding: 10px;
            margin: 10px 0;
            border: none;
            border-radius: 8px;
            background-color: #48f;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }
        .login-container button:hover {
            background-color: #37d;
        }
        .login-container a {
            display: block;
            margin-top: 10px;
            color: #48f;
            text-decoration: none;
        }
        .login-container a:hover {
            text-decoration: underline;
        }
        .error {
            color: red;
            margin-bottom: 10px;
        }
         .download-btn {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #007BFF;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .download-btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h2>SMM login</h2>
        <?php
        if(isset($_GET['error'])) {
            echo '<div class="error">Ulanyjy ady ýa-da açar sözi ýalňyş</div>';
        }
        ?>
        <form action="mykey.php" method="POST">
            <input type="text" name="username" placeholder="Ulanyjy ady" required>
            <input type="password" name="password" placeholder="Açar sözi" required>
            <button type="submit">APIKEY görmek</button>
        </form>
    </div>
    
    <p></p>
    <p>Ýüklemek üçin düwmä basyň:</p>

    
    <a href="files/smm.exe" download="smm.exe" class="download-btn">23 mb</a>

</body>
</html>
