# addressbook
vaadin spring boot microservices app

app curently consists from 3 projects:
* addressbook-config (eureka configuration server, which register different services)
* addressbook-ui-service (vaadin user interface) and
* addressbook-service (jpa component which deals with data, providing crud)


## TODO list
- [ ] addressbook-ui-service needs code for calling addressbook-service over eureka server
- [ ] addressbook-ui-service authentication and authorization part, with possible new user services, with separate db schema
- [ ] addressbook-service needs caching for speed



