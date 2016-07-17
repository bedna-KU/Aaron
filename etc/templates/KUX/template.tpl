<body onLoad = "scrollChatDown()">						// Scroll Chat down
	// {{ shell, $shell_request }}						// Run command from $_REQUEST
	<div id = "ku_win">
		<div id="menu_up">
			<div class="menu_up_button">
				<a href="?"><img src="etc/templates/KUX/kutux.png" height="20px"></a>			// Help button
			</div>
			<div class="menu_up_button">
				<a href="?shell=help"><span class="menu_up_text">HELP</span></a>			// Help button
			</div>
			<div class="menu_up_button">
				<a href="?shell=ls"><span class="menu_up_text">LS</span></a>			// Help button
			</div>
			<div class="menu_up_button">
				<a href="?shell=id"><span class="menu_up_text">ID</span></a>				// ID button
			</div>
			<div class="menu_up_button">
				<a href="?shell=ip"><span class="menu_up_text">IP</span></a>				// IP button
			</div>
			<div class="menu_up_button">
				<a href="?shell=w"><span class="menu_up_text">W</span></a>				// W button
			</div>
			<div class="menu_up_button">
				<a href="?shell=notify"><span class="menu_up_text">NOTIFY</span></a>		// Logout button
			</div>
			<div class="menu_up_button">
				<a href="?shell=add"><span class="menu_up_text">ADD</span></a>			// Add button
			</div>
			<div class="menu_up_button">
				<a href="?shell=login"><span class="menu_up_text">LOGIN</span></a>		// Login button
			</div>
			<div class="menu_up_button">
				<a href="?shell=logout"><span class="menu_up_text">LOGOUT</span></a>		// Logout button
			</div>
			<div class="menu_up_button">
				<a href="?shell=adduser"><span class="menu_up_text">REGISTER</span></a>	// Logout button
			</div>
		</div>
		<div id="header">
			{{ shell_filter, say }}						// If command say exist run him
			{{ show_root_messages }}					// Show messages for root
			{{ show_emails }}							// Show internal emails
		</div>
		<div id="chat_win">
			<div class="window_title">
				Chat
			</div>
			<div id="chat">
				{{ show_chat }}								// Show chat
			</div>
		</div>
			<div class="lshref"><a href="?shell=lshref">web</a></div>
		<div id="left_column">
			<div class="menu_left">
				<div class="window_title">
					Menu
				</div>
				<div class="window_content">
					{{ shell, ls -m /var/www }}				// Run command ls -lm /var/www
					{{ show_output }}						// Show output from command ls -lm /var/www
				</div>
			</div>
			<div class="menu_left">
				<div class="window_title">
					Notes
				</div>
				<div class="window_content">
					{{ shell_filter, note }}				// If command say exist run him
					{{ shell, note }}						// Run command note
					{{ show_output }}						// Show output from command note
				</div>
			</div>
			<div class="menu_left">
				<div class="window_title">
					NEWS
				</div>
				<div class="window_content">
					{{ shell, news }}						// Run command news
					{{ show_output }}						// Show output from command news
				</div>
			</div>
		</div>
		<div id="page">
			<div class="window_title">
				Terminal
			</div>
			<div class="window_content">
				{{ shell, $shell_request }}				// Run command from $_REQUEST
				{{ show_current_command }}				// Show current command
				<div class="output">
					{{ show_output }}						// Show output from command
				</div>
				{{ input }}								// Show command line
			</div>
		</div>
		<script type="text/javascript" src="bin/javascript/autocomplete.js"></script>
	</div>
</body>
