# RandomFilePicker
(Under construction.)

A program that opens a file at random from a specified directory, using the file's associated default program. Useful for large directories of media when you're in the mood to be surprised. (Currently only supports Windows 7 and OS X. Future updates will include other operating systems.)

Command line usage:
* directory [The directory to explore ("." for current). Must be wrapped in double quotes.]
* extension(s) [Only search for / open files with specified extension(s). (At least one extension is required. Multiple extensions must be whitespace separated and wrapped in double quotes.)]
* -m [The max number of files the program is allowed to discover. (Must be >= 1, default is 100,000.)]
* -r (Make the exploration recursive to all subdirectories.)
* -p (Only print discovered files to the console. Do not open any of them.)

The first argument is expected to be the root directory and the second argument is expected to be the extension limiter(s); all other options may be entered in any order.

Hint: If the console output overwhelms your terminal window for huge directories while using the -p option, use "> output.txt" at the end of your arguments to send everything to a file.

Sample command line usage:

        // todo

Sample console output (will only print runtime details if the user does not engage the -p option):

        // todo