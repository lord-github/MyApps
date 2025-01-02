using System;
using System.Diagnostics;
using System.Windows.Forms;

namespace OpenWordFileApp
{
    public class MainForm : Form
    {
        private Button openWordButton;

        public MainForm()
        {
            // Создание кнопки
            openWordButton = new Button();
            openWordButton.Text = "Открыть Word файл";
            openWordButton.Width = 150;
            openWordButton.Height = 50;
            openWordButton.Top = 50;
            openWordButton.Left = 50;

            // Привязка обработчика события нажатия на кнопку
            openWordButton.Click += OpenWordButton_Click;

            // Добавление кнопки на форму
            Controls.Add(openWordButton);

            // Настройка формы
            Text = "Открытие Word файла";
            Width = 300;
            Height = 200;
            StartPosition = FormStartPosition.CenterScreen;
        }

        private void OpenWordButton_Click(object sender, EventArgs e)
        {
            string filePath = @"c:\hello.docx";

            try
            {
                // Запуск файла Word с помощью Process
                Process.Start(new ProcessStartInfo
                {
                    FileName = filePath,
                    UseShellExecute = true
                });
            }
            catch (Exception ex)
            {
                // Вывод сообщения об ошибке, если файл не найден или не открывается
                MessageBox.Show($"Ошибка при открытии файла: {ex.Message}", "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.Run(new MainForm());
        }
    }
}
