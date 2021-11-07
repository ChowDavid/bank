# Bank Small Project

## Framework
- Spring boot
- Lombok
- Spring data
- Spring rest
- Swagger API 3
- H2
- JUnit 5
- SpringBootTest

## Requirement
- Java 8
- jar
- 

## URL
- http://localhost:8080/swagger-ui/index.html
- http://localhost:8080/bank/account/list?userName=DavidChow
- http://localhost:8080/bank/account/transaction?accountNumber=123-2223-212
- http://localhost:8080/h2-console

## Account List Request
```aidl
localhost:8080/bank/account/list?userName=DavidChow
```

## Account List Response
```aidl
[
    {
        "accountNumber": "123-2223-212",
        "accountName": "SGSaving726",
        "accountType": "Savings",
        "balanceDate": "2021-11-07",
        "currency": "SGD",
        "balance": 8.00
    },
    {
        "accountNumber": "123-2223-213",
        "accountName": "AUSaving726",
        "accountType": "Savings",
        "balanceDate": "2021-11-07",
        "currency": "AUD",
        "balance": 8.00
    }
]
```
## Account List fail response
```aidl
{
    "error": "USER_NOT_FOUND",
    "time": "2021-11-07T22:44:28.223893",
    "message": "123-2223-212 not found"
}
```

## Account Transaction Request
```aidl
localhost:8080/bank/account/transaction?accountNumber=123-2223-212
```

## Account Transaction Response
```aidl
[
    {
        "accountNumber": "123-2223-212",
        "accountName": "SGSaving726",
        "valueDate": "2021-01-01T00:00:00",
        "currency": "SGD",
        "debitAmount": 10.00,
        "creditAmount": null,
        "creditDebit": "DEBIT",
        "transactionNarrative": null
    },
    {
        "accountNumber": "123-2223-212",
        "accountName": "SGSaving726",
        "valueDate": "2021-01-01T01:00:00",
        "currency": "SGD",
        "debitAmount": null,
        "creditAmount": 1.00,
        "creditDebit": "CREDIT",
        "transactionNarrative": null
    },
    {
        "accountNumber": "123-2223-212",
        "accountName": "SGSaving726",
        "valueDate": "2021-11-01T01:00:00",
        "currency": "SGD",
        "debitAmount": null,
        "creditAmount": 1.00,
        "creditDebit": "CREDIT",
        "transactionNarrative": null
    }
]
```
## Account Transaction Fail Response
```aidl
{
    "error": "ACCOUNT_NOT_FOUND",
    "time": "2021-11-07T22:45:19.001553",
    "message": "123-2223-214 not found"
}
```