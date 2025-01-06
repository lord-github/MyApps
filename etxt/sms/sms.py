import serial
import time
import argparse

parser = argparse.ArgumentParser(description="Telefon belgi  we  tekst.")
# Добавляем аргументы
parser.add_argument(
    "phone_number", 
    type=str, 
    help="belgi: +99364775969"
)
parser.add_argument(
    "second_arg", 
    type=str, 
    help="Tekst ya-da kod:"
)
args = parser.parse_args()
recipient_phone_number= args.phone_number
sms_message = args.second_arg

sms_message = sms_message.replace('"','')

def send_sms(port, baudrate, phone_number, message):
    try:
        gsm_modem = serial.Serial(port, baudrate, timeout=5)
        time.sleep(1)  # Allow the modem to initialize
        
        gsm_modem.write(b'AT\r')
        response = gsm_modem.read(64)
        if b"OK" not in response:
            print("Failed to communicate with the GSM modem.")
            return
        
        print("Modem communication established.")

        gsm_modem.write(b'AT+CMGF=1\r')
        time.sleep(1)
        response = gsm_modem.read(64)
        if b"OK" not in response:
            print("Failed to set SMS text mode.")
            return

        gsm_modem.write(f'AT+CMGS="{phone_number}"\r'.encode())
        time.sleep(1)

        gsm_modem.write(message.encode() + b"\x1A")
        time.sleep(5)  # Allow time for the SMS to send
        response = gsm_modem.read(64)

        if b"OK" in response:
            print("SMS sent successfully!")
        else:
            print("Failed to send SMS.")

    except Exception as e:
        print(f"An error occurred: {e}")

    finally:
        # Close the serial connection
        gsm_modem.close()
 
modem_port = "COM7"
modem_baudrate = 9600

send_sms(modem_port, modem_baudrate, recipient_phone_number, sms_message)
