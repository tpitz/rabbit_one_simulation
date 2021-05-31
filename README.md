### Import rabbit configuration
Use the file rabbit_queue.json to import the required configuration.
The part below has to be changed to match your needs
```
"users": [
    {
      "name": "your_user",
      "password_hash": "your_hash_password",
      "hashing_algorithm": "rabbit_password_hashing_sha256",
      "tags": ""
    }
  ]
```

### For local use
```
mvn clean gatling:test -Denv=local -Dduration=1200 -Dtps=300
```