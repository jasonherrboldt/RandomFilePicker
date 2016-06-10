# RandomFilePicker
(Under construction.)

A program that opens a file at random from a specified directory, using the file's associated default program. Useful for directories of mixed media files like photos, movies, and songs.

This kind of counts as a coding exercise example, but mostly it's just a quick and dirty utility that I wanted to use at home.

Command line usage:
* directory [The directory to explore ("." for current). Must be wrapped in double quotes.]
* -m [The max number of files the program is allowed to discover. Must be >= 1 and <= 100,000. (Default is 100,000.)]
* -e (Only search for / open files with this extension.)
* -r (Make the exploration recursive to all subdirectories.)
* -p (Only print discovered files to the console. Do not open any of them.)

(The first argument is expected to be the root directory; all other options may be entered in any order.)

WARNING: Although I have made efforts to limit the type of files that this program may open, it is up to the user to ensure his or her own safety.

Sample console output:

        Runtime details:

        User's OS: Mac
        Root directory: (the current directory)
        Max length: 20
        Extension:
        Recursive: true
        Print only: true


        The following file type extensions were discovered:

        txt
        xml
        iml
        idx
        sample
        pack
        class


        Printing discovered files:

        1: ./.git/hooks/applypatch-msg.sample
        2: ./.git/hooks/commit-msg.sample
        3: ./.git/hooks/post-update.sample
        4: ./.git/hooks/pre-applypatch.sample
        5: ./.git/hooks/pre-commit.sample
        6: ./.git/hooks/pre-push.sample
        7: ./.git/hooks/pre-rebase.sample
        8: ./.git/hooks/prepare-commit-msg.sample

(Currently only supports Windows 7. Future updates will include other versions of Windows as well as OS X.)

        IDE sample runtime configs:

        Windows 7:
        "C:\dev\demos\RandomFilePicker\Test_Directory" -r docx
        "C:\dev\demos\RandomFilePicker\Test_Directory" -r -m 2000 -p

        Mac:
        "/Users/jasonherrboldt/Documents/Test_Directory/" -r -p
        "/Users/jasonherrboldt/Music/iTunes/iTunes Media/Music" -m 100 -e m4a -r -p

        Either:
        . -r

        Compiling / running from the command line:
        javac src/com/jason/*.java
        java -cp ./src com.jason.Main . -r -p -m 15
        java -cp ./src com.jason.Main "C:\dev\demos\RandomFilePicker\Test_Directory" -r -e png