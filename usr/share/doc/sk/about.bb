[h2]O projekte[/h2]
[a href="#1"]Čo je Aaron[/a]
[a href="#2"]Ako to začalo[/a]

[h2 id="1"]Čo je Aaron[/h2]
Je to [b]webový operačný systém[/b] napísaný od nuly. Obsahuje rovnako ako klasický operačný systém, jadro, shell, štandardný vstup a výstup. Namiesto odkazov spracúva príkazy, čo mu dáva veľké možnosti, kedy môže spracovať viacero príkazov naraz.

Napríklad na banovanie niektorých škodlivých robotov používam príkaz:

[code]logs 1 | grep hladany_retazec | bar -s 7 | ufw deny[/code]

Príkazy sú oddelené rúrou |, čo spôsobí, že výstup jedného príkazu sa pošle do ďalšieho.
Príkaz 'logs 1' vytiahne logy z jedného dňa, 'grep hladany_retazec' vyfiltruje len riadky ktoré obsahujú hľadaný reťazec, 'bar -s 7' vytiahne siedmi stĺpec kde sú IP adresy, no a 'ufw deny' hodí IP adresy do banlistu.

Viac nápovedy zistíte príkazom 'man', napríklad 'man grep'. Toto je hlavný rys Aarona, všetko je zdokumentované, priamočiare a jednoduché.

Oproti podobným projektom nesaje a funguje aj bez JavaScriptu.
JavaScript používam len tam kde to naozaj uľahčuje prácu, ako je automatické doplňovanie príkazov, história príkazov a podobne.

JavaScript sa používa aj na šifrovanie na užívateľskej strane, čo je momentálne jediné riešenie ako sa to dá realizovať a upload veľkých súborov.

[h2 id="2"]Ako to začalo[/h2]
Nápad vznikol v jeden deň keď som sa bavil s Amigapowerom, že by sme si spravili vlastný web a vyzeralo by to ako terminál. Základ som spravil za deň dva. O verzií 0.0.1 vyšla na známom českom linuxovom portáli aj [a href="http://www.abclinuxu.cz/zpravicky/spusten-web-ku"]správička.[/a]
Postavil som to na základoch Linuxu, ako sú textové konfiguračné súbory, funkcionálne programovanie a zdokumentovanie celého projektu.
Veľká časť kódu sú komentáre, aby si kód dokázal upraviť aj neprogramátor.

Slovenskú verziu napísal: Mário chorváth
Licencia GPLv2+: GNU GPL verzia 2 alebo neskoršia [a href="https://www.gnu.org/licenses/old-licenses/gpl-2.0.html"]https://www.gnu.org/licenses/old-licenses/gpl-2.0.html[/a].
