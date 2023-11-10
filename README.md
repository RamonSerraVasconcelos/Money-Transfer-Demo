# Money Transfer Demo

This repository houses the source code for a small-scale transfer service that I developed to enhance my expertise in backend development and tools such as Docker and Docker Compose.

## How to run the service

-   Have Docker and Docker compose installed on your system
-   Clone the repository
-   Enter the **misc** folder and run the command:
```
docker compose up --build
```

### About the transfer service:

-   You can create users

-   From users you can create bank accounts with or without a cnpj

-   Accounts with cnpj cannot transfer money to other accounts, just receive it

-   You can deposit money to an account

### Payload examples

### User

- Create User: POST /users
```json
{
  "name": "Jon Doe",
  "email": "jon@email.com",
  "cpf": "73755890038",
  "password": "123345678",
  "confirmationPassword": "123345678"
}
```
- Get all Users: GET /users

### Account

- Create Account: POST /accounts
```json
{
  "userId": "2e3ac2b7-c0d5-411c-ab1f-ccf87d061811",
  "cnpj": "94943821000105" optional field
}
```

- Deposit Money: POST /accounts/deposit
```json
{
  "agency": 8476,
  "accountNumber": 62176,
  "amount": 100
}
```

- Transfer Money: POST /accounts/transfer
```json
{
  "payerId": "02ecf945-ab89-456f-9e58-d54246da85ce",
  "agency": 8476,
  "accountNumber": 62176,
  "amount": 100
}
```

Please be aware that you should save the account numbers upon creation for future use