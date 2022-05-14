1. utworzenie dowolnej liczby elementów (wierszy) reprezentujących dane studentów dla zakresu informacyjnego podanego poniżej:

```
Nr albumu
Osoba
Grupa
Praca domowa    Max 5
Aktywność       Max 5
Projekt         Max 10
Kolokwium 1     Max 20
Kolokwium 2     Max 20
Egzamin         Max 40
SUMA
```

2. Okno programu ma wyświetlać dane z użyciem komponentu JTable. Przy dodawaniu należy oprogramować walidację:

```
Nr albumu       – niepuste i same cyfry
Osoba           – niepuste i imię oraz nazwisko (co najmniej 2 człony)
Grupa           – niepuste
Praca domowa    – tylko cyfry wartość między 0 a Max podany w nagłówku
Aktywność       – tylko cyfry wartość między 0 a Max podany w nagłówku
Projekt         – tylko cyfry wartość między 0 a Max podany w nagłówku
Kolokwium1      – tylko cyfry wartość między 0 a Max podany w nagłówku
Kolokwium2      – tylko cyfry wartość między 0 a Max podany w nagłówku
Egzamin         – tylko cyfry wartość między 0 a Max podany w nagłówku
```

Po poprawnym dodaniu wiersza automatycznie ma się wyliczać suma pól punktowych.

2. zapis aktualnego stanu tabeli do pliku na dysku, z możliwością podania dowolnej nazwy pliku oraz wyboru ścieżki.
3. odczyt z pliku danych i załadowanie ich do tabeli.

Pliki projektu do przesłania:

1. Kod źródłowy projektu wraz z testami
2. Dokumentacja javadoc
3. Opis podziału prac (w przypadku zespołów wieloosobowych)

## Diagram klas


```plantuml
class MainWindow{
    +Zapisz()
    +Wczytaj()
    +Wyświetl()
    +Dodaj()
    +Edytuj()
    +Usun()
}

class StudentTableView{
    // 
    +Wyświetl()
    +Odśwież()
    +Zaznacz()
}

class StudentTableViewModel{
    +StudentsList
    +Zaznacz()
    +Sortuj() // ?
}

class StudentEditView{
    // Okno w którym dodajemy nowego studenta do listy 
    // oraz edytujemy istniejącego.
    +Dodaj()
    +Zmień()
    +Anuluj()
}

class StudentEditViewModel{
    +Student
    +Zmień()
    +Anuluj()
}

class StudentEditModel{
    +Student
    +Edytuj()
    +Dodaj()
}

class StudentTableModel{

}



MainWindow -- StudentTableView
MainWindow -- StudentEditView

StudentTableView -- StudentTableViewModel
StudentEditView -- StudentEditViewModel

StudentTableViewModel -- StudentTableModel
StudentEditViewModel -- StudentEditModel

StudentTableModel -- IStudentDataService
StudentEditModel -- IStudentDataService


IStudentDataService <|-- MockStudentDataService
IStudentDataService <|-- StudentDataService

interface IStudentDataService{
    +GetData()
    +Update(StudentData)
    +Delete(string album)
    +Add(StudentData)
    +Save()
    +Load()
    +Validate(StudentData): boolean
}

class MockStudentDataService{
    +GetData()
    +Update(StudentData)
    +Delete(string album)
    +Add(StudentData)
    +Save()
    +Load()
}

class StudentDataService{
    IStudentDataPreservationService preservationService;
    IStudentDataValidation validationService;
    +GetData()
    +Update(StudentData)
    +Delete(string album)
    +Add(StudentData)
    +Save()
    +Load()
    +Validate(StudentData): boolean
}

class StudentData{
    Album string
    Name string
    Group string
    Homework int
    Activity int
    Project int
    Kolokwium1 int
    Kolokwium2 int
    Exam int
    Sum int
}

interface IStudentDataPreservationService{
    +Save()
    +Load()
}

class FilesystemStudentDataPreservationService{
    +Save()
    +Load()
}

IStudentDataPreservationService <|-- FilesystemStudentDataPreservationService

StudentDataService --> StudentData

StudentDataService - IStudentDataPreservationService


interface IStudentDataValidation{
    +Validate(StudentData): boolean
}

class DataValidation{
    +Validate(StudentData): boolean
}

IStudentDataValidation - StudentDataService

IStudentDataValidation <|-- StudentDataValidation

```

## Pytania do prowadzącego

1. Czy powinien być cały CRUD dla danych studentów, czy powinno być jedynie dodawanie studenta.

Wydaje mi się że nawet przy pełnym CRUDzie nie będziemy mieli aż tak dużo pracy.

2. Czy pola z punktami powinny być rozszerzalne, czy jest to stała struktura po której nie spodziewamy się zmian.

