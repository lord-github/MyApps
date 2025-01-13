<?php
$imageName = isset($_GET['image']) ? $_GET['image'] : null;

// Проверяем, что файл существует
$imagePath = 'uploads/' . $imageName; // Папка, где хранятся изображения
if ($imageName === null || !file_exists($imagePath)) {
    die('Maglumat ýok...');
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Просмотр изображения</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f0f0;
        }
        img {
            max-width: 90%;
            max-height: 90%;
            transition: transform 0.3s ease;
            cursor: zoom-in;
        }
        img.zoomed {
            transform: scale(2);
            cursor: zoom-out;
        }
    </style>
</head>
<body>
    <!-- Отображение изображения -->
    <img id="image" src="<?php echo htmlspecialchars($imagePath); ?>" alt="Изображение">

    <script>
        // Добавляем функциональность увеличения
        const img = document.getElementById('image');
        img.addEventListener('click', () => {
            if (img.classList.contains('zoomed')) {
                img.classList.remove('zoomed');
            } else {
                img.classList.add('zoomed');
            }
        });
    </script>
</body>
</html>
