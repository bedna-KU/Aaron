[h1]Nápoveda pre administrátorov[/h1]
[a href="#1"]Štruktúra priečinkov[/a]
[a href="#2"]Chybové hlášky[/a]
[a href="#3"]Ako si napísať vlastnú šablónu[/a]
* [a href="#31"]Základná šablóna[/a]
* [a href="#32"]Dizajn stránky[/a]
* [a href="#33"]Zobrazenie výstupu podľa dotazu[/a]
* [a href="#34"]Príklady[/a]
* [a href="#35"]Shell_filter[/a]
* [a href="#36"]Rozdielna šablóna pre hlavnú stránku a ostatné stránky[/a]

[h2 id="1"]Štruktúra priečinkov[/h2]
Adresárová štruktúra vychádza z [a href="https://cs.wikipedia.org/wiki/Filesystem_Hierarchy_Standard"]Filesystem Hierarchy Standard[/a].

[b]bin[/b] -> Programy
└commands -> Všetky príkazy
└inc -> Pomocné funkcie
└javascript -> JavaScriptové funkcie
└shell.php -> Shell
└shell_filter.php -> Shell pre špeciálne použitie
[b]dev[/b] -> Zariadenia
[b]etc[/b] -> Konfiguračné súbory
└apt -> Nastavenia pre aktualizácie
└mail -> Nastavenie emailovej notifikácie
└system -> Nastavenia systému
 └banned -> Súbor so zabanovanými IP adresami
 └bashrc -> Ak užívateľ nemá vytvorené svoje vlastné bashrc, tak sa použije toto
 └conf.php -> Nastavenia systému
 └post_commands.php -> Príkazy ktoré sa spustia po určitom príkaze
└group -> Súbor s užívateľskými skupinami
[b]home[/b] -> Domovský priečinok všetkých užívateľov
[b]tmp[/b] -> Dočasné súbory, napr. prevzorkované obrázky
[b]usr[/b] -> Dáta programov
└sessions -> Sedenia užívateľov
└share -> Dáta pre systém a dokumentácia
 └man -> Manuálové stránky
 └doc -> Dokumentácia
 └themes -> Nastavenia vzhľadu (témy)
[b]var[/b] -> Logy a obsah webu
└log -> Logy
 └articles
 └comments
 └dirs
 └edits
 └errors
 └moves
 └removes
 └system
 └uploads
└www -> Obsah webu
 └.chat -> História chatu
 └.trash -> Kôš, tu sa ukladajú zmazané súbory

[h2 id="2"]Chybové hlášky[/h2]
[b]Chyby z /index.php[/b]
ERR-INDEX-R.CONF -> Nepodarilo sa pripojiť konfiguračný súbor '/etc/system/conf.php'
ERR-INDEX-R.FUNCTION -> Nepodarilo sa pripojiť základné funkcie z '/bin/inc/function.php'
ERR-INDEX-R.FRONTEND -> Nepodarilo sa pripojiť frontend z '/bin/inc/frontend.php'
ERR-INDEX-WF.LOG -> Nepodarilo sa zapisovať do logov
ERR-INDEX-RF.SESSIONS -> Nepodarilo sa čítať súbory z priečinka '/usr/sessions'
ERR-INDEX-RF.GID -> Nepodarilo sa prečítať GID so súboru '/home/uzivatel/.gid.php'
ERR-INDEX-R.SHELL -> Nepodarilo sa pripojiť shell '/bin/shell.php
ERR-INDEX-R.SHELL-FILTER -> Nepodarilo sa pripojiť shell_filter'/bin/shell_filter.php
ERR-INDEX-R.OUTPUT -> Nepodarilo sa pripojiť výstup '/bin/inc/output.php'

[b]Chyby z /bin/inc/kernel.php[/b]
ERR-KERNEL-THEME-DONT-EXIST -> Nepodarilo sa načítať tému

[b]Chyby zo systému[/b]
Prvé sa zobrazí výstraha:
[b]Error, contact root.[/b]
Potom súbor a riadok z ktorého bola volaná funkcia:
[b]SHELL-120[/b]
A ďalej príkaz s volanými parametrami:
[b]ku\chmod__ opt: www dat: home/bedna/ lin: 55[/b]
Na záver sa zobrazí root email:
[b]root email: email@email.com[/b]

[h1 id="3"]Ako si napísať vlastnú šablónu[/h1]
[h2 id="31"]Základná šablóna[/h2]
Na úvod si napíšeme klasické Hello World.

[code]<body>
	Hello World
</body>[/code]

Šablóna je uložená v súbore /usr/share/theme/nazov_temy/template.tpl

Podľa príkladu je vidieť, že v šablóne je možné použiť klasické HTML.
Mi ale budeme chcieť využiť "Aarona" aby nám ponúkol výstupy z príkazov.
Takže napríklad budeme chcieť hlavičku stránky, čo bude súbor "1" a výstup článku čo bude súbor "2",
tak šablóna bude vyzerať takto:

[code]<body>
	{{ shell, cat 1 }}	// Spustí príkaz na spracovanie súboru "1"
	{{ show_output }}	// Zobrazí sa výstup zo súboru "1"
	{{ shell, cat 2 }}	// Spustí príkaz na spracovanie súboru "2"
	{{ show_output }}	// Zobrazí sa výstup zo súboru "2"
