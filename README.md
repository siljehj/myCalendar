# Prosjekt TDT4100 V2023

## Beskrivelse av prosjektet

Jeg har laget en kalender-app. Kalenderen i appen har 28 dager (for å kunne lage et enkelt 4x7 rutenett). Det gir meg mulighet til å fokusere på andre funksjoner i appen. 

Når brukeren åpner kalender-appen for første gang, vises et tomt 4x7 rutenett med dato i hver rute. Brukeren kan legge til aktiviteter ved å fylle ut fire felter (activity name, date, start time og end time), velge en av fem kategorier fra en drop down-meny og trykke på en `Add activity`-knapp. Dersom brukeren prøver å legge til en aktivitet med ugyldig eller manglende input, dukker det opp et vindu som sier hva som er feil. Krav til input er:

-	Alle felter må være fylt ut.
-	Datoen må være et tall mellom 1 og 28.
-	Starttidspunkt og sluttidspunkt må skrives på formen HH:MM, hvor tallene utgjør et gyldig klokkeslett.
-	Sluttidspunkt må være etter starttidspunkt.

Dersom inputen er gyldig, blir aktiviteten lagt til i den tilhørende dato-ruta i kalenderen med starttid, sluttid og navn. Aktivitetene blir skrevet til fil ettersom de blir lagt til, og dersom brukeren lukker kalenderen og åpner den på nytt, vil aktivitetene fortsatt være i kalenderen.
Eksempel på fil:
```
Skitur,Workout,14,12:00,15:00
Bursdag,Social,3,15:00,19:00
Fotballtrening,Workout,16,14:00,16:30
```

Brukeren har mulighet til å lage en ny kalender (og slette den gamle) ved å trykke på en `New calendar`-knapp. Da dukker det opp en tom kalender. I tillegg kan brukeren filtrere kalenderen ut ifra fem kategorier ved hjelp av en drop down-meny og en `Filter`-button.

Bilde av kalenderen:
![](./screenshot.png)

## Diagram

![](./Klassediagram.png)

## Spørsmål

Modellen min inneholder fire klasser:
-	En `PersonalCalendar`-klasse som er en kalender.
-	En `CalendarTile`-klasse for hver dato i kalenderen, som inneholder en liste med aktiviteter.
-	En `Activity`-klasse for aktiviteter.
-	En `FileHandler`-klasse som håndterer alt som innebærer filer.

### **Hvilke deler av pensum i emnet dekkes i prosjektet, og på hvilken måte?**

Jeg har implementert grensesnitt gjennom å bruke Comparable-grensesnittet i `Activity`-klassen min, for å kunne sortere lister med aktiviteter etter en gitt forutsetning, noe som i mitt tilfelle er starttidspunkt. 
Videre har jeg brukt delegering. Når en aktivitet legges til kalenderen med `addNewActivity`-metoden i `PersonalCalendar`-klassen, delegeres denne oppgaven videre til `CalendarTile`-objektet tilhørende datoen til aktiviteten.

### **Dersom deler av pensum ikke er dekket i prosjektet deres, hvordan kunne dere brukt disse delene av pensum i appen?**

Jeg føler at jeg har brukt de delene av pensum som var hensiktsmessige for min app med de funksjonene jeg har valgt å implementere, men hvis jeg hadde lagt til mer funksjonalitet, hadde det vært nyttig å legge til andre deler av pensum.

Hvis jeg hadde lagt til en funksjon hvor hver kalender hører til en `User`, hvor man logger inn med brukeren, kunne jeg også lagt til en funksjon hvor et `Activity`-objekt kan ha en kobling med flere `User`-instanser. Hvis jeg i tillegg hadde lagt til en funksjon for endring eller sletting av `Activity`-objekter, hadde det vært hensiktsmessig å implementere observatør-observert-teknikken, hvor flere `User`-objekter observerer `Activity`-objekter, og får beskjed når tilstanden deres endrer seg. Da ville det også vært lurt å benytte grensesnitt for begge parter.

### **Hvordan forholder koden deres seg til Model-View-Controller-prinsippet?**

Så å si all logikken i appen min ligger i modellen min. Når man skriver ting inn i brukergrensesnittet (View), blir det sendt via Controlleren ned i klassene i modellen, som endrer tilstanden til objektene, og lagrer dataene. Controlleren henter deretter de oppdaterte dataene fra modellen, og oppdaterer brukergrensesnittet ut ifra den oppdaterte tilstanden til objektene.

Da jeg begynte på prosjektet, la jeg for mye logikk i Controlleren. Det endret jeg på senere, da jeg innså at det var mye mer hensiktsmessig å ha logikken i modell-klassene. All koden for endring av View ligger imidlertid i Controlleren (eller hjelpeklassen).

### **Hvordan har dere gått frem når dere skulle teste appen deres, og hvorfor har dere valgt de testene dere har? Har dere testet alle deler av koden? Hvis ikke, hvordan har dere prioritert hvilke deler som testes og ikke?**

Jeg begynte med å skrive tester for Activity-klassen, så skrev jeg for resten av klassene i modellen. Jeg skrev tester for alle klassene i modellen min, og tester så å si alle metoder, med unntak av noen get-metoder, som var totalt uinteressante.
