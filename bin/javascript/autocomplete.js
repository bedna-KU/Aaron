// ####################################################################
// JavaScript functions
// For kernelultras 0.0.1
// (D.M.Y) 08.12.2014
// Author: Mário Chorváth - Bedna
// ####################################################################
// var autocmpCMD = ["say", "says", "cat", "nano", "nat", "nas", "name", "nagets", "nar", "nak", "narrow", "niiic", "niiie", "eat"];
// var shellHistory = ["say", "says", "cat", "nano", "nat", "nas", "name", "nagets", "nar", "nak", "narrow", "niiic", "niiie", "eat"];

// Variables
var counter = 0;
var subDir = "";
var matches = [];
var myInput = document.getElementById ("shell");
var myInputArea = document.getElementById ("content");
var tempKey;
var autocmpCMD = autocmpCMDcommands;

// Action on paste
// document.onpaste = pasteText;

// document.getElementById("ku_win").designMode = "on";

// document.getElementById("ku_win").addEventListener("paste", pasteText, false);
// autocmpCMD_temp = autocmpCMD;
// autocmpCMD = autocmpCMD.concat (autocmpPATH);
// autocmpCMD = [];

// Set SHELL history length
if (typeof shellHistory == 'undefined' || shellHistory == null) {
	shellHistoryCount = "0";
	shellHistory = " ";
}
else {
	shellHistoryCount = shellHistory.length;
}

// Set SHELL history on last command
shellHistoryCurrent = shellHistoryCount;

// Read directory with XHR
readDirectory ();

// ====================================================================
// Search command by regex and return him,
// or return all similiar commands by regex
// ====================================================================
function searchStringInArray (str, strArray) {
	// Regex to search command from beginning of string
	var rgxp = new RegExp ("^"+str+".*", "g");
	matches = [];
	// Search matches in array with commands
	for (var i = 0; i < strArray.length; i++) {
		if (strArray[i].match (rgxp)) {
			matches.push (strArray[i]);
		}
	}
	return matches;
}

// ====================================================================
// Add event listener on keyup
// ====================================================================
document.body.addEventListener("keyup", onkeyup);

// ====================================================================
// On key up test "shell" is in viewport?
// ====================================================================
function onkeyup (event) {
	if (document.getElementById("shell") && !document.getElementById("clipboard")) {
		if (!isElementOutViewport (shell)) {
			shell.focus ();
		}
	}
	if (document.getElementById("content") && event.keyCode != 9) {
		if (!isElementOutViewport (form.content)) {
			form.content.focus ();
		}
	}
}

// ====================================================================
// Test element if is out of viewport
// ====================================================================
function isElementOutViewport (el) {
    var rect = el.getBoundingClientRect();
    return rect.bottom < 0 || rect.right < 0 || rect.left > window.innerWidth || rect.top > window.innerHeight;
}

// ====================================================================
// Add event listener on keydown
// ====================================================================
document.body.addEventListener("keydown", onkeydown);

// ====================================================================
// If pressed Ctrl+
// ====================================================================
function onkeydown (event) {
	// --------------------------------------------------------------------
	// If pressed Ctrl+N
	// --------------------------------------------------------------------
	if (event.ctrlKey && event.keyCode == 78) {
		event.preventDefault ();
		window.location.href = "index.php?shell=news";
	}
	// --------------------------------------------------------------------
	// If pressed Ctrl+V
	// --------------------------------------------------------------------
	if (event.ctrlKey && event.keyCode == 86) {
		if (document.activeElement.id != "shell" && document.activeElement.id != "content") {
			// Get output
			var x = document.getElementsByClassName ("clipboard");
			// Create textarea
			x[0].innerHTML = '<textarea class="" id="clipboard" rows="0" cols="0"></textarea>';
			// Focus textarea
			clipboard.focus ();
			// Add event úaste on textarea
			document.getElementById("clipboard").addEventListener("paste", pasteText, false);
			// Run paste into clipboard
		}
	}
	// --------------------------------------------------------------------
	// If pressed Ctrl+C
	// --------------------------------------------------------------------
	else if (event.ctrlKey && event.keyCode == 67) {
		// Get selected text from textarea getElementById("content")
		if (document.getElementById ("content")) {
			var activeItem = "content";
		}
		else if (document.getElementById ("shell")) {
			var activeItem = "shell";
		}
		if (activeItem) {
			var selectedItem = document.getElementById (activeItem);
			var selectedStart = selectedItem.selectionStart;
			var selectedEnd = selectedItem.selectionEnd;
			if (selectedEnd - selectedStart > 0) {
				var selectedText = selectedItem.value.substring(selectedStart, selectedEnd);
			}
		}
		// Run copy only if no selected text in page and textarea getElementById("content")
		if (window.getSelection() == "" && typeof selectedText === 'undefined') {
			var worker_read_clipboard = new Worker ('bin/javascript/worker_read_clipboard.js');

			userLoginName_enc = encodeURIComponent (userLoginName);

			download_array = {"name": "clipboard", "sessionID": sessionID, "userLoginName": userLoginName_enc};

			worker_read_clipboard.postMessage (download_array);
			// worker_write_clipboard.terminate ();
			// Event worker - after successfully write clipboard on server
			worker_read_clipboard.onmessage = function (event) {
				var textContent = event.data;
				var x = document.getElementsByClassName ("clipboard");
				x[0].innerHTML = 'Text copied successfully from remote clipboard';
				// x[0].innerHTML = '<textarea id="clipboard" rows="20">' + textContent + '</textarea>';
				x[0].innerHTML = '<textarea id="clipboard" rows="20"></textarea>';
				// document.designMode = "on";
				// clipboard.contentEditable = "true";
				clipboard.value = textContent;
				clipboard.focus ();
				clipboard.select ();
				// copyToClipboard ();
				worker_read_clipboard.terminate ();
			}
		}
	}
}

