# Antwoorden op technische vragen over het Alhambra: The Dice Game project

## 1. Hoe werken resourcebundels? Wat is het nut ervan? Toon in de code hoe je ermee werkt.

Resourcebundles maken het mogelijk om een applicatie meertalig te maken zonder de code zelf aan te passen. In dit project is dit geïmplementeerd via de `TaalManager` klasse:

```java
public class TaalManager {
    private ResourceBundle bundel;
    private String taalCode;

    public TaalManager(String taal) {
        this.taalCode = taal;
        Locale gekozenTaal = new Locale(taal);
        bundel = ResourceBundle.getBundle("resourcebundles.messages", gekozenTaal);
    }

    public String geef(String sleutel) {
        try {
            return bundel.getString(sleutel);
        } catch (MissingResourceException e) {
            System.out.println("Waarschuwing: Taalsleutel '" + sleutel + "' niet gevonden!");
            return sleutel;
        }
    }

    public String getCode() {
        return taalCode;
    }
}
```

In plaats van hardgecodeerde strings te gebruiken, vraagt de applicatie teksten op via sleutels:

```java
// In plaats van: Label lblTitel = new Label("Eindresultaat");
Label lblTitel = new Label(taal.geef("resultaatscherm.eindresultaat"));
```

Het nut is dat de applicatie eenvoudig meertalig gemaakt kan worden. De taal kan worden gewijzigd zonder de code aan te passen, enkel door een andere resourcebundle te selecteren.

## 2. Wat is het nut/voordeel van de javadoc-comment? Toon aan.

Javadoc-comments zijn speciaal geformatteerde commentaren die worden gebruikt om automatisch API-documentatie te genereren.

**Nut/voordelen:**
1. **Consistente documentatie**: Standaard format voor alle code
2. **Automatische generatie**: Genereer HTML-documentatie
3. **IDE-integratie**: Toon documentatie tijdens het programmeren als tooltips
4. **Code begrijpelijkheid**: Maakt het makkelijker voor andere ontwikkelaars om de code te begrijpen

Een voorbeeld uit de `GebouwpuntGebied.java` klasse:

```java
/**
 * De GebouwpuntGebied klasse vertegenwoordigt een gebied met verschillende
 * gebouwen die overeenkomen met specifieke dobbelkleuren. Elk gebouw heeft een
 * bijbehorende lijst van gebouwstenen die in het gebied geplaatst kunnen
 * worden.
 */
public class GebouwpuntGebied extends Gebied {
    // ...
    
    /**
     * Haalt het gebouw op dat overeenkomt met de opgegeven dobbelkleur.
     * 
     * @param dKleur de DobbelKleur van het gewenste gebouw
     * @return het GebouwVanGebouwpuntGebied dat overeenkomt met de opgegeven
     *         dobbelkleur
     */
    public GebouwVanGebouwpuntGebied geefGebouw(DobbelKleur dKleur) {
        // implementatie
    }
}
```

Dit genereert documentatie die alle parameters, return types en een algemene beschrijving van de functie bevat. Zeer nuttig bij het samenwerken in teams of wanneer anderen je code moeten gebruiken.

## 3. Toon aan dat je applicatie voor UC.... een robuuste applicatie is.

Als voorbeeld voor UC1 (Registreer nieuwe speler), maakt je applicatie gebruik van verschillende technieken om robuustheid te garanderen:

**1. Invoervalidatie**: In de `Speler.java` klasse worden gebruikersnaam en geboortejaar grondig gevalideerd:

```java
private void setGebruikersnaam(String gebruikersnaam) {
    if (gebruikersnaam == null || gebruikersnaam.isBlank() || gebruikersnaam.length() < 6)
        throw new IllegalArgumentException(taal.geef("fout.gebruikersnaamOngeldig"));
    this.gebruikersnaam = gebruikersnaam;
}

private void setGeboortejaar(int geboortejaar) {
    int huidigJaar = LocalDate.now().getYear();
    if (geboortejaar > huidigJaar - 6 || geboortejaar < huidigJaar - 100)
        throw new IllegalArgumentException(taal.geef("fout.geboortejaarOngeldig"));
    this.geboortejaar = geboortejaar;
}
```

