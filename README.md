# 207-Final-Project

# Description: 
The problem domain we want to focus on is media managing. We want to develop an application that allows users to
store/save images, music, and other media. Our application will also allow the user to organize what they have stored
and create groups of media, adding new media from the web through an API.

# Link to API: https://apilayer.com/marketplace/image_upload-api

# Screenshot of trying to use API https://gyazo.com/d850e78f7cbc6a5f579cf0e9eb94ee91

Here is the code that we generated from API Layer after obtaining a token for this API.
## Example of Java Code ##

import requests

url = "https://api.apilayer.com/image_upload/url?url=https%3A%2F%2Fwww.google.com%2Furl%3Fsa%3Di%26url%3Dhttps%253A%252F%252Fwww.goal.com%252Fen%252Fnews%252Fwhy-did-cristiano-ronaldo-leave-real-madrid-for-juventus%252F1xzscq6gfxb8n15976p2yuyi8j%26psig%3DAOvVaw05dklKF5_9-ANjSyAA8SQy%26ust%3D1696290513167000%26source%3Dimages%26cd%3Dvfe%26opi%3D89978449%26ved%3D0CBEQjRxqFwoTCPCxtN2E1oEDFQAAAAAdAAAAABAE&delay=False"

payload = "{body}".encode("utf-8")
headers= {
"apikey": "LBBA6IIrFb2DVuwbdYYZuWNwYVft46nf"
}

response = requests.request("POST", url, headers=headers, data = payload)

status_code = response.status_code
result = response.text

####

The output of running this program is: {"error":{"code":"validation_error","message":"Request failed with validation error","context":{"delay":["The delay field must be true or false."],"callback":["The callback must be a valid URL."],"lost":["The lost field must be true or false."],"enhance":["The enhance field must be true or false."]}}}

This is because we are unable to satisfy all the requirements to generate the image. They ask for information that we are unsure
about, such as specification for an image delay in the optimization process or the ID_size. We inputted what we could but ultimately
we could not get the API call to work. We will likely switch APIs if these problems persist.


Some technical problems that we were experiencing are related to the website that we are accessing the API on.
They ask for information that we do not know such as specification of image delay in the optimization process or the ID_size.
It may be easier to switch APIs, especially since there are many options for our given problem domain.
