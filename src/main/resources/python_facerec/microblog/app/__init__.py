from flask import Flask, request, face_recognition, pybase64
import face_recognition
from PIL import Image
import io

app = Flask(__name__)

@app.route('/')
def hello_world():
   return 'Hello World!'

@app.route('/face',methods = ['POST', 'GET'])
def face():
   if request.method == 'POST':
      databaseImage = request.form['database']
      loginImage = request.form['login']

      data = pybase64.b64decode(databaseImage)
      filename = 'database.jpg'
      with open(filename, 'wb') as f:
      	f.write(data)

      login = pybase64.b64decode(loginImage)
      filename2 = 'login.jpg'
      with open(filename2, 'wb') as f:
      	f.write(login)
      
      

      # load your image
      image_to_be_matched = face_recognition.load_image_file(filename)

      # encoded the loaded image into a feature vector
      # Minu listud kontroll, kui pildilt ei leita nägu.
      if len(face_recognition.face_encodings(image_to_be_matched)) == 0:
      	return 'False'

      image_to_be_matched_encoded = face_recognition.face_encodings(image_to_be_matched)[0]
      current_image = face_recognition.load_image_file(filename2)

      # encode the loaded image into a feature vector
      if len(face_recognition.face_encodings(current_image)) == 0:
      	return 'False'
      current_image_encoded = face_recognition.face_encodings(current_image)[0]

      # match your image with the image and check if it matches
      result = face_recognition.compare_faces([image_to_be_matched_encoded], current_image_encoded)

      if result[0] == True:
      	print("Matched: ")
      	return 'True'
      else:
      	print("Not matched: ")
      	return 'False'
   else:
   	return 'Get'

if __name__ == '__main__':
   app.run()