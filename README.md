# spring

### cURL queries for /users

#### 1) Add user 
##### Query
curl --location --request PUT 'http://localhost:8080/users/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name" : "your username here",
    "age": "your userage here"
}'

##### Example 
curl --location --request PUT 'http://localhost:8080/users/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name" : "mike",
    "age": "17"
}'

#### 2) Delete user by id 
##### Query
curl --location --request DELETE 'http://localhost:8080/users/your_userid_here' \
--data-raw ''

##### Example 
curl --location --request DELETE 'http://localhost:8080/users/0' \
--data-raw ''

#### 3) Get user by id 
##### Query
curl --location --request GET 'http://localhost:8080/users/your_userid_here' \
--data-raw ''

##### Example 
curl --location --request GET 'http://localhost:8080/users/0' \
--data-raw ''

#### 4) Get all users 
##### Query
curl --location --request GET 'http://localhost:8080/users/' \
--data-raw ''

##### Example 
curl --location --request GET 'http://localhost:8080/users/' \
--data-raw ''

#### 5) Change user age by user id 
##### Query
curl --location --request PUT 'http://localhost:8080/users/your_userid_here/your_userage_here' \
--data-raw ''

##### Example
curl --location --request PUT 'http://localhost:8080/users/0/21' \
--data-raw ''
 
