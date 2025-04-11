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
- api/rewards/all
- api/rewards/customer/{customerId}
-
- 





##  How to Run

1. **Clone this repo**
   ```bash
   git clone https://github.com/Ravish77/customers-rewards-api.git
   cd customer-rewards-api