**2. Exception handling**: In `Applicatie.java` worden exceptions opgevangen en gebruikersvriendelijk getoond:

```java
try {
    registreerSpeler();
    ok = true;
} catch (IllegalArgumentException e) {
    System.out.println(e.getMessage());
}
```

**3. Database-integriteit**: In `SpelerRepository.java` wordt gecontroleerd of een gebruikersnaam al bestaat voordat een nieuwe speler wordt toegevoegd:

```java
public void voegToe(Speler speler) {
    if (bestaatSpeler(speler.getGebruikersnaam()))
        throw new GebruikersnaamInGebruikException();
    mapper.voegToe(speler);
}
```

**4. Testen**: Unit tests zoals `SpelerTest.java` controleren of de regels correct worden toegepast.

Dit maakt de applicatie robuust omdat het:
- Gebruikersinvoer controleert
- Fouten netjes afhandelt
- Data-integriteit waarborgt
- Gedrag verifieert via tests



## 4. Wat is de taak/zijn de verantwoordelijkheden van een DomeinController?

De DomeinController heeft drie hoofdtaken:

1. **Fungeert als tussenpersoon tussen UI en domeinlaag**:
   - De Applicatie klasse roept alleen methodes van de DomeinController aan (zoals `dc.registreerSpeler()`), zonder direct toegang tot Speler objecten of de SpelerRepository.

2. **Verzamelt complexe operaties om de UI eenvoudig te houden**:
   - Wanneer een nieuwe ronde wordt gestart, roept de UI alleen `dc.startNieuweRonde()` aan, waarna de DomeinController achter de schermen alles regelt: fiches aanvullen, dobbelstenen resetten, zetstenen aan spelers toewijzen.

3. **Beschermt de domeinlaag door validatie**:
   - In de methode `dc.kiesSpelerEnKleur(gebruikersnaam, kleur)` controleert de controller eerst of de speler bestaat, of de kleur geldig is, en of deze nog niet in gebruik zijn.

Deze opzet zorgt ervoor dat:
- De UI-code eenvoudig blijft
- Fouten op één plek worden afgevangen
- De domeinlaag zuiver blijft en zich alleen bezighoudt met spelregels
- Het later eenvoudiger is om een andere UI toe te voegen (zoals de overgang van console naar grafische interface)

## 5. Wat zijn de verantwoordelijkheden van een repositoryklasse?

Een repository in dit project heeft drie hoofdverantwoordelijkheden:

1. **Dient als magazijn voor objecten**:
   - Bewaart objecten zoals Spelers of Spellen
   - Bijvoorbeeld: `SpelerRepository` slaat alle spelers op die aan het spel deelnemen

2. **Regelt communicatie met de database**:
   - Zorgt voor het opslaan en ophalen van gegevens zonder dat andere klassen SQL-kennis nodig hebben
   - Bijvoorbeeld: `spelerRepository.geefAlleSpelers()` haalt alle spelers op via de mapper

3. **Biedt eenvoudige methodes voor data-operaties**:
   - Zoals toevoegen, updaten, verwijderen en opzoeken
   - Bijvoorbeeld: `spelerRepository.voegToe(nieuweSpeler)` voegt een speler toe zonder directe SQL

In het project zijn er twee repositories:
- `SpelerRepository` voor het beheer van Speler-objecten
- `SpelRepository` voor het beheer van Spel-objecten 

Ze werken samen met mapper-klassen die de SQL-commando's uitvoeren.

## 7. Illustreer hoe en tot welke producten je tijdens de analyse van UC x bent gekomen.

Voor de analyse van bijvoorbeeld UC4 (Speel ronde) ben ik begonnen met het bestuderen van de use case en de domeinregels. Eerst heb ik een domeinmodel opgesteld met alle relevante klassen zoals Spel, Speler, Zetsteen, Gebouwsteen, etc. en hun onderlinge relaties.