// Not work in FireFox
//~ function copyToClipboard () {
	//~ var successful = document.execCommand('copy');
	//~ if (successful) {
		//~ console.log ("OK");
	//~ }
	//~ else {
		//~ console.log ("NO");
	//~ }
 //~ }

// ====================================================================
// Add event listener on Paste
// ====================================================================
function pasteText (e) {
	var pastedText = undefined;
	// Trapping data from clipboard
	if (window.clipboardData && window.clipboardData.getData) {
		pastedText = window.clipboardData.getData ('Text');
	}
	else if (e.clipboardData && e.clipboardData.getData) {
		pastedText = e.clipboardData.getData ('text/plain');
	}
	// Write clipboard into file with XHR2
	writeClipboard (pastedText);
}

// ====================================================================
// XHR - Write clipboard to server
// ====================================================================
function writeClipboard (clipboard) {
	// New worker for upload clipboard
	var worker_write_clipboard = new Worker ('bin/javascript/worker_write_clipboard.js');
	// Encode clipboard and uer login name
	clipboard_enc = encodeURIComponent (clipboard);
	userLoginName_enc = encodeURIComponent (userLoginName);
	// Array for worker
	upload_array = {"name": "clipboard", "content": clipboard_enc, "size": clipboard_enc.length, "sessionID": sessionID, "userLoginName": userLoginName_enc};
	// Spawn worker
	worker_write_clipboard.postMessage (upload_array);
	// worker_write_clipboard.terminate ();
	// Event worker - after successfully write clipboard on server
	worker_write_clipboard.onmessage = function (event) {
		var textContent = event.data;
		var x = document.getElementsByClassName ("clipboard");
		// If clipboard successfully uploaded
		if (textContent == "OK") {
			x[0].innerHTML = 'Text copied successfully into remote clipboard</h1>';
		}
		else {
			x[0].innerHTML = '<h1 class="red">'+textContent+'</h1>';
		}
		// Terminate worker
		worker_write_clipboard.terminate ();
	}
}

// ====================================================================
// Add event listener on keypressed
// ====================================================================
// If command line
if (myInput) {
	if (myInput.addEventListener) {
		myInput.addEventListener ('keydown',this.keyHandler,false);
	} else if (myInput.attachEvent) {
		myInput.attachEvent ('onkeydown',this.keyHandler); /* damn IE hack */
	}
}
// If textarea
if (myInputArea) {
	if (myInputArea.addEventListener) {
		myInputArea.addEventListener ('keydown',this.keyHandler,false);
	} else if (myInputArea.attachEvent) {
		myInputArea.attachEvent ('onkeydown',this.keyHandler); /* damn IE hack */
	}
}

