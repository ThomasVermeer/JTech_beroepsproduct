# J-Tac Beheerapplicatie 🛡️

Dit project is een beheerapplicatie voor militairen, materieel en voertuigen, ontwikkeld als beroepsproduct voor J-Tech.

## 📋 Implementatieplan

### 1. Projectoverzicht & Huidige Staat
De J-Tac applicatie is ontworpen om de logistiek rondom personeel en uitrusting te digitaliseren. De applicatie is momenteel volledig ontwikkeld en getest in een ontwikkelomgeving. Om het systeem in een productieomgeving te implementeren, worden de volgende stappen doorlopen:

* **Database Initialisatie**: Het uitvoeren van het SQL-script op de productie-server om de tabellen, relaties en indexen aan te maken.
* **Configuratie**: De `DatabaseConnection` parameters aanpassen naar de gegevens van de productiedatabase.
* **Artifact Building**: Het genereren van de executable JAR-file inclusief alle dependencies (zoals de MySQL-connector).

### 2. Systeemvereisten
* **Taal**: Java 17+
* **Framework**: JavaFX (zonder FXML voor volledige controle)
* **Database**: MySQL server
* **Build Tool**: Maven

### 3. Implementatiestrategie (MVC)
De software is opgebouwd volgens het **Model-View-Controller** patroon om de code overzichtelijk en onderhoudbaar te houden:
* **Models**: Java-klassen die de data uit de database representeren (`Soldaat`, `Voertuig`, `Materiaal`,'Koppelingen').
* **Views (Screens)**: Handmatig gecodeerde JavaFX-schermen gebaseerd op de wireframes voor maximale controle over de UI.
* **Controllers**: De logica die de interactie tussen de UI en de database regelt.

### 4. UI & Styling
De applicatie volgt een strikte **styleguide** voor een professionele uitstraling:
* **Kleuren**: Gebruik van Primary Blue (`#1C8AFF`) en Primary Black (`#1A1A1A`).
* **Typografie**: Hoofdlettertype is Roboto (secundair Dialog) met een standaard body-grootte van 10px.
* **Elementen**: Afgeronde navigatieknoppen en gestileerde invoervelden via een centrale CSS-file.

### 5. Tijdlijn van Uitvoering
De implementatie vindt plaats in een gefaseerde tijdlijn van vier weken:
* **Week 0 (Ontwikkeling)**: Realisatie van de MVC-structuur, Database-ontwerp en JavaFX schermen.
* **Week 1 (Voorbereiding)**: Inrichten van de MySQL-omgeving en testen van de database-integriteit.
* **Week 2 (Installatie)**: Uitrollen van de JAR-file naar een kleine testgroep (Pilot-fase).
* **Week 3 (Training & Feedback)**: Gebruikersinstructies geven en eventuele UI-fricties oplossen op basis van eerste bevindingen.
* **Week 4 (Go-Live)**: Volledige ingebruikname van het systeem voor alle afdelingen.

### 6. Communicatiestrategie
* **Statusupdates**: Wekelijkse updates naar de stakeholders van J-Tech over de voortgang van de uitrol.
* **Feedbackloop**: Gebruikers kunnen bugs of verbeterpunten melden via een centraal registratiepunt (e-mail).
* **Instructie**: Er wordt een korte handleiding (Quick Reference Card) meegeleverd die de kernfuncties (toevoegen, koppelen, zoeken) uitlegt.

### 7. Risk Management & Testing
* **Risico**: Database-connectiviteitsproblemen tijdens de overgang naar productie.
* **Oplossing**: Gebruik van een centrale `DatabaseConnection` klasse met robuuste foutafhandeling.
* **Testing**: Handmatige unit-tests op CRUD-functies binnen de Controllers en geautomatiseerde tests op de model-logica.

### 8. Evaluatie & Verificatie
De uiteindelijke situatie wordt als volgt geverifieerd en geëvalueerd:
* **Verificatie**: Door middel van een acceptatietest (zie bijgevoegd testrapport) wordt gecontroleerd of alle functionele eisen naar behoren werken in de productieomgeving.
* **Performance check**: Gebruik van `EXPLAIN` queries in de database om te verifiëren dat de gemaakte **Indexes** de snelheid waarborgen bij grote hoeveelheden data.
* **Evaluatie**: Een maand na implementatie vindt een evaluatiegesprek plaats met stakeholders om te bepalen of de applicatie de logistieke druk effectief vermindert.
