# DriverAPi
This is a sample rest api developed using spring boot framework used to handle create and read driver details.

## Prerequisites
* JDK 1.8
* Gradle
* Rest endpoint tester such as *Postman* or *curl* (optional)


## How to run the API

`This app runs on spring boot default port i.e. 8080. The root endpoint for the api is :` [http://localhost:8080/](http://localhost:8080/)

The app is bundled with gradle build script. There are multiple gradle tasks added to the build script. The tasks are defined as below:
* To build the application : ``` <command for build> ```
* To run all unit and integration tests : ``` <command for test> ```
* To run application : ``` <command for run> ```


## API endpoints and description

`Throughout the appication - the date format is used as ISO date format i.e.` **yyyy-MM-dd** `. Please use the same foramt for all Date inputs whereever it is applicable`


### Open Api - Swagger 

`This api is annotated with Open Api specifications using swagger. To get more information on what are the endpoints, descriptions and how to use examples please use swagger-ui endpoint:` [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

`<TBD ADD image>`


1. **Create Driver**

    This endpoint is used to create and store new driver details. The date format for `date_of_birth` should be in **yyyy-MM-dd** format. The details of the endpoint is as below : 

    Item | Detils
    -----|-------
    **URL** | /driver/create 
    **HTTP method** | **POST**
    **POST body** | ```{ "firstname": "John", "lastname": "Smith","date_of_birth": "1980-05-01" }```
    **Response Status** | 202 : Successfully created 

    `<TBD ADD image>`


2. **Get all drivers**
    This endpoint will return a list of all existing drivers.

    Item | Detils
    -----|-------
    **URL** | /drivers 
    **HTTP method** | **GET**
    
`<TBD ADD image>`

3. **Get Drivers by date**
    This endpoint will returns a list of all drivers created after the specified date. The should be passed through request paramters in **yyyy-MM-dd** format.
    
    Item | Detils
    -----|-------
    **URL** | /drivers/byDate
    **HTTP method** | **GET**
    **Request Params**| Name : `date` Format : `yyyy-MM-dd`