Daarna heb ik een activiteitendiagram (AD) gemaakt dat visueel weergeeft hoe het proces van een ronde spelen verloopt, met de verschillende stappen zoals het bepalen van de speler aan de beurt, het toevoegen van beloningen en het berekenen van de punten.

Vervolgens heb ik systeem sequentiediagrammen (SSD) opgesteld die tonen hoe de applicatie interageert met de gebruiker tijdens het spelen van een ronde. Bijvoorbeeld hoe een speler zijn beurt speelt en hoe het systeem reageert.

Op basis van de SSD's heb ik operatiecontracten (OC's) opgesteld die precies beschrijven wat er gebeurt bij elke operatie, zoals welke objecten worden aangemaakt of gewijzigd. Bijvoorbeeld voor de operatie 'pasScoresVanSpelersAan' heb ik beschreven welke klassen hierbij betrokken zijn en wat de pre- en postcondities zijn.

Al deze producten hebben samen geleid tot een volledig begrip van de use case voordat ik begon aan het ontwerp.

## 8. Illustreer hoe doing en knowing verantwoordelijkheden gescheiden worden gehouden in het project.

In het project is een duidelijk onderscheid tussen 'knowing' (weten) en 'doing' (doen) verantwoordelijkheden:

**Knowing verantwoordelijkheden** (klassen die vooral informatie bevatten):
- De `Zetsteen` klasse is een goed voorbeeld: deze kent alleen zijn eigen kleur en heeft weinig methodes. De methode `getKleur()` is een typische 'knowing' verantwoordelijkheid - het geeft informatie terug die de klasse over zichzelf weet.
- Ook de `Gebouwsteen` klasse heeft vooral 'knowing' verantwoordelijkheden: het kent zijn eigen kleur en positie zonder complexe operaties uit te voeren.

**Doing verantwoordelijkheden** (klassen die operaties uitvoeren):
- De `Spel` klasse is duidelijk een 'doer': het coördineert het spelverloop met methodes zoals `vulFichesAan()`, `plaatsZetsteen()`, en `bepaalSpelerVolgorde()`.
- De `DomeinController` is ook een 'doer': het coördineert acties tussen verschillende klassen met methodes zoals `registreerSpeler()` en `kiesSpelerEnKleur()`.

Deze scheiding zorgt voor betere onderhoudbaarheid: als de regels voor het plaatsen van zetstenen veranderen, hoeft alleen de 'doing' code in `Spel` aangepast te worden, terwijl de 'knowing' klasse `Zetsteen` ongewijzigd kan blijven.

## 9. Illustreer op welke manier GRASP patronen toegepast zijn bij het maken van het ontwerp.

In het project zijn verschillende GRASP patronen toegepast:

**Controller patroon**:
- De `DomeinController` klasse dient als enige toegangspunt tussen de UI en het domein
- Het handelt commando's af zoals `registreerSpeler()` en `kiesSpelerEnKleur()`
- Dit zorgt ervoor dat de UI niet rechtstreeks met domeinobjecten hoeft te werken

**Expert patroon**:
- Verantwoordelijkheden zijn toegewezen aan de klassen die de benodigde informatie hebben
- Bijvoorbeeld: de `Spel` klasse is verantwoordelijk voor het plaatsen van zetstenen omdat deze alle informatie heeft over het spelbord en dobbelstenen
- De `Speler` klasse beheert eigen zetstenen en score, omdat deze de expert is over deze informatie

**Creator patroon**:
- Klassen zijn verantwoordelijk voor het aanmaken van objecten waarmee ze nauw verbonden zijn
- Bijvoorbeeld: de `Spel` klasse maakt de `Dobbelsteen` objecten aan omdat het spel deze dobbelstenen gebruikt
- De `SpelerRepository` is verantwoordelijk voor het aanmaken van `Speler` objecten vanuit database gegevens

## 10. Leg bondig het 3-lagen model uit en illustreer de toepassing ervan in je code.

