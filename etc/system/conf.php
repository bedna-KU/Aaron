<?php
// ####################################################################
// Aaron web operating system
// config file
// Author: Mário Chorváth - Bedna
// (D.M.Y) 23.07.2012
// License GPLv2: GNU GPL version 2 https://www.gnu.org/licenses/gpl-2.0.html
// This is free software: you are free to change and redistribute it.
// There is NO WARRANTY, to the extent permitted by law.
// ####################################################################
// PLEASE SORT VARIABLES BY NAME
// --------------------------------------------------------------------
// Allowed paths
$allowed_paths = array ("var/www");
// Autocomplete additional paths
$autocmpCMD_add_paths = array ("/var/www");
// Banned servers
$banned_servers = array ("");
// Basic output
$basic_output = "Boot KERNEL ULTRAS kernel 0.0.1 RC4\nKERNEL ULTRAS ready tty1\n\nType help for commands\n\n[a href=\"?shell=cat /var/www/1\"]Donate[/a]";
// Chat bot
$chatbot = TRUE;
// Chat bot name
$chatbot_name = "Kubo";
// Chat max search days
$chat_max_days = 90;
// Chat max lines
$chat_max_lines = 1000;
// Chat min lines
$chat_min_lines = 5;
// Decimal separator
$decimal_separator = ".";
// No logged us
$default_user_name = "guest";
// Set time to expire cookie
$expire_cookie = time () + 189216000;
// Friendly servers
$friendly_servers = array ("");
// Image resample quality
$image_resample_quality = 75;
// Default language
$LANG = "en";
// Max upload file size (in bytes)
$max_upload_file_size = 250000000;
// Max upload size for all files (in bytes)
$max_upload_size_all = 250000000;
// Max image size for resize in text
$max_image_width = 480;
// Max image size for resize in gallery
$max_image_width_gallery = 200;
// Master server
$master_server = "http://kernelultras.org";
// Im mater server?
$im_master = TRUE;
// Min length of password
$min_pass_size = 6;
// Current path
$PWD = 'var/www';
// Root email
$root_email = 'kdesi@kdesi.sk';
// Root web email
$root_web_email = 'kdesi@kdesi.sk';
// Server domain
$server_domain = 'kernelultras.org';
// Name of server
$server_name = 'kernelultras';
// Shell history length
$shell_history_length = 30;
// Timing operations
$system_timer = FALSE;
// Timing operations color
$system_timer_color = "#@[black]";
// Timing operations color for index
$system_timer_color_sh = "#@[yellow]";
// Name template
$template = 'KU';
// Title name
$title_name = 'KERNEL ULTRAS';
// Suffix files
$web_file_suffix = 'kuf';
// Name of file in which files are stored
$web_file_type = '1.kuf';
// Where is www files
$www_path = 'var/www';
// ####################################################################
// Groups for commands
// ####################################################################
$cmd_group["add"] = array ("user","guest");
$cmd_group["adduser"] = array (" ");
$cmd_group["alias"] = array ("user");
$cmd_group["aptitude"] = array ("root");
$cmd_group["cat"] = array (" ");
$cmd_group["cd"] = array ("user","guest");
$cmd_group["chmod"] = array ("user","guest");
$cmd_group["com"] = array ("user","guest");
$cmd_group["deluser"] = array ("user");
$cmd_group["download"] = array (" ");
$cmd_group["du"] = array (" ");
$cmd_group["echo"] = array (" ");
$cmd_group["edit"] = array ("user");
$cmd_group["exitsu"] = array (" ");
$cmd_group["feh"] = array (" ");
$cmd_group["grep"] = array (" ");
$cmd_group["groupadd"] = array ("user");
$cmd_group["groupdel"] = array ("user");
$cmd_group["groupmod"] = array ("user");
$cmd_group["help"] = array (" ");
$cmd_group["head"] = array (" ");
$cmd_group["helpkuf"] = array (" ");
$cmd_group["id"] = array (" ");
$cmd_group["ip"] = array (" ");
$cmd_group["login"] = array (" ");
$cmd_group["logout"] = array ("user");
$cmd_group["logs"] = array ("root");
$cmd_group["ln"] = array ("user");
$cmd_group["ls"] = array (" ");
$cmd_group["lshref"] = array (" ");
$cmd_group["man"] = array (" ");
$cmd_group["mkdir"] = array ("user","guest");
$cmd_group["moo"] = array (" ");
$cmd_group["mooo"] = array (" ");
$cmd_group["mv"] = array ("user");
$cmd_group["nano"] = array ("user");
$cmd_group["news"] = array (" ");
$cmd_group["nmap"] = array ("user");
$cmd_group["note"] = array ("user");
$cmd_group["notify"] = array ("user");
$cmd_group["passwd"] = array ("user");
$cmd_group["ping"] = array ("user","guest");
$cmd_group["pwd"] = array (" "); ///
$cmd_group["rm"] = array ("user");
$cmd_group["say"] = array (" ");
$cmd_group["su"] = array ("root");
$cmd_group["ufw"] = array ("root");
$cmd_group["upload"] = array ("user","guest");
// $cmd_group["useradd"] = array ("user");
$cmd_group["usermod"] = array ("user");
$cmd_group["vlc"] = array (" ");
$cmd_group["w"] = array (" ");
$cmd_group["whois"] = array ("user","guest");
$cmd_group["whoisku"] = array (" ");
$cmd_group["write"] = array ("user");
$cmd_group["xtime"] = array (" ");
?>
