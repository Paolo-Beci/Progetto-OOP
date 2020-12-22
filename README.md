# SprigBoot REST API Domains
L' applicazione SpringBoot ha come obiettivo l'analisi e il monitoraggio dei domini presenti in rete. I dati relativi ai 
domini li otteniamo tramite una [REST API](https://api.domainsdb.info/v1/).

Tra le funzioni di analisi abbiamo il filtraggio e il calcolo di statistiche.

L' utente grazie al Client (ad esempio [Postman](https://www.postman.com/)) può accedere alle funzionalità dell'applicazione grazie
al Web Service [Tomcat](http://tomcat.apache.org/) integrato nel Framework [Spring](https://spring.io/).

----------------------------------------------------------------------------------------------------------------------------------------

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

![parametri](https://user-images.githubusercontent.com/71789321/102865774-8b3ebf00-4436-11eb-85bb-40463371bb38.png)

## GET /domains
- Modello
```json
{
"name": "your-facebook-address.com",
"createDate": "2020-07-25T06:07:00.044377",
"updateDate": "2020-07-25T06:07:00.044380",
"country": "US",
"isDead": "False"
}
```
 Il  JSON sopra riportato indica la rappresentazione utilizzata  per indicare un **dominio**. 
 I campi rappresentano:
 1) **name** = nome.
 2) **createDate** = data creazione.
 3) **updateDate** = data ultimo update.
 4) **country** = paese di hosting.
 5) **isDead** = scadenza.

- Risultato chiamata rotta GET /domains su Postman
  ![esempio_domains](https://user-images.githubusercontent.com/71789321/102865765-8974fb80-4436-11eb-8e02-a36bfd29494b.png)
  Il programma restituisce i domini elaborati sotto forma di un JSONArray.
  
## GET /stats
- Modello
```json
{
  "Host countries": {
    "DE": 3,
    "null": 27,
    "JP": 7,
    "IT": 0,
    "US": 11,
    "NL": 1,
    "TR": 1,
    "altro": 0
  },
  "Keywords": {
    "marketing": 1,
    "pages": 2,
    "business": 3,
    "login": 1,
    "vacances": 1,
    "altro": 42
  },
  "Average update time(days)": 150.0,
  "Average life time(days)": 150.0,
  "Quantity": 50
}
```
Il JSON sopra riportato indica la rappresentazione utilizzata per indicare le statistiche elaborate su un gruppo di domini.
I campi rappresentano:
1) **Host countries** = nazioni di hosting.
2) **Keywords** = parole chiave più comuni.
3) **Average update time(days)** = media dei giorni trascorsi dall'ultimo update.
4) **Average lifetime(days)** = media dei giorni trascorsi dalla creazione.
5) **Quantity** = numero di domini analizzati.
- Risultato chiamata rotta GET /stats su Postman
![esempio_stats](https://user-images.githubusercontent.com/71789321/102865770-8aa62880-4436-11eb-9c3f-f481dc6e6eea.png)
Il programma restituisce le statistiche elaborate sotto forma di un JSONObject.
## POST /filter
- Modello
```json
{
  "name":"xn;cash;business",
  "country":"US;DE;IT",
  "createDate": "2020-07-25T05",
  "updateDate": "T05",
  "isDead":"false"
}
```
Il JSON sopra riportato indica la rappresentazione utilizzata nel body per indicare i filtri da applicare su un gruppo di domini.
I campi rappresentano:
1) **name** = una o più sottostringhe contenute nei domini.
2) **country** = uno o più nazioni di hosting.
3) **createDate** = data creazione.
4) **updateDate** = data ultimo update.
5) **isDead** = scadenza.

