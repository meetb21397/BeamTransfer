# BeamTransfer

This service transfers files from one bridgehead to another bridgehead.

It serves for both sides of the connection. If we transfer the files from bridgehead-1 to
bridgehead-2:

- Configuration of bridgehead-1: TARGET_BRIDGEHEAD_URL and TARGET_BRIDEHEAD_APIKEY of bridgehead-2


- Configuration of bridgehead-2: TRANSFER_FILES_CRON_EXPR=-
  With this configuration, no files will be transferred to any additional bridgehead.

Of course, TARGET_BRIDGEHEAD_URL and TARGET_BRIDGEHEAD_APIKEY don't need to be specified.


## Building
mvn clean package


## Environment configuration example
bridgehead-1:

TRANSFER_FILES_DIRECTORY=.\files-bk1  
CLIENT_API_KEY=abc123  
APPLICATION_PORT=8181  
TARGET_BRIDGEHEAD_URL=http://localhost:8282/transfer  
TARGET_BRIDGEHEAD_APIKEY=def456

bridgehead-2:

TRANSFER_FILES_DIRECTORY=.\files-bk2  
TRANSFER_FILES_CRON_EXPR=-  
CLIENT_API_KEY=def456  
APPLICATION_PORT=8282  
