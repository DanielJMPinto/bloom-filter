FROM openjdk:8

# Set the working directory to /app
WORKDIR /bloom-filter/bin

# Copy the current directory contents into the container at /app
COPY . /bloom-filter

# Make port 80 available to the world outside this container
EXPOSE 80

# Define environment variable
ENV BLOOM_FILTER World

# Run app.py when the container launches
CMD ["java", "bloom/Main"]