- Risultato chiamata rotta POST /filter su Postman
![esempio_filter](https://user-images.githubusercontent.com/71789321/102865766-8a0d9200-4436-11eb-85f1-2483b27fc517.png)

I campi `name` e `country` permettono di ospitare più valori contemporaneamente.
Questi valori verranno applicati come filtri con logica OR.
I campi restanti, invece, come `createDate`, `updateDate` e `isDead`, possono contenere solo un valore.

I valori relativi a campi differenti vengono applicati come filtri con logica AND.

### Attenzione
- I valori dei campi `country` e `isDead` possono essere indicati indifferentemente con maiuscole o minuscole, gli altri no.
- I valori dei campi `name` e `country` devono essere separati da ";" senza spazi.
- Le date nei domini hanno una formatazzione del tipo aaaa-mm-ggTore:minuti:secondi:millisecondi quindi i valori inseriti nei campi `createDate` e `updateDate` devono rispettarla. E' possibile tuttavia inserire delle sottostrinche (es: `"createDate":"mm-ggTore"` oppure `"updateDate":"Tore:minuti"`)

----------------------------------------------------------------------------------------------------------------------------------------

# Eccezioni
 > Il programma può lanciare due eccezioni personalizzate:
- **NoDataException**
  
    Viene richiamata quando il programma riconosce che il vettore domains non contiene alcun dominio.
  Viene visualizzato il seguente messaggio di errore:
```
    I CAMPI DELLA RICHIESTA NON PRODUCONO ALCUN RISULTATO...
    Riprova con diversi campi domain e zone!`
```
- **NoBodyException**
  
    Viene richiamata quando il programma ottiene in input un body vuoto.
Viene visualizzato il seguente messaggio di errore:
```
    IL BODY DELLA CHIAMATA POST NON CONTIENE NESSUN FILTRO
```
 Inoltre possono essere lanciate le seguenti eccezioni standard:
- **Exception**
  - IOException
    - FileNotFoundException
  - RuntimeException
    - ArithmeticException
    - PatternSyntaxException
    - ClassCastException
  - ParseException


----------------------------------------------------------------------------------------------------------------------------------------

# UML
### Use Case Diagram
![OOpUseCaseDiagram](https://user-images.githubusercontent.com/71789321/102777122-47917a00-4390-11eb-8aa2-23429d168bec.png)

----------------------------------------------------------------------------------------------------------------------------------------


### Class Diagram
![(MAIN)OOPClassDiagram](https://user-images.githubusercontent.com/71789321/102722996-4bc48580-4305-11eb-9372-71790f4426d8.PNG)

![(CONTROLLER)OOPClassDiagram-Controller](https://user-images.githubusercontent.com/71789321/102723003-5c74fb80-4305-11eb-8ca8-9a9a30a1ccb7.PNG)
![(EXCEPTION)OOPClassDiagram-Exception](https://user-images.githubusercontent.com/71789321/102723004-5e3ebf00-4305-11eb-9692-e81fb8a83736.PNG)

![OOPClassDiagram-Filters](https://user-images.githubusercontent.com/71789321/102723006-60088280-4305-11eb-9fd2-c47d35c70a43.PNG)

![OOPClassDiagram-Model](https://user-images.githubusercontent.com/71789321/102723008-6139af80-4305-11eb-9f81-af559aa19e50.PNG)

![OOPClassDiagram-Stats](https://user-images.githubusercontent.com/71789321/102723009-63037300-4305-11eb-9de7-37727658c841.PNG)

----------------------------------------------------------------------------------------------------------------------------------------

# Diagramma delle Sequenze
- /domains
  ![OOPSequenceDiagram-Domains](https://user-images.githubusercontent.com/71789321/102723023-7d3d5100-4305-11eb-85f3-1cbbdb126771.PNG)
  
- /filter
  ![OOPSequenceDiagram-Filters](https://user-images.githubusercontent.com/71789321/102723025-7e6e7e00-4305-11eb-8fac-76e4f675dc7e.PNG)
  
- /stats
  ![OOPSequenceDiagram-Stats](https://user-images.githubusercontent.com/71789321/102723028-7f9fab00-4305-11eb-90a6-03edb3db9fa9.PNG)

----------------------------------------------------------------------------------------------------------------------------------------
# Test JUNIT
>Nel programma è presente anche una sezione di testing: 
- **Test 1**
  
  Verifica che il vettore domains non sia null.
- **Test 2**

    Verifica se il filtro FilterCountry viene correttamente applicato.
- **Test 3**

  Verifica se le statistica Quantity viene correttamente calcolata.
- **Test 4**

  Verifica se l'eccezione NoDataException viene lanciata correttamente.
    
----------------------------------------------------------------------------------------------------------------------------------------

# Plus del programma
:white_check_mark: Filtri multipli e sovrapponibili \n
:white_check_mark: Backup dei dati da locale in caso di url corrotto \n
-[x] Testing JUNIT
-[X] Personalizzazione dei parametri durante la richiesta all'API

# Autori
- [Paolo Beci](https://github.com/Paolo-Beci)
- [Emilio Grieco Joseph](https://github.com/emi-2205)
- [Giuseppe Izzi](https://github.com/IzziGiuseppe)
