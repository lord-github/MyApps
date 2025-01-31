import socket
import pyperclip
import os
import threading
from pynput.mouse import Controller as MouseController
from pynput.keyboard import Controller as KeyboardController

HOST = "0.0.0.0"
PORT = 9999

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((HOST, PORT))
server.listen(1)

print(f"[+] Serwer işe goýebrildi {HOST}:{PORT}")

conn, addr = server.accept()
print(f"[+] Kabul edildi: {addr}")

mouse = MouseController()
keyboard = KeyboardController()

def receive_file():
    """Klientdan kabul etmek"""
    file_info = conn.recv(1024).decode()
    filename, filesize = file_info.split("|")
    filename = os.path.basename(filename)
    filesize = int(filesize)

    with open(filename, "wb") as f:
        received = 0
        while received < filesize:
            data = conn.recv(1024)
            received += len(data)
            f.write(data)

    print(f"[+] Faýl {filename} kabul edildi!")

while True:
    try:
        command = conn.recv(1024).decode()
        if not command:
            break

        if command.startswith("mouse_move"):
            _, x, y = command.split()
            mouse.position = (int(x), int(y))

        elif command.startswith("mouse_click"):
            mouse.click(MouseController().left)

        elif command.startswith("keyboard_type"):
            _, text = command.split(" ", 1)
            keyboard.type(text)

        elif command == "clipboard_get":
            conn.send(pyperclip.paste().encode())

        elif command.startswith("clipboard_set"):
            _, text = command.split(" ", 1)
            pyperclip.copy(text)

        elif command.startswith("send_file"):
            receive_file()

    except Exception as e:
        conn.send(f"Ошибка: {str(e)}\n".encode())

conn.close()
server.close()
