# **Take Home API Test**

### Must have before start
1. Github account
2. Docker

### Steps
1. Pull the docker image containing the api
`docker pull automaticbytes/demo-app`
2. Run the image `docker run -p 3100:3100 automaticbytes/demo-app`
3. Verify the api is up and set it as the base url for the tests `http://localhost:3100/api`
4. Create in your personal github a public repository (name it for instance home-test-api)
5. Code requested exercises, commit and push your code and send the repository link according to the instructions given by the recruiter who contacted you.
6. Forking this repository is not needed.

### Test Scenarios

1. Get all menu items
    * Using: GET /api/inventory
    * Validate that the response contains at least 9 items
    * Validate that all the items in the response contains the following data: "id","name","price","image"

2. Filter by id 
    * Using: GET /api/inventory/filter?id=3
    * Validate that the response contains the correct id,name,price and image for "Baked Rolls x 8"

3. Add item for non existent id
    * Using POST /api/inventory/add with following body: `{"id": "10","name": "Hawaiian","image": "hawaiian.png","price": "$14"}`
    * Validate response status code 200
    
4. Add item for existent id
    * Using POST /api/inventory/add with following body: `{"id": "10","name": "Hawaiian","image": "hawaiian.png","price": "$14"}`
    * Validate response status code 400

5. Try to add item with missing information
    * Using POST /api/inventory/add with some of the data missing from the json body (for instance without id)
    * Validate response status code 400
    * Validate response contains the message "Not all requirements are met"
  
6. Validate recent added item is present in the inventory
    * Using: GET /api/inventory
    * Validate that the item added in the step 3 is present and with the correct data
	
	
	
### Folder Structure of the Project ###

APiTesting/
│
├── pom.xml                                                    # Maven build file (all dependencies are declared there)
│
├── src/
│   ├── main/
│   │   ├── java/                                              # Main application code (Empty in our test automation projects)
│   │   │
│   │   └── resources/                                         # Application config (Empty in our test automation projects)
│   │
│   └── test/
│       ├── java/
│       │   └── com/
│       │       ├── automation.api.actions/                    # All the HTTP and Validation Operation type declare here 
│       │       ├── automation.api.constants/                  # We can put the constants variable here e.g baseURI(http://localhost:3100/api)  
│       │       ├── automation.api.interfaces/                 # Inside this we have IApi interface which contains declartion of various API operation e.g setBody(),setHeader(),setFormParam() etc
│       │       ├── automation.api.restassuredfunctions/       # Inside this we have API class which contains defination of various API operation declared in IApi interface
│		│		├── automation.api.utilities/                  # Utilities (e.g. FileReader, Helper , JsonBuilder etc)
│		│		├── runner/                                    # We have a Runner class TestRunner.java from where we can trigger the execution.
│		│	    └── stepDef/                                   # Inside this packgae we have step defination of the feature file or glue code.
│       │                            
│       │
│       └── resources/            
│           └── features/                                      # Inside this packgae we have our test case written in BDD(Given,When,Then format)
│
├── config/                                                    #configuration file.              
│
└── target/                                                    # Reports will be generated here



### How to Run this project ###


1)Each feature file has separate unique tag (e.g, GetInventory.feature file has @getInventoryDetails tag) Now in TestRunner.java file we can put the specific tag which test case we want to run and also we can run all the feature file using @Regression tag as all the feature has common @Regression tag.
	
2)If we want to run using terminal follow the below step
      i) Right click on project 
      ii)Click on Show in Local Terminal (Terminal will open)	  
	  iii) mvn clean install -Dcucumber.filter.tags="@Regresion" (If wants to run all the scenario)
	       mvn clean install -Dcucumber.filter.tags="@getInventoryDetails" (If wants to run any specific scenario like Get Inventory Details)




      
  
   
   
