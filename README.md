# 207-Final-Project

# Description: 
The problem domain we want to focus on is media managing. We want to develop an application that allows users to
store/save images, music, and other media. Our application will also allow the user to organize what they have stored
and create groups of media, adding new media from the web through an API.

# Link to API: https://apilayer.com/marketplace/image_upload-api

# Screenshot of trying to use API https://gyazo.com/d850e78f7cbc6a5f579cf0e9eb94ee91

Here is the code that we generated from API Layer after obtaining a token for this API.

## Example of Java Code ##

Our example code is in testfile.java

####

The output of running this program is:

{
"height": 408,
"image_url": "https://core-production-us-east-1-imageupload.s3.amazonaws.com/363818_8511394c5e3f9ef66f3be96bd661d0/6a2db29a_wild-grass-in-the-mountains-at-sunset.jpg%3Fs%3D612x612%26w%3D0%26k%3D20%26c%3D6mItwwFFGqKNKEAzv0mv6TaxhLN3zSE43bWmFN--J5w%3D?response-content-disposition=attachment%3B%20filename%3Dwild-grass-in-the-mountains-at-sunset.jpg%3Fs%3D612x612%26w%3D0%26k%3D20%26c%3D6mItwwFFGqKNKEAzv0mv6TaxhLN3zSE43bWmFN--J5w%3D&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAXYTEMCMCBKRUSNCS%2F20231002%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20231002T002350Z&X-Amz-SignedHeaders=host&X-Amz-Expires=86400&X-Amz-Signature=61119cc3c3ace0646085b6d41044aa2f6598255db33c5c27670173c15e72e9d5",
"name": "wild-grass-in-the-mountains-at-sunset.jpg?s=612x612&w=0&k=20&c=6mItwwFFGqKNKEAzv0mv6TaxhLN3zSE43bWmFN--J5w=",
"new_height": 408,
"new_width": 612,
"optimized_size": 20055,
"saved_bytes": 3902,
"size": 23957,
"success": true,
"width": 612
}



Some technical problems that we were experiencing are related to the website that we are accessing the API on.
They sometimes ask for information that we do not know such as specification of image delay in the optimization process 
or the ID_size, for certain images.
It may be easier to switch APIs, especially since there are many options for our given problem domain.
