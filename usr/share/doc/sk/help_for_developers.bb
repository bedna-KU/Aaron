[h1]Nápoveda pre programátorov[/h1]
[a href="#1"]Ako si napísať vlastný príkaz[/a]
* [a href="#11"]Hello World[/a]
* [a href="#12"]Spracovanie vstupu[/a]
* [a href="#13"]Reťazenie príkazov[/a]
* [a href="#14"]Práca so súbormi[/a]
* [a href="#15"]Práca s reťazcami[/a]
* [a href="#16"]Práca so skupinami[/a]

[h2 id="1"]Ako si napísať vlastný príkaz[/h2]
[h2 id="11"]Hello World[/h2]
Princíp je jednoduchý, proste len pošleme text na výstup.
Takto vyzerá príkaz Hello World:

[code]namespace ku;
function hw__ ($shell_option, $shell_data, $input, $step, $echo) {
	$return["output"] = "Hello World!";
	return $return;
}[/code]

Za názvom funkcie je vždy dvojité podtržítko, aby sa dali použiť ako názov aj rezervované slová.
Subor sa ukladá do priečinku [b]/bin/commands[/b], ale už bez podtržítiek,
takže v našom prípade ako [b]/bin/commands/hw.php[/b]

[h2 id="12"]Spracovanie vstupu[/h2]
Spracovanie vstupu z príkazu (linku).
[b]Napríklad http://domain.tld?shell=mocnina 3[/b]
Kód spracuje výstup príkazu a vráti druhú mocninu zadaného čísla:

[code]namespace ku;
function mocnina__ ($shell_option, $shell_data, $input, $step, $echo) {
	$return["output"] = $shell_data * $shell_data;
	return $return;
}[/code]

Reťazec za príkazom sa ukladá do premennej [b]$shell_data[/b]
Pokiaľ ale napíšeme napríklad:

[b]Napríklad http://domain.tld?shell=mocnina -h[/b]

Čiže ak je na začiatku pomlčka, tak sa dáta pošlú do premennej [b]$shell_option[/b]

Príklad s mocninou a nápovedou:

[code]namespace ku;
function mocnina__ ($shell_option, $shell_data, $input, $step, $echo) {
	if ($shell_option == "h") {
		$return["output"] = "Za príkazom zadaj kladné číslo a príkaz ti vráti mocninu toho čísla";
	}
	elseif (!$shell_option) {
		$return["output"] = $shell_data * $shell_data;
	}
	else {
		$return["output"] = "Neznámy prepínač";
	}
	return $return;
}[/code]

Ak napíšeme kladné číslo, vráti nám jeho mocninu,
pokiaľ zadáme [b]-h[/b], zobrazí sa nápoveda
a pokiaľ zadáme iný prepínač než [b]-h[/b], príkaz vráti chybovú hlášku.

Premenná $return["step"] sa používa, ak potrebujete funkciu ktorá potrebuje viac krokov, ako napríklad login.
V premennej $return["step"] si predávame ďalší krok, ako napríklad:

[code]...
$return["output"] = "prvy_krok";
$return["step"] = "druhy_krok";
return $return;
...[/code]

Premennú $return["echo"] nepoužívam, ale vlastne vznikla kvôli tomu, keby sme chceli umlčať výstupy príkazov.

[h2 id="13"]Reťazenie príkazov[/h2]
Na reťazenie príkazov s posielaním výstupu z jedného príkazu do druhého sa používa rúra [b]|[/b]
[b]Napríklad http://domain.tld?shell=mocnina 3 | mocnina[/b]
Výstup z predchádzajúceho príkazu sa nám vráti v premennej $input.
Znovu si upravíme príklad [b]mocnina[/b], tak aby ak existuje výstup z predchádzajúceho príkazu,
spraví sa mocnina z predchádzajúcej mocniny:

[code]namespace ku;
function mocnina__ ($shell_option, $shell_data, $input, $step, $echo) {
	if ($shell_option == "h") {
		$return["output"] = "Za príkazom zadaj číslo a príkaz ti vráti mocninu toho čísla";
	}
	elseif (!$shell_option) {
		if ($input) {
			$return["output"] = $input * $input;
		}
		else {
			$return["output"] = $shell_data * $shell_data;
		}
	}
	else {
		$return["output"] = "Neznámi prepínač";
	}
	return $return;
}[/code]

[h2 id="14"]Práca so súbormi[/h2]
Na bezpečnú prácu so súbormi a právami slúžia príkazy začínajúce s [b]X_[/b]
Vo funkciách je automatická kontrola prístupových práv.

