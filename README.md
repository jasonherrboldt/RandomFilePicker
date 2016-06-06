# RandomFilePicker
A program that opens a file at random from a specified directory. Useful for media files like photos, movies, and songs. (Under construction.)

This kind of counts as a coding exercise example, but mostly it's just a quick and dirty utility that I wanted to use at home.

Command line usage:
* directory [the directory to explore ("." for current)]
* -r [make the exploration recursive to all subdirectories]
* -maxlength [the max number of files the program is allowed to discover (must be >= 1 and <= 200,000)]

For Windows users: If you specify a filepath, be warned that its name must not contain spaces (inner recursive directories may).

WARNING: Although I have made efforts to limit the type of files that this program may open, it is up to the user to ensure his or her own safety.

(Currently only supports Windows 7. Future updates will include other versions of Windows as well as OS X.)