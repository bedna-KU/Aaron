<body onLoad = "start()">
	<div id = "ku_win">
		<div class="root_mail">
			{{ show_root_messages }}</div>	// Show messages for root
		<div class="mail">
			{{ show_emails }}</div>			// Show internal emails
		{{ shell_filter, say }}				// If command say exist run him
		<div id="chat">
			{{ show_chat }}					// Show chat
		</div>
		{{ shell, $shell_request }}	// Run command from $_REQUEST
		{{ show_current_command }}	// Show current command
		<div class="output">
			{{ show_output }}		// Show output from command
		</div>
		{{ input }}					// Show command line
		<script type="text/javascript" src="bin/javascript/autocomplete.js"></script>
	</div>
</body>
