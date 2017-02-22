api_key=AAAAOCKHki4:APA91bFXoOqRcsNq73gMnY1Ca7kAntmi8IvWeYKbjzw_-Um325rPZ1vI0zT9j2XAXadUpf3fDdTkBxIEEj1vFvIpLOuvXugtHPCShui5nFzziZWMT8YuTfUjR53Ek_jRlG0fKKOJMFlK


curl --header "Authorization: key=$api_key" \
       --header Content-Type:"application/json" \
       https://fcm.googleapis.com/fcm/send \
       -d "{ \"to\" : \"bk3RNwTe3H0:CI2k_HHwgIpoDKCIZvvDMExUdFQ3P1...\" }" >> result

exit 0
