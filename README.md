# Progetto-OOP A.A. 2020/2021
L'applicazione sviluppata è una Rest Api che, prendendo dei Domini da un'[API](https://api.domainsdb.info/v1/), ne effettui l'analisi sulle 
parole chiave, sull'host e su altri parametri. Supporta anche le funzionalità di filtraggio e statistica sui domini presi dal database.

# Come Usare l'Applicazione
Inserimento immagini e istruzioni varie

# Rotte Applicazione

Tipo | Rotta | Descrizione
---- | ---- | ----  
GET | /domains | Effettua l'analisi su un gruppo di domini.
GET | /stats | Calcola la statistica su un gruppo di domini.
POST | /filter | Effettua il filtraggio dei domini rispettando le condizioni specificate nel body della richiesta.

### Parametri
Nelle rotte è possibile inserire dei parametri del tipo "domain" e "zone" per definire il gruppo di domini. 
Di default i campi saranno riempiti con "domain" = "facebook" e "zone" = "com"
- Esempio di chiamata GET dei /domains con parametri diversi da facebook e com ("google" e "it")

![parametri](https://user-images.githubusercontent.com/71789321/102786635-233d9980-43a0-11eb-88ae-a3c80fa3106a.png)

## GET /domains
```json
{
"name": "your-facebook-address.com",
"createDate": "2020-07-25T06:07:00.044377",
"updateDate": "2020-07-25T06:07:00.044380",
"country": "US",
"isDead": "False"
}
```
 // descrizione  del formato JSON//
#### Risultato chiamata rotta GET /domains su Postman
![esempio_domains](https://user-images.githubusercontent.com/71789321/102786405-b9bd8b00-439f-11eb-8ee2-5be9bce01673.png)

### GET /stats
Le statistiche che abbiamo elaborato si dividono in diversi campi:
```json
{
  "Host countries": {
    "DE": 0,
    "null": 3,
    "JP": 0,
    "IT": 6,
    "US": 10,
    "NL": 0,
    "TR": 0,
    "altro": 3
  },
  "Keywords": {
    "marketing": 1,
    "pages": 0,
    "business": 1,
    "login": 0,
    "vacances": 0,
    "altro": 20
  },
  "Average update time(days)": 69.0,
  "Average life time(days)": 69.0,
  "Quantity": 22
}
```
// descrizione  del formato JSON//

### Risultato della chiamata GET /stats su Postman
![esempio_stats](https://user-images.githubusercontent.com/71789321/102786411-bb874e80-439f-11eb-8188-d014b3d53bc0.png)
### POST /filter
I filtri desiderati vanno inseriti nel Body dell chiamata il formato JSON nel seguente modo:
```json
{
  "name":"cash;business",
  "country":"us;DE;it",
  "createDate":"2020-07-25T14",
  "updateDate": "2020-08-01",
  "isDead":"false"
}
```
I filtri Name e country permettono di ospitare più valori contemporaneamente e il filtro di questi sarà con logica OR mentre
gli altri ne possono ospitare uno solo, il filtraggio tra i diversi campi (es:name, country, ecc..) avviene con logica AND.

I valori dei campi, come da esempio, possono essere indicati indifferentemente con maiuscole o minuscole, il programma sarà in
grado di distinguerle. E'importante non inserire spazi tra i parametri del campo, ma solo ";". Le date di  createDate e updateDate
vanno necessariamente inserite nel formato AAAA-MM-GGTORA.
# Eccezioni
Descrizione delle eccezioni create 

# UML
- Use Case Diagram
    ![OOpUseCaseDiagram](https://user-images.githubusercontent.com/71789321/102777122-47917a00-4390-11eb-8aa2-23429d168bec.png)    

- Class Diagram
    ![OOPClassDiagram](https://user-images.githubusercontent.com/71789321/102722996-4bc48580-4305-11eb-9372-71790f4426d8.PNG)
    ![OOPClassDiagram-Controller](https://user-images.githubusercontent.com/71789321/102723003-5c74fb80-4305-11eb-8ca8-9a9a30a1ccb7.PNG)
    ![OOPClassDiagram-Exception](https://user-images.githubusercontent.com/71789321/102723004-5e3ebf00-4305-11eb-9692-e81fb8a83736.PNG)
    ![OOPClassDiagram-Filters](https://user-images.githubusercontent.com/71789321/102723006-60088280-4305-11eb-9fd2-c47d35c70a43.PNG)
    ![OOPClassDiagram-Model](https://user-images.githubusercontent.com/71789321/102723008-6139af80-4305-11eb-9f81-af559aa19e50.PNG)
    ![OOPClassDiagram-Stats](https://user-images.githubusercontent.com/71789321/102723009-63037300-4305-11eb-9de7-37727658c841.PNG)

# Diagramma delle Sequenze
- Domains
  ![OOPSequenceDiagram-Domains](https://user-images.githubusercontent.com/71789321/102723023-7d3d5100-4305-11eb-85f3-1cbbdb126771.PNG)
  
- Filters
  ![OOPSequenceDiagram-Filters](https://user-images.githubusercontent.com/71789321/102723025-7e6e7e00-4305-11eb-8fac-76e4f675dc7e.PNG)
  
- Stats
  ![OOPSequenceDiagram-Stats](https://user-images.githubusercontent.com/71789321/102723028-7f9fab00-4305-11eb-90a6-03edb3db9fa9.PNG)

  
# Autori
- [Paolo Beci](https://github.com/Paolo-Beci)
- [Emilio Grieco Joseph](https://github.com/emi-2205)
- [Giuseppe Izzi](https://github.com/IzziGiuseppe)
