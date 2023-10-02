# 207-Final-Project


# Description: The problem domain we want to focus on is media managing. We want to develop an application that allows users to
store/save images, music, and other media. Our application will also allow the user to organize what they have stored
and create groups of media, adding new media from the web through an API.

# Link to API: https://apilayer.com/marketplace/image_upload-api

# Screenshot of trying to use API https://gyazo.com/d850e78f7cbc6a5f579cf0e9eb94ee91

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

This is because we are unable to satisfy all the requirements they provide us with when generating the image.