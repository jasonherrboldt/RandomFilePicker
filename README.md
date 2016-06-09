# RandomFilePicker
(Under construction.)

A program that opens a file at random from a specified directory, using the file's associated default program. Useful for directories of mixed media files like photos, movies, and songs.

This kind of counts as a coding exercise example, but mostly it's just a quick and dirty utility that I wanted to use at home.

Command line usage:
* directory [The directory to explore ("." for current).]
* -s (Only display discovered files -- do not open any of them.)
* -r (Make the exploration recursive to all subdirectories.)
* -m [The max number of files the program is allowed to discover. Must be >= 1 and <= 100,000. (Default is 100,000.)]


For Windows users: Root specified filepath names may not have spaces. (Inner recursive directory names may have spaces).

WARNING: Although I have made efforts to limit the type of files that this program may open, it is up to the user to ensure his or her own safety.

(Currently only supports Windows 7. Future updates will include other versions of Windows as well as OS X.)