</body>[/code]

Z príkladu je vidieť, že pre každú akciu [*] je potrebné spustiť príkaz a potom zobraziť výstup.

[*] Exisujú príkazy ktoré len zobraujú výstup nejakých dát, takže nepotrebujú spustiť predtým príkaz na ich vygenerovanie,
prípadne spustíme príkaz a nepotrebujeme zobraziť výstup.

Na spustenie príkazu vždy slúži časť {{ shell, za čiarkou je už samotný príkaz, ako v príklade na zobraenie článku "2" cat 2 }}
Výstup zobrazíme s {{ show_output }}

[h2 id="32"]Dizajn stránky[/h2]
Dizajn stránky si upravíme pomocou CSS, ktoré sa ukladá do súboru [b]/usr/share/theme/nazov_temy/template.css[/b].
Teraz template.tpl môžeme upraviť takto:

[code]<body>
	{{ shell, cat 1 }}		// Spustí príkaz na spracovanie súboru "1"
	<div class="hlavička">		// CSS pre hlavičku
		{{ show_output }}	// Zobraí sa výstup zo súboru "1"
	</div>
	{{ shell, cat 2 }}		// Spustí príkaz na spracovanie súboru "2"
	<div class="clanok">		// CSS pre článok
		{{ show_output }}	// Zobraí sa výstup zo súboru "2"
	</div>
</body>[/code]

[h2 id="33"]Zobrazenie výstupu podľa dotazu[/h2]
Napr. do prehliadača zadáme: [b]http://www.domain.com?shell=cat 1[/b]
Do šablóny vložíme príkaz na spracovanie dotazu: {{ shell, $shell_request }}
A na zobrazenie výstupu použijeme: {{ show_output }}

Úplne jednoduchý príklad šablóny, ktorý spracuje takýto dotaz:

[code]<body>
	{{ shell, $shell_request }}
	{{ show_output }}
</body>[/code]

Do šablóny pridáme odkazy na jednotlivé články,
po kliknutí na odkaz sa zobrazí vybraný článok.

[code]<body>
	<a href="?shell=cat 1">Článok 1</a>	// Odkaz na zobrazenie článku 1
	<a href="?shell=cat 2">Článok 2</a>	// Odkaz na zobrazenie článku 1
	<a href="?shell=cat 3">Článok 3</a>	// Odkaz na zobrazenie článku 1
	{{ shell, $shell_request }}		// Spusti príkaz na spracovanie dotazu
	{{ show_output }}			// Zobrazí výstup
</body>[/code]

[h2 id="34"]Príklady[/h2]
Takto vyzerá šablóna pre terminál, emaily a chat.

[code]<body>
	<div id = "ku_win">
		{{ shell_filter, form }}		// Ak bol zadaný príkaz form spustí ho
		<div class="mail">
			{{ show_emails }}		// Zobrazí interné emaily
		</div>
		{{ shell_filter, say }}			// Ak bol zadaný príkaz say spustí ho
		<div id="chat">
			{{ show_chat }}			// Zobrazí chat
		</div>
		{{ shell, $shell_request }}		// Spustí príkaz z $_REQUEST
		{{ show_current_command }}		// Zobrazí zadaný príkaz
		<div class="output">
			<div class="clipboard"></div>
			{{ show_output }}		// Zobrazí výstup z príkazu
		</div>
		{{ input }}					// Zobrazí príkazový riadok
		{{ javas, bin/javascript/autocomplete.js }}	// Pripojí Javascript
	</div>
</body>[/code]

[a href="http://kernelultras.org"]Tu je reálne použitie[/a]

[h2 id="35"]Shell_filter[/h2]
[b]Shell_filter[/b] pracuje podobne ako [b]shell[/b], s tým rozdielom, že spracuje len príkaz ktorý mu predáme.
Vo vyššie uvedenom príklade je [b]{{ shell_filter, say }}[/b], takže sa spustí len vtedy, ak bol zadaný príkaz [b]say[/b].
V predvolenom správaní [b]shell_filter[/b] po vykonaní príkazu z príkazového riadku daný príkaz odstráni.
Napríklad pošlene príkaz [b]ls && say test && id[/b], za riadkom [b]{{ shell_filter, say }}[/b] sa už bude spracovávať len [b]ls && id[/b].

[h2 id="36"]Rozdielna šablóna pre hlavnú stránku a ostatné stránky[/h2]
Ak chceme rozdielnu šablónu pre hlavnú stránku a ostatné stránky,
tak pre hlavnú stránku vytvorime šablónu [b]/usr/share/theme/nazov_temy/index.tpl[/b]
a [b]/usr/share/theme/nazov_temy/index.css[/b].
Pre ostatné stránky sa použije [b]/usr/share/theme/nazov_temy/template.tpl[/b]
a [b]/usr/share/theme/nazov_temy/template.css[/b]

Slovenskú verziu napísal: Mário Chorváth - Bedňa
Licencia GPLv2+: GNU GPL verzia 2 alebo neskoršia [a href="https://www.gnu.org/licenses/old-licenses/gpl-2.0.html"]https://www.gnu.org/licenses/old-licenses/gpl-2.0.html[/a].
