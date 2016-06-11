# RandomFilePicker
(Under construction.)

A program that opens a file at random from a specified directory, using the file's associated default program. Useful for large directories of media when you're in the mood to be surprised. (Currently only supports Windows 7 and OS X. Future updates will include other operating systems.)

(Program will only discover files of the type [a.b], where a and b are some non-empty strings. This is to avoid accidentally opening hidden or system files.)

Command line usage:
* directory [The directory to explore ("." for current). Must be wrapped in double quotes.]
* -m [The max number of files the program is allowed to discover. (Must be > 0. Default is 100,000.)]
* -e (Only search for / open files with specified extension(s). Multiple extensions must be whitespace separated and wrapped in double quotes.)
* -r (Make the exploration recursive to all subdirectories.)
* -p (Only print discovered files to the console. Do not open any of them.)

The first argument is expected to be the root directory; all other options may be entered in any order.

(Hint: If the console output overwhelms your terminal window for huge directories while using the -p option, use "> output.txt" at the end of your arguments to send everything to a file.)

Sample command line usage in a terminal window:

        javac src/com/jason/*.java

        Mac:
        java -cp ./src com.jason.Main "/Users/user/path/to/Root Directory/" -e "docx mp3" -r -p -m 100

        Windows:
        java -cp ./src com.jason.Main "C:\path\to\Root Directory\" -e "docx mp3" -r -p -m 1000

Sample input / output:

        # INPUT

        "/Users/jasonherrboldt/Documents/Test Directory" -r -p

        # OUTPUT

        Printing discovered files:

        1: /Users/jasonherrboldt/Documents/Test Directory/Test Inner Directory 2/test.txt
        2: /Users/jasonherrboldt/Documents/Test Directory/test.mp3
        3: /Users/jasonherrboldt/Documents/Test Directory/test.xlsx
        4: /Users/jasonherrboldt/Documents/Test Directory/Test_Inner_Directory_3/Test_Inner_Directory_4/test.docx

        The following file extensions were discovered:

        .docx
        .mp3
        .txt
        .xlsx


        Runtime details:

        User's OS: Mac
        Root directory: /Users/jasonherrboldt/Documents/Test Directory
        Max length: 100000
        Extension limiter(s): (none)
        Recursive: true
        Print only: true


        # INPUT

        "/Users/jasonherrboldt/Documents/Test Directory" -e "docx mp3" -r -m 3

        # OUTPUT

        Opening file test.mp3

        The following file extensions were discovered:

        .docx
        .mp3


        Runtime details:

        User's OS: Mac
        Root directory: /Users/jasonherrboldt/Documents/Test Directory
        Max length: 3
        Extension limiter(s): [docx, mp3]
        Recursive: true
        Print only: false