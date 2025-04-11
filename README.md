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

- Java 8+
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

2. ✅ Get rewards for a specific customer
Endpoint: GET /api/rewards/customer/{customerId}
Description: Returns reward summary for the provided customer ID.
Example URL:
http://localhost:8080/api/rewards/customer/C001
Sample Response: See docs/sample-responses.json → "customer_C001"

3. Get rewards for a customer for a specific month
Endpoint: GET /api/rewards/customer/{customerId}/month?month={month}
Description: Returns rewards earned by a customer in a particular month.
Example URL:
http://localhost:8080/api/rewards/customer/C002/month?month=2
Sample Response: See docs/sample-responses.json → "customer_C002_February"

4. Get rewards for a customer between a date range
Endpoint: GET /api/rewards/customer/{customerId}/range?startDate={start}&endDate={end}
Description: Returns rewards within a custom date range for the customer.
Example URL:
http://localhost:8080/api/rewards/customer/C003/range?startDate=2023-02-01&endDate=2023-03-31
Sample Response: See docs/sample-responses.json → "customer_C003_range"





##  How to Run

1. **Clone this repo**
   
   git clone https://github.com/Ravish77/customers-rewards-api.git
   cd customer-rewards-api