// ====================================================================
// Key handler, autocompete command, or part of command
// ====================================================================
function keyHandler (e) {
	// readDirectory (".");
	// Keycode for Page Up
	var PGUP = 33;
	// Keycode for Page Down
	var PGDN = 34;
	// Keycode for TAB
	var TABKEY = 9;
	// If pressed Page Up or Page Down unfocus "shell" for scroll on page
	if (e.keyCode == PGUP || e.keyCode == PGDN) {
		if (document.getElementById("shell")) {
			shell.blur ();
		}
		if (document.getElementById("content")) {
			form.content.blur ();
		}
	}
	// If pressed TAB
	if (e.keyCode == TABKEY && document.getElementById("shell")) {
		// Get Current cursor position
		var currentCursorPos = this.selectionStart;
		// Part of commandline from beginning to cursor
		var CMDlineBeginning = this.value.substring (0, currentCursorPos);
		// Part of commandline from cursor to end
		var CMDlineEnd = this.value.substring (currentCursorPos, this.value.length);
		// Regex to extract (part) command on current position
		var rgxp2 = new RegExp ("[a-zA-Z0-9_\./\@\-]+$", "g");
		var tempLastCMD = CMDlineBeginning.match (rgxp2);
		// If finds one or more matches in commands array
		if (searchStringInArray (tempLastCMD && tempLastCMD[tempLastCMD.length - 1], autocmpCMD)) {
			// Matches writes in a row separated spaces
			var output = matches.join (' ');
			// If search only one match, autocomplete command or path
			if (matches.length == 1) {
				// Output
				if (matches[0].slice (-1) == "/") {
					this.value = CMDlineBeginning + matches[0].substring (tempLastCMD[0].length, matches[0].length);
					subDir = matches[0];
					// autocmpCMD.push (subDir);
					readDirectory (this.value);
				}
				else {
					this.value = CMDlineBeginning + matches[0].substring (tempLastCMD[0].length, matches[0].length) + " ";
					subDir = subDir.substring(0, subDir.lastIndexOf('/'));
					readDirectory (this.value);
				}
				// Count right cursor position
				cursorOffset = matches[0].substring (tempLastCMD[0].length, matches[0].length).length + 1;
				this.value += CMDlineEnd;
				this.selectionStart = currentCursorPos + cursorOffset;
				this.selectionEnd = this.selectionStart;
				// Clear autocomplete line
				document.getElementById ("autocomplete").innerHTML = "";
			}
			// If search more then one match, write all matches
			else if (matches.length > 1) {
				document.getElementById ("autocomplete").innerHTML = output;
				// Set minPosition to 999, because search match in all matches
				var minPosition = 999;
				var tempPosition = 0;
				// Loop for all commands and paths
				for (var i = 1; i < matches.length; i++) {
					// Loop for all charakters of expression
					for (var j = 0; j <= matches[0].length; j++) {
						// Compare matches
						if (matches[0].substring (0, j) == matches[i].substring (0, j)) {
							tempPosition = j;
						}
					}
					// Search match in all matchces (Select minimal)
					if (tempPosition < minPosition) {
						minPosition = tempPosition;
					}
					// Output
					this.value = CMDlineBeginning + matches[0].substring (tempLastCMD[0].length, minPosition);
					// Count right cursor position
					cursorOffset = minPosition - tempLastCMD[0].length;
					this.value += CMDlineEnd;
					this.selectionStart = currentCursorPos + cursorOffset;
					this.selectionEnd = this.selectionStart;
				}
				matches = [];
			}
			else {
				// Print all matches
				output = matches.join (' ');
				document.getElementById ("autocomplete").innerHTML = output;
			}
		}
		// Prevent default action
		if (e.preventDefault) {
			e.preventDefault ();
		}
		return false;
	}
	// Shell history - Arrow UP
	else if (e.keyCode == "38" && document.getElementById("shell")) {
		shellHistoryCurrent--;
		// If shell history is lower then 0
		if (shellHistoryCurrent < 0) {shellHistoryCurrent = 0;}
		// Output to comandline
		this.value = shellHistory[shellHistoryCurrent] + " ";
		this.selectionStart = this.value.length - 1;
	}
	// Shell history - Arrow DOWN
	else if (e.keyCode == "40" && document.getElementById("shell")) {
		shellHistoryCurrent++;
		// If shell history is bigger then max
		if (shellHistoryCurrent > shellHistoryCount) {shellHistoryCurrent = shellHistoryCount;}
		// If shell history is bigger then max, add empty command
		if (!shellHistory[shellHistoryCurrent]) {shellHistory[shellHistoryCurrent] = "";}
		// Output to comandline
		this.value = shellHistory[shellHistoryCurrent];
	}
}

// ====================================================================
// XHR - read directory
// ====================================================================
function readDirectory () {
	// Define worker for read directory
	var worker_autocomplete = new Worker ('bin/javascript/worker_autocomplete.js');
	userLoginName_enc = encodeURIComponent (userLoginName);
	// Aray for sned to worker
	autocomplete_array = {"pwd": pwd, "subDir": subDir, "sessionID": sessionID, "userLoginName": userLoginName_enc};
	// Send array to worker
	worker_autocomplete.postMessage (autocomplete_array);
	// worker_autocomplete.terminate ();
	// Event worker - after successfully read directory on server
	worker_autocomplete.onmessage = function (event) {
		// Returned list files from server
		var json_data_files = event.data;
		// Parse list files to array
		if (json_data_files) {
			var obj_files = JSON.parse (json_data_files);
		}
		// Insert autocomplete paths to array
		if (typeof obj_files != 'undefined') {
			autocmpCMD = obj_files;
			// Add commands to autocomplete array
			autocmpCMD = autocmpCMD.concat (autocmpCMDcommands);
		}
		// Terminate worker
		worker_autocomplete.terminate ();
	}
}



