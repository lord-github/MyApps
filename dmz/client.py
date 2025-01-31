import socket
import pyperclip
import os
import tkinter as tk
from tkinter import filedialog, messagebox

SERVER_IP = "127.0.0.1"
PORT = 9999

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect((SERVER_IP, PORT))

def send_command(command):
    """Serwere buýruk ugratmak"""
    client.send(command.encode())

def move_cursor():
    """Kursory üýtgetmek"""
    x, y = entry_x.get(), entry_y.get()
    if x.isdigit() and y.isdigit():
        send_command(f"mouse_move {x} {y}")

def click_mouse():
    """Syçanjyk basmak"""
    send_command("mouse_click")

def type_text():
    """Tekst ýazmak"""
    text = entry_text.get()
    if text:
        send_command(f"keyboard_type {text}")

def get_clipboard():
    """Alyş-çalyş bufferi"""
    send_command("clipboard_get")
    response = client.recv(1024).decode()
    messagebox.showinfo("Alyş-çalyş bufferi", response)

def set_clipboard():
    """Buffer"""
    text = entry_clipboard.get()
    send_command(f"clipboard_set {text}")

def send_file():
    """Faýl ugratmak"""
    filepath = filedialog.askopenfilename()
    if not filepath:
        return

    filename = os.path.basename(filepath)
    filesize = os.path.getsize(filepath)

    send_command(f"send_file|{filename}|{filesize}")
    with open(filepath, "rb") as f:
        while (chunk := f.read(1024)):
            client.send(chunk)

    messagebox.showinfo("Файл", f"Файл {filename} ugradyldy!")

def exit_program():
    """Закрыть клиент"""
    send_command("exit")
    client.close()
    root.quit()

# === GUI ===
root = tk.Tk()
root.title("Uzak aralykdan dolandyryş")
root.geometry("600x600")

# Движение мыши
tk.Label(root, text="X:").pack()
entry_x = tk.Entry(root)
entry_x.pack()

tk.Label(root, text="Y:").pack()
entry_y = tk.Entry(root)
entry_y.pack()

tk.Button(root, text="Kursoryň ýerini üýtgetmek", command=move_cursor).pack()

# Клик мыши
tk.Button(root, text="Syçanjyk basmak", command=click_mouse).pack()

# Ввод текста
tk.Label(root, text="Tekst girizmek:").pack()
entry_text = tk.Entry(root)
entry_text.pack()

tk.Button(root, text="Ýazmak", command=type_text).pack()

# Буфер обмена
tk.Label(root, text="Alyş-çalyş bufferi:").pack()
entry_clipboard = tk.Entry(root)
entry_clipboard.pack()

tk.Button(root, text="Bufferi kabul etmek", command=get_clipboard).pack()
tk.Button(root, text="Bufferi saýlamak", command=set_clipboard).pack()

# Передача файлов
tk.Button(root, text="Faýl ugratmak", command=send_file).pack()

# Выход
tk.Button(root, text="Çykmak", command=exit_program).pack()

root.mainloop()