Het 3-lagen model in ons project bestaat uit:

1. **De presentatielaag (UI)**:
   - Dit zijn de packages `cui` (console UI) en `gui` (grafische UI)
   - Toont informatie aan de gebruiker en verwerkt gebruikersinvoer
   - Heeft geen directe kennis van domeinlogica of databases

2. **De domeinlaag**:
   - Dit is het package `domein`
   - Bevat alle spellogica en businessregels
   - Klassen zoals `Spel`, `Speler`, `Dobbelsteen`, `Fiche` zitten hier
   - Weet niets over hoe gegevens worden getoond of opgeslagen

3. **De persistentielaag**:
   - Dit is het package `persistentie`
   - Zorgt voor het opslaan en ophalen van gegevens
   - Bevat mapper-klassen zoals `SpelerMapper` die SQL-queries uitvoeren

Voorbeeld van deze scheiding in onze code:
Wanneer een gebruiker een nieuwe speler registreert, gebeurt het volgende:
1. In de UI (`Applicatie.java`) roept de gebruiker `registreerSpeler()` aan
2. De UI roept `dc.registreerSpeler(gebruikersnaam, geboortejaar)` aan op de DomeinController
3. De DomeinController valideert de gegevens en maakt een nieuw `Speler`-object aan
4. De DomeinController geeft dit door aan de `SpelerRepository`
5. De `SpelerRepository` roept `mapper.voegToe(speler)` aan
6. De `SpelerMapper` zet dit om naar een SQL INSERT statement

Deze strikte scheiding zorgt ervoor dat:
- We gemakkelijk van UI kunnen wisselen (we hebben zowel console als GUI implementaties)
- We domeinlogica kunnen testen zonder database of UI
- We in de toekomst de database kunnen vervangen zonder de domeinlogica aan te passen

## 11. Het ontwerp van de grafische laag is heel programmeertaalafhankelijk. Leg uit hoe je deze laag opgebouwd hebt in jullie project.

In ons project hebben we de grafische laag opgebouwd volgens een gestructureerd patroon met drie soorten klassen:

1. **Scherm-klassen** - Dit zijn de hoofdcontainers voor elk scherm:
   - Zoals `HoofdScherm`, `SpelScherm` en `ResultaatScherm`
   - Deze klassen definiëren de layout van elk scherm met containers zoals VBox, HBox en BorderPane
   - Ze behandelen navigatie tussen verschillende schermen via `getScene().setRoot(new NieuwScherm(dc))`
   - Bijvoorbeeld: `HoofdScherm` bevat de logic voor het registreren van spelers en het starten van een spel

2. **Manager-klassen** - Deze klassen beheren specifieke spelcomponenten:
   - Zoals `DobbelsteenManager`, `SpelbordManager` en `SpelerManager`
   - Ze halen data op van de domeinlaag en zetten deze om naar visuele elementen
   - Ze beheren ook gebruikersinteracties met deze componenten
   - Bijvoorbeeld: `DobbelsteenManager` regelt het werpen van dobbelstenen en het selecteren ervan

3. **View-klassen** - Dit zijn herbruikbare visuele componenten:
   - Zoals `DobbelsteenView` en `SpelerZetsteenView`
   - Ze tonen individuele spelobjecten en kunnen overal hergebruikt worden
   - Bijvoorbeeld: `DobbelsteenView` toont een dobbelsteen met zijn kleur

Deze structuur geeft ons verschillende voordelen:
- Betere scheiding van verantwoordelijkheden
- Gemakkelijker onderhoud omdat specifieke functionaliteit geclusterd is
- Herbruikbaarheid van componenten zoals views

## 12. Eventafhandeling en/of wisselen van scherm: toon hiervan een voorbeeld uit jullie project en leg de code uit.

In onze JavaFX applicatie gebruiken we event handling om te reageren op gebruikersacties en om tussen schermen te wisselen:

**Voorbeeld 1: Taalwissel met event handling**

