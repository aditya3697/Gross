# Gross - A HTTP server built from scratch

### Referenes

**Mozilla docs** - https://developer.mozilla.org/en-US/docs/Web/HTTP



### Build and Start the server
1. **ant clean** - cleanup the previous build if any
2. **ant compile** - compile the entire java code
3. **ant jar** - creates two jar files, one for server and one for client
4. **ant run** - starts the http server
5. **ant run_client** - Executes the client program which can be used for testing
Note - **ant** should be installed

### Daemonize the server
Applicable only for Linux distributions. 
This is a good reference on how to daemonize a bash script - https://www.linode.com/docs/quick-answers/linux/start-service-at-boot/
Follow along using the script daemonize-gross.sh. Make sure to update the project base folder path in the bash script.