# tech-support

A simple API developed in Java. The client sends the server an HTTP request with details about a technical problem, and gets back a matching response. 
The requests that are sent between the client and the server are JSON strings, that are parsed using Google's Gson library.
The data (both the client's input and the server's response) is saved to a MySQL database containing two tables: FormTbl and FormResultTbl.

In addition to the API, there is a test folder, that contains JUnit tests of the API's logic.