```java
rbNL.setOnAction(e -> {
    if (rbNL.isSelected()) {
        TaalManager nieuweTaal = new TaalManager("nl");
        dc.stelTaalIn(nieuweTaal);
        this.taal = nieuweTaal;
        // Herlaad het scherm
        getScene().setRoot(new HoofdScherm(dc));
    }
});
```

Hier gebeurt het volgende:
1. We voegen een event handler toe aan de RadioButton rbNL (voor Nederlands)
2. We gebruiken een lambda expressie (e -> {...}) om code te definiëren die uitgevoerd wordt bij een klik
3. We controleren of de knop geselecteerd is
4. We maken een nieuwe TaalManager aan voor Nederlands
5. We geven deze door aan de DomeinController via dc.stelTaalIn()
6. We bewaren de taal ook lokaal
7. We wisselen van scherm door getScene().setRoot(new HoofdScherm(dc)) aan te roepen - hiermee vervangen we het huidige scherm met een nieuw HoofdScherm dat de nieuwe taalinstellingen gebruikt

**Voorbeeld 2: Spelscherm laden met validatie**

```java
btnStart.setOnAction(e -> {
    if (dc.spel().geefSpelers().size() >= 3) {
        getScene().setRoot(new SpelScherm(dc));
    } else {
        infoBox.appendText(taal.geef("hoofdscherm.minimaalDrieSpelers") + "\n");
    }
});
```

Hier zien we:
1. Een event handler op de startknop
2. Validatie die controleert of er minstens 3 spelers zijn (een vereiste volgens de spelregels)
3. Als aan de voorwaarde is voldaan, wisselen we naar het SpelScherm
4. Anders tonen we een foutmelding in de infoBox

## 14. Bespreek hoe en waarom jouw domeinmodel, EERD en DCD verschillend zijn.

De drie modellen (domeinmodel, EERD en DCD) hebben verschillende doelen en zijn daarom ook verschillend in opbouw:

1. **Domeinmodel** is een conceptueel model dat alle belangrijke concepten uit het probleem toont:
   - Het bevat alleen klassen en hun relaties, zonder implementatiedetails
   - Het toont alleen attributen die essentieel zijn voor het begrijpen van het domein
   - In ons Alhambra project bevat het klassen zoals `Speler`, `Spel`, `Dobbelsteen`, `Zetsteen`, etc.
   - Het bevat geen methodes of technische details

2. **EERD (Enhanced Entity-Relationship Diagram)** is specifiek voor de database-structuur:
   - Het toont hoe gegevens worden opgeslagen in tabellen
   - Het bevat entiteiten zoals SPELER, SPEL en SPELER_SPEL (voor de many-to-many relatie)
   - Het is veel eenvoudiger dan het domeinmodel omdat we alleen opslaan wat nodig is
   - In ons project slaan we bijvoorbeeld geen volledige objecten op zoals `Dobbelsteen` of `Fiche`, omdat die bij elke spelronde opnieuw worden aangemaakt
   - We slaan wel gegevens op zoals spelresultaten en spelerstatistieken

3. **DCD (Design Class Diagram)** is een gedetailleerd ontwerp voor implementatie:
   - Het bevat alle klassen, methodes, attributen, parameters en return types
   - Het toont alle implementatiedetails zoals private/public
   - Het bevat extra klassen zoals repository- en controller-klassen die niet in het domeinmodel staan
   - In ons project hebben we bijvoorbeeld `SpelerRepository` en `DomeinController` die niet in het domeinmodel staan

Concrete verschillen in ons project:
- In het domeinmodel hebben we klassen zoals `Dobbelsteen`, maar in de EERD komt deze niet voor omdat dobbelstenen niet persistent opgeslagen worden
- In het domeinmodel hebben we geen repositories, maar in het DCD wel (`SpelerRepository`, `SpelRepository`)
- In het EERD hebben we een SPELER_SPEL koppeltabel, terwijl in het DCD dit een verzameling is binnen de Spel-klasse
- Het DCD bevat veel meer details zoals de exacte implementatie van methodes als `plaatsZetsteen()` en `vulFichesAan()`
