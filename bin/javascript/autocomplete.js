// ####################################################################
// JavaScript functions
// For kernelultras 0.0.1
// (D.M.Y) 08.12.2014
// Author: Mário Chorváth - Bedna
// ####################################################################
// var autocmpCMD = ["say", "says", "cat", "nano", "nat", "nas", "name", "nagets", "nar", "nak", "narrow", "niiic", "niiie", "eat"];
var matches = [];
var myInput = document.getElementById ("shell");

// --------------------------------------------------------------------
// Search command by regex and return him,
// or return all similiar commands by regex
// --------------------------------------------------------------------
function searchStringInArray (str, strArray) {
	// Regex to search command from beginning of string
	var rgxp = new RegExp ("^"+str+".*", "g");
	matches = [];
	// Search matches in array with commands
	for (var i = 0; i < strArray.length; i++) {
		if (strArray[i].match (rgxp)) {
			matches.push (autocmpCMD[i]);
		}
	}
	return matches;
}

// --------------------------------------------------------------------
// Add event listener on keypressed
// --------------------------------------------------------------------
if (myInput.addEventListener) {
	myInput.addEventListener ('keydown',this.keyHandler,false);
} else if (myInput.attachEvent) {
	myInput.attachEvent ('onkeydown',this.keyHandler); /* damn IE hack */
}

// --------------------------------------------------------------------
// Key handler, autocompete command, or part of command
// --------------------------------------------------------------------
function keyHandler(e) {
	// Keycode for TAB
	var TABKEY = 9;
	// If pressed TAB
	if (e.keyCode == TABKEY) {
		// Get Current cursor position
		var currentCursorPos = this.selectionStart;
		// Part of commandline from beginning to cursor
		var CMDlineBeginning = this.value.substring (0, currentCursorPos);
		// Part of commandline from cursor to end
		var CMDlineEnd = this.value.substring (currentCursorPos, this.value.length);
		// Regex to extract (part) command on current position
		var rgxp2 = new RegExp ("[a-z]+$", "g");
		var tempLastCMD = CMDlineBeginning.match (rgxp2);
		// If finds one or more matches in commands array
		if (searchStringInArray (tempLastCMD && tempLastCMD[tempLastCMD.length - 1], autocmpCMD)) {
			// Matches writes in a row separated spaces
			var output = matches.join (' ');
			// If search only one match, autocomplete command or path
			if (matches.length == 1) {
				// Output
				this.value = CMDlineBeginning + matches[0].substring (tempLastCMD[0].length, matches[0].length) + " ";
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
		}
		if (e.preventDefault) {
			e.preventDefault ();
		}
		return false;
	}
}
