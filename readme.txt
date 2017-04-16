Instalare

1. Deschide cu Intellij sau importa in alt IDE



Functionalitate

 - autentificarea se face cu username 'admin' si parola 'admin' pentru modul administrator, username 'user' si parola 'user' pentru simplu angajat. se pot adauga si alti useri

- utilizare mod simplu angajat(user):
 	- pentru cautare carte:
 			- apasa buton <Search> cu campul de input lasat gol --> afiseazxa toate cartile indiferent de campul afisat
 			- apasa buton <Search> cu campul de input completat --> afiseaza toate cartile comform criteriului selectat (!atentie ca e case sensitive)

	- pentru vinderea unei carti, aceasta trebuie selectata din tabel, specificat cantitatea si apasat pe butonul <Sell>

- utilizare mod administrator
	- afisarea tuturor inregistrarilot(useri, carti) se face apasand butonul <Display>
	- pentru adaugare se completeaza campurile de TextField cu datele dorite si se apasa butonul <Add>. Campul de Id nu este editabil, este pur informativ, deci nu se va lua in calcul, aplicatia genereaza id-ul corespunzator
	- pentru editare trebuie selectata din tabel o entitate(user, carte), se fac modificarile in campurile corespunzatoare si se apasa <Edit>
	- pentru stergere trebuie selecatata cartea/userul, apoi se apasa <Delete>
	- pentru generarea de rapoarte se apasa butonul corespunzator tipului de raport care se doreste generat din tabul cu generare de rapoarte.




Informatii utile

- baza de date XML se gaseste in folderul  /xml/
- baza de date XML folosita pentru testare se gasete in  /xml/test/
- rapoartele afiseaza TOP cele mai vandute 10 carti
- rapoartele se genereaza in folderele  /reoprts/pdf, respectiv  /reports/csv
- rapoartele se genereaza avand ca nume data curenta. in cazul in care se genereaza mai multe rapoarte in aceeasi zi, acestea se vor suprascrie