# RandomFilePicker
(Under construction.)

A program that opens a file at random from a specified directory, using the file's associated default program. Useful for directories of mixed media files like photos, movies, and songs. (Currently only supports Windows 7 and OS X. Future updates will include other operating systems.)

This kind of counts as a coding exercise example, but mostly it's just a quick and dirty utility that I wanted to use at home.

Command line usage:
* directory [The directory to explore ("." for current). Wrap in double quotes to avoid whitespace issues.]
* -m [The max number of files the program is allowed to discover. Must be >= 1 and <= 100,000. (Default is 100,000.)]
* -e (Only search for / open files with this extension.) Coming soon: user may specify more than one file extension type.
* -r (Make the exploration recursive to all subdirectories.)
* -p (Only print discovered files to the console. Do not open any of them.)

(The first argument is expected to be the root directory; all other options may be entered in any order.)

WARNING: Although I have made efforts to limit the type of files that this program may open, it is up to the user to ensure his or her own safety. It's a good idea to print all files in a directory / directory tree before opening one at random.

Sample command line usage:

        javac src/com/jason/*.java

        Mac:
        java -cp ./src com.jason.Main "/Users/user/path/to/Test Directory/" -r -p

        Windows:
        java -cp ./src com.jason.Main "C:\path\to\Test Directory\" -r -p

Sample console output:

        Runtime details:

        User's OS: Mac
        Root directory: /Users/jasonherrboldt/Documents/Test Directory
        Max length: 100000
        Extension limiter(s):
        Recursive: true
        Print only: true


        The following file type extensions were discovered:

        txt
        mp3
        xlsx
        docx


        Printing discovered files:

        1: /Users/jasonherrboldt/Documents/Test Directory/Test Inner Directory 2/test.txt
        2: /Users/jasonherrboldt/Documents/Test Directory/test with space.txt
        3: /Users/jasonherrboldt/Documents/Test Directory/test.mp3
        4: /Users/jasonherrboldt/Documents/Test Directory/test.xlsx
        5: /Users/jasonherrboldt/Documents/Test Directory/Test_Inner_Directory_3_'()-^,/Test_Inner_Directory_4/_,test's^()-.docx

        Process finished with exit code 0