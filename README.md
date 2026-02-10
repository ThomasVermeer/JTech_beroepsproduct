# J-Tac Beheerapplicatie 🛡️

Dit project is een beheerapplicatie voor militairen, materieel en voertuigen, ontwikkeld als beroepsproduct voor J-Tech.

## 📋 Implementatieplan

### 1. Projectoverzicht
De J-Tac applicatie is ontworpen om de logistiek rondom soldaten en hun uitrusting te digitaliseren. De applicatie biedt een interface voor het beheren van personeel, voertuigen en materiaal, inclusief een koppelsysteem voor uitgifte.

### 2. Systeemvereisten
* **Taal**: Java 17+
* **Framework**: JavaFX (zonder FXML voor volledige controle)
* **Database**: MySQL server
* **Build Tool**: Maven

### 3. Implementatiestrategie (MVC)
De software is opgebouwd volgens het **Model-View-Controller** patroon:
* **Models**: Java-klassen die de data uit de database representeren (Soldaat, Voertuig, etc.).
* **Views (Screens)**: Handmatig gecodeerde JavaFX-schermen gebaseerd op de wireframes.
* **Controllers**: De logica die de interactie tussen de UI en de database regelt.

### 4. UI & Styling
De applicatie volgt een strikte **styleguide** om een professionele en rustige uitstraling te waarborgen:
* **Kleuren**: Gebruik van Primary Blue (#1C8AFF) en Primary Black (#1A1A1A).
* **Typografie**: Hoofdlettertype is Roboto (secundair Dialog) met een standaard body-grootte van 10px.
* **Elementen**: Afgeronde navigatieknoppen en gestileerde invoervelden via een centrale CSS-file.

### 5. Risk Management & Testing
* **Risico**: Database-connectiviteitsproblemen.
* **Oplossing**: Gebruik van een centrale `DatabaseConnection` klasse met foutafhandeling.
* **Testing**: Handmatige unit-tests op CRUD-functies binnen de Controllers.
