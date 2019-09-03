from app import app

@app.route('/')
def sm():
    return "World!"
@app.route('/index')
def index():
	if request.method == 'POST':
		return("Hey")
	