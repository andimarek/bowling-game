Im Unterordner "bowling" ist meine Lösung zu dem Problem "Bowling-Game".

Es ist ein Eclipse/Maven Projekt mit Standard Maven Struktur.
scr/main/java -> Java Code der Lösung
src/test/java -> JUnit Test Klassen
pom.xml

Es wurde Eclipse 3.7, Maven 3 und Java 7 benutzt (OS was Linux). Als Bibliotheken für Tests wurde Junit und Mockito eingesetzt (siehe pom.xml)

Über das Programm/Design:

Die Klasse ConsoleReader enthält eine Main Methode, die von der Konsole Rolls einließt, 
bis das Spiele beendet ist und dann das Endergebnis und jeden einzelnen Frame ausgibt. (Auch in der Manifest Datei des 
Jar Files eingetragen, d.h. das generierte Archiv ist direkt mit java ausführbar.)


Die Klasse BowlingGame repräsentiert das laufende Spiel als ganzes und enthält eine Liste von Frame Objekten. Ein Frame
enthält eine Liste von RollValues (Integer, umgeworfene Pins pro Versuch) und ein Score.

Die Frame Objekte werden vom BowlingGame erstellt und beim Erstellen wird eine Implementierung von FrameStategy mitgegeben.
Die Strategy bestimmt das Verhalten des Frames (Strategy Pattern). Es gibt eine Implementierung für die Frames von 1 bis 9 und 
eine Implementierung für das letzte Frame.

Durch die Benutzung des Strategy Patterns kümmert sich jedes Frame selbst um seine Score Berechnung und definiert selbst, wann
ein Frame beendet ist. Die BowlingGame Klasse bestimmt lediglich wann welche Strategy benutzt werden soll.

Damit jedes Frame selbst sein Score berechnen kann und es keine direkte Abhängikgeit zum Game gibt, wurde ein RollListener eingeführt und jedes 
Frame kann sich über einen RollListenerManager dort registrieren. (Siehe NormalFrameStrategy.)

Es gibt Unit Tests mit JUnit (teilweise mit Mockito als Mocking Tool) sowie die Klasse AcceptanceTest, die zwei komplette Spieldurchläufe simuliert
und verifiziert.

Abgrenzung: Die Konsolen Eingabe läßt bestimmte unsinnige Wert zu. D.h. man kann z.B. als Roll 5 und 7 direkt hintereinander eingeben. Eine Fix wäre 
durch eine neue Methode im FrameStrategy, die den nächsten möglichen Wert auf Gültigkeit überprüft, einfach und sauber (passt ins Design) möglich.


Andreas Marek






