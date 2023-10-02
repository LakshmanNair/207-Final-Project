# 207-Final-Project

A description of the problem domain your team is tentatively wanting to focus on in the project. (e.g., trivia, finance, real estate, etc…)
a brief, high-level description of what kind of application your team is thinking of developing.
This is mostly just so we can give you some initial feedback on whether your team might want to reconsider your project direction.
a link to the documentation for an API your team can use related to the domain.




a screenshot of using a tool to try out the API (like https://www.postman.com/Links to an external site. or https://hoppscotch.io/Links to an external site.Links to an external site.)
example output of running your Java code (see below).


a list of any technical problems blocking progress (if any)
please don't hesitate to ask such questions on Piazza too of course.
Java code:


## Java Code ##

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