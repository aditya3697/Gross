cd /home/aditya/github/Gross

# clean up existing jars
ant clean

# comiple the code
ant compile

# Create the jars
ant jar

# Start the Http process
ant run