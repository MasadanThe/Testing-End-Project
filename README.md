# Testing-End-Project
READ THE FILE RAW
The way of authetication is just know you username and sometimes id.
Not secure but it works.
There are classes for authenticate that is not used but could easily be activated.

Must have:
Ubuntu 22 installed somewhere, I used a VM for this.

Jenkins:
  First pipline:
    Connect pipeline to repository.
    Execute commands:
      ./mvnw compile
      ./mvnw package
    Save junit test report to:
      target/surefire-reports/*.xml
    Archive the artifact to:
      target/*.jar
  Second pipeline:
    Connect pipeline to repository.
    Build project automaticallt if the first pipeline was stable.
    Use exec command over SSH to your Ubuntu(This case VM) server:
      cd /home/user/Testing-End-Project
      git pull origin testing
      echo "Compile and package"
      cd /home/user/Testing-End-Project
      sh ./mvnw compile
      sh ./mvnw package
      cd target
      java -jar Testing-End-Project-0.0.1-SNAPSHOT.jar

  Optionally you can have a third pipeline that merge if the second is stable.

  JMeter:
  Use JMeter for a stress test of 1000 users all at the same time.
  For me it could /get_routes with 2 routes in database in 800ms
