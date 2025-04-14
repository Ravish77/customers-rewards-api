# customers-rewards-api
API to calculate customer reward points

# 🏆 Customer Rewards API

A Spring Boot REST API that calculates monthly and total reward points for customers based on their purchase transactions.

---

##  Reward Calculation Rules

For each transaction:
-  2 points for every dollar spent **over $100**
-  1 point for every dollar spent **between $50 and $100**

### Example:
A transaction of **$120** earns:
- 2 x ($120 - $100) = 40 points
- 1 x ($100 - $50) = 50 points  
**Total: 90 points**

---

##  Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- MySQL Database
- Maven
- REST API
- Postman (for testing)

---

##  Endpoints and their usage and responses
1. Get rewards for all customers
Endpoint: GET /api/rewards/all
Description: Returns total and monthly rewards for all customers.
Example URL:
http://localhost:8080/api/rewards/all
Sample Response: See docs/sample-responses.json → "allCustomers"

2. Get rewards for a specific customer
Endpoint: GET /api/rewards/customer/{customerId}
Description: Returns reward summary for the provided customer ID.
Example URL:
http://localhost:8080/api/rewards/customer/C001
Sample Response: See docs/sample-responses.json → "customer_C001"

3. Get monthly rewards for a specific customer
Endpoint: GET /api/rewards/customer/{customerId}/rewards?month=YYYY-MM
Description: Returns rewards for the specified customer in the given month.
Example URL:
http://localhost:8080/api/rewards/customer/C002/rewards?month=2023-02
Sample Response: See docs/sample-responses.json → "monthlyRewards_C002_2023-02"

4. Get rewards for a specific customer within a date range
Endpoint: GET /api/rewards/customer/{customerId}/rewards?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD
Description: Returns rewards for the specified customer within the provided date range.
Example URL:
http://localhost:8080/api/rewards/customer/C003/rewards?startDate=2023-02-01&endDate=2023-03-31
Sample Response: See docs/sample-responses.json → "rewardsRange_C003_2023-02-01_to_2023-03-31"





##  How to Run

1. **Clone this repo**
   
   git clone https://github.com/Ravish77/customers-rewards-api.git
   cd customer-rewards-api