// chmod s testom či sa práva skutočne zmenili
function X_chmod ($file_name, $permissions) {

// Echo s konverziou <> do HTML entities
function X_echo ($string) {

// Test či súbor existuje
function X_file_exists ($file_name) {

// Vráti obsah súboru s testom či sa súbor načítal
function X_file_get_contents ($file_name) {

// fopen s testom či sa súbor otvoril
function X_fopen ($file_name, $mode) {

// Je súbor audio?
function X_is_audio ($file_name) {

// Je to adresár?
function X_is_dir ($path) {

// Je to súbor?
function X_is_file ($path) {

// Je to obrázok?
function X_is_image ($file_name) {

// Je súbor v KUF format?
function X_is_kuf ($file_name) {

// Test či máme práva súbor čítať
function X_is_readable ($file_name) {

// Je súbor video?
function X_is_video ($file_name) {

// Test či máme práva do súboru zapisovať
function X_is_writable ($file_name) {

// Vytvorí priečinok a otestuje či sa priečinok vytvoril
function X_mkdir ($dir_name) {

// Máme právo na čítanie súboru, alebo priečinku?
function X_perm_read ($file_name) {

// Skontroluje formát práv
function X_perm_right ($permissions) {

// Máme právo na zápis do súboru, alebo priečinku?
function X_perm_write ($file_name) {

// Zoskenuje adresár s kontrolou prístupových práv
function X_scandir ($directory) {

// Zoskenuje priečinok bez súborov s bodkou na začiatku
// podľa času vytvorenia
function X_scandir_by_mtime ($directory) {

// Zoskenuje priečinok rekurzívne
function X_scandir_recursive ($directory) {

// Vráti koľko bytov zaberá adresár (rekurzívne)
function dir_size_rec ($directory) {

// Vráti či máme právo súbor presunúť, alebo vymazať
function file_allowed_owner ($file) {

// Vráti obsah KUF súboru v asociatívnom poli
function get_ku_data ($file_name) {

// Vráti mime type súboru
function get_mime ($file_name) {

// Vráti absolútnu cestu k súboru
function get_path ($string) {

// Upraví reťazec s cestou
// Odstráni /./ a /../
// Napríklad /1/2/../3 zmení na /1/3  a cestu /./1 zmení na /1
function get_real_path ($string) {

// Vráti voľné miesto v domovskom priešinku
function home_free () {

// Prevzorkuje obrázok v príkazoch 'cat' a 'ls'
function resample_image ($image_file, $shell_data) {

// Podobne ako 'resample_image'
function resample_image_in_text ($output, $shell_data) {

// Uloží log
function save_log ($path, $mark) {

[h2 id="15"]Práca s reťazcami[/h2]
// Pridá spätnú lomku, pred lomku
function add_back_slash ($string) {

// Skonvertuje byty na kilobajty, megabajty, gigabajty a terabajty
// prepínače sú B, K, M, G a T.
// Ak zadáme human, byty sa skonvertujú do ľudsky čitateľného formátu
function bytes_to_units ($bytes, $unit) {

// Vráti byty v ľudsky čitateľnom formáte
function human_bytes ($bytes) {

// Wráti sekundy vo formáte Hodiny:minúty:sekundy
function human_seconds ($seconds) {

// Prevedie byty v ľudsky čitateľnom formáte na byty
function human_to_bytes ($from) {

// Ak je reťazec v IPv4 format vráti 4
// Ak je reťazec v IPv6 format vráti 6
// Inak vráti FALSE
function is_ip ($ip) {

// Uloži asociatívne pole do súboru v KUF formáte
function put_ku_data ($file_name, $kuf_content) {

// Vráti reťazec zložený zo znakov 0-9 a-z A-Z
function random_string ($length) {

// Odstráni diakritiku
function rm_diacritics ($string) {

// Odstráni diakritiku a symboly
function rm_diacritics_symbols ($string) {

// Validácia emailovej adresy
function validate_email ($email) {

[h2 id="16"]Práca so skupinami[/h2]
// Existuje skupina?
function group_exist ($group) {

// Obsahuje názov skupiny povolené znaky?
function group_name_right ($group_name) {

// Existuje užívateľ?
function user_exists ($user) {

// Je prihlásený užívateľ vlastník skupiny?
function user_is_owner_group ($group) {

// Má prihlásený užívateľ práva na modifikáciu skupiny?
function user_is_perm_group ($group) {

// Je užívateľ členom skupiny?
function user_in_group ($group, $user) {
