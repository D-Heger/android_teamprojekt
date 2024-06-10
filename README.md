## To-do erweitern
Idee: "Gamification" in die To-do-Listen App aus der Übungsmappe einbauen, sprich ein Charakter, den man mit dem Abschließen von To-dos Leveln kann.

| Pro                                        | Contra                                        |
| ------------------------------------------ | --------------------------------------------- |
| Coole Idee, würde vielleicht spaß machen   | Plaungsaufwand                                |
| Wenn gut argumentiert, auch guter Anspruch | Umsezung von Gamification kann Aufwendig sein |
| Mit Konzept potenziell leichter            | "Design" ist komplizierter                    |
| Gut fürs Git Portfolio                     |                                               |

Speichern der Daten?
- XML (Viel manueller Shit)
- JSON (Anscheinend das einfachste, ka wie gut erweiterbar)
- Datenbank mit Realm (anscheinend einfacher als room)
- Datenbank mit Room (kennen wir)

# Gamification

Nutzer erstellt Charakter nach initialem Launch der App
- Eigenschaften:
	- Name
	- Icon/Bild (Auswahl aus wenigen möglichen, kann auch weg wegen Zeit)
	- Globales Level
- Levelbare Attribute:
	- Stärke
	- Wahrnehmung
	- Ausdauer
	- Charisma
	- Intelligenz
	- Beweglichkeit
	- Glück
- muss gespeichert werden über Sessions hinaus

# TODO
Sortieren von Todos nach:
- Anfangsdatum
- Enddatum
- Priorität 
- (Kategorien)
Eigenschaften:
- Titel
- Beschreibung
- Anfangsdatum (Standardmäßig automatisch gesetzt, durch Nutzer änderbar)
- Enddatum (Stat Verlust bei nicht einhalten)
- Priorität
- Kategorie(n)
- Status (nicht fertig, fertig)

# Priorität
Auswahl aus vorbestimmten:
- Niedrig
- Mittel
- Hoch

# Kategorien
Auswahl aus einer Liste. Nach skills oder Alphabet sortiert?
Stärke:
- Krafttraining
- Gartenarbeit
- Umzugshilfe
- Heimwerken
- Schneeschippen

Wahrnehmung:
- Meditation
- Naturbeobachtung
- Lesen
- Rätsel
- Fotografie

Ausdauer:
- Joggen
- Rad fahren
- Schwimmen
- Wandern
- Tanzen

Charisma:
- Öffentliches sprechen
- Small Talk
- Freiwilligenarbeit
- Karaoke
- Verhandeln

Intelligenz:
- Sprachkurse
- Programmieren
- Schach spielen
- Sprachen lernen
- Buch lesen

Beweglichkeit:
- Gymnastik
- Klettern
- Akrobatik
- Stretching
- Yoga

Glück:
- Dankbarkeitsübungen
- Reisen
- Essen genießen
- Musik hören/machen
- Tierpflege

# Menü
## Einstellungen
Speichern über SharedPreferences, mit Listener für Veränderungen, womit diese dann live applied werden
General:
- Schriftgröße 

Colors:
- Hintergrundfarbe
- Textfarbe
- Todo nicht fertig
- Todo fertig
- (Custom Farben für Priorität)
## Zurücksetzen 
besser als eigene Menüoption, damit wird der Character neu erstellt/die Level alle auf 0 zurückgesetzt 


## Plan für Montag
UI für:
- Todo List Activity - Done
	- Todo Item - Done
- Add Todo Activity - Done
- Character Activity - Done
- Settings Activity - Done
## Plan für Dienstag
Datenbank für Todo und Character
- Entities
- Todo Funktionen:
	- Einzelnes Todo per ID holen
	- Alle Todos holen
	- Todo hinzufügen
	- Todo aktuallisieren/bearbeiten
	- Todo löschen
	- Alle Todo löschen (für Reset)
- Character Funktionen:
	- Character hinzufügen (für initial character creation dialog)
	- Character bearbeiten (für name/alter, am besten auch per dialog)
	- Character löschen (für Reset)
	- Character leveln
	- Einzelne Attribute leveln
