In Questo progetto di fa uso di un Database Postgres in un container Docker, prima di avviare il progetto SpringBoot settare il database con docker seguire la guida sottostante.

###### Docker Database Postgres

- Creara un container Docker di nome "ContainerPostgress", esso conterrà il database "mydb" , startare il container e controllare se il database è disponibile:

```
// Prendo l'immagine docker di postgres:
docker pull postgres

//in questo modo posso creare il container sotto l'immagine di postgres:
docker run --name ContainerPostgress -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres

// con questo comando entro nel terminale psql così da poter creare il mio database:
docker exec -it ContainerPostgress psql -U postgres

//Una volta dernto il terminale psql crea il database col comando:
CREATE DATABASE mydb

//in questo modo controllo i logs, posso quindi controllare se il
  database è attivo e disponibile all'utilizzo:
docker logs ContainerPostgress
```

in questo modo il database è attivo e disponibile come da configuarazione:

alla porta: 5432 

nome: ContainerPostgress 

password: password 

e username non specificato nell'instanziazione ma di default: postgres.

