# SQaLibur-Core

## Die Anbindung von SQaLibur an eine Veranstaltung

Der neue Bestandteil SQaLibur soll das Erstellen einer Übungsserie erweitern und dabei speziell für eine Veranstaltung installiert werden. Dabei nutzt es den gleichen Ansatz wie das bekannte LOOP von OSTEPU, wobei es als Erweiterung einer Veranstaltung hinzugefügt werden kann 

![A: die SQaLibur Erweiterung installieren](images/ostepu-course/A.png)
> A: die SQaLibur Erweiterung installieren

Wie in Abbildung A zu sehen, muss SQaLibur ausgewählt und über die Schaltfläche ``speichern`` entsprechend installiert werden. Dabei legt SQaLibur einen neuen Eintrag in der Datenbank an, durch welchen es als verarbeitendes Modul beim Erstellen von Übungsserien gewählt werden kann. Damit ist es SQaLibur möglich, auf das Einsenden eines Studenten zu reagieren und Bewertungen der Einsendungen beim Hochladen vorzunehmen.

## Die Erstellung einer Übungsserie

Damit SQaLibur in einer Übungsserie verwendet wird, muss es als Verarbeitung für jede Aufgabe gewählt werden, in der es Wirken soll.
Darüber hinaus erfolgt die Erstellung einer Übungsserie und Aufgabe wie bisher.

![B: SQaLibur als Verarbeitung wählen](images/ostepu-serieErstellen/A.png)
> B: SQaLibur als Verarbeitung wählen

Dazu wird also entsprechend Abbildung B ausgewählt, dass man eine Verarbeitung verwenden möchte.

![C: SQaLibur als Verarbeitung konfigurieren](images/ostepu-serieErstellen/B.png)
> C: SQaLibur als Verarbeitung konfigurieren

Nun muss wie in Abbildung C das Modul SQaLibur gewählt werden, sodass sich der Konfigurationsbereich des Moduls öffnet. Hier können wir nun jeweils eine SQL-Datei als Musterlösung und für den Kontext hochladen. Dabei ist es unwichtig in welcher Reihenfolge die geschieht, denn die Zuordnung erfolgt anhand der enthaltenen SQL-Elemente. Wenn er also eine Create-Table Anweisung erkennt, so wird er diese Datei als Kontext betrachten. Wenn er eine andere Art von Anfragen in der jeweiligen Datei findet oder nur eine einzelne Datei eingereicht wird, so wir er diese als Musterlösung interpretieren.

Wenn die Übungsserie nun in dieser Form angelegt wird, können die Studenten ihre Einsendungen hochladen, welche dann durch OSTEPU während des Einsendevorgangs zunächst von SQaLibur bearbeitet werden.

## Die Verwendung von SQaLibur durch Studenten
TODO TODO TODO
