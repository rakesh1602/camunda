**Camunda BPMN Project - Demonstrating CRUD Operations with Spring Boot Application**


**This project showcases the implementation of CRUD operations using a Spring Boot application along with BPMN processes using Camunda BPMN. Follow the steps below to run and interact with the project:**

**Setting Up Database Configuration**
1. Update the database configuration in the application.yaml file.
   
**Running the Application**
1. Open the project in the Command Prompt (CMD) and execute the following commands:
    - mvn clean install
    - java -jar jar-name
2. Access the application by opening it in your preferred web browser.
3. Log in using the username and password specified in the application.yaml file located in the resource folder.
   
4. After logging in, click on "Cockpit" to view the number of processes and deployments that have been performed.
   <img width="959" alt="image" src="https://github.com/rakesh1602/camunda/assets/87637381/629cf2d4-cb90-4c7e-98e2-4afccd4b26dd">

5. Click on the TasklistClick on "Tasklist" to access the task list.

   <img width="960" alt="image" src="https://github.com/rakesh1602/camunda/assets/87637381/f333f4a1-39dc-45fa-a991-8132b35b8c8a">

6. Start a new process by clicking on "Start process."
  <img width="958" alt="image" src="https://github.com/rakesh1602/camunda/assets/87637381/6a592ebf-2a03-4fd7-b3a3-10d89cf0f7f1">


7. You will see a list of available processes. Choose the "Create Person" method and click on "Start process."
   - choose create person method
   - click on start process
   - <img width="938" alt="image" src="https://github.com/rakesh1602/camunda/assets/87637381/bffa7948-fbd6-4f1b-9f91-77f808bd7907">
   
8 .After clicking on the start button, refresh the page. You will see a user task that needs to be completed before the service task can run. Perform the following steps:
        - Claim the task.
        - Fill out the forms (these are the payloads required for our Spring Boot app's "Create Person" method).
        - Submit the form.
   - <img width="959" alt="image" src="https://github.com/rakesh1602/camunda/assets/87637381/d09a3017-31ce-4757-9eff-ead5da2a6938">


9.In the diagram section, you can see the progress of the process. The number "1" indicates that one task is pending at the user task. -
    <img width="671" alt="image" src="https://github.com/rakesh1602/camunda/assets/87637381/a328ac06-a42c-4abc-ac82-0ee1a7d3e54e">
   - <img width="959" alt="image" src="https://github.com/rakesh1602/camunda/assets/87637381/068395f1-2675-4c56-b689-6aa7e2d94d8f">

  10. Once you submit the form, the "Create Person" method in the Spring Boot app will be completed. This will also complete the process flow of the BPMN diagram.










