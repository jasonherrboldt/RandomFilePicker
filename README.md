# RandomFilePicker
(Under construction.)

A program that opens a file at random from a specified directory, using the file's associated default program. Useful for large directories of media when you're in the mood to be surprised. (Currently only supports Windows 7 and OS X. Future updates will include other operating systems.)

This kind of counts as a coding exercise example, but mostly it's just a quick and dirty utility that I wanted to use at home.

Command line usage:
* directory [The directory to explore ("." for current). Be sure to wrap this in double quotes to avoid whitespace issues.]
* -m [The max number of files the program is allowed to discover. Must be >= 1 and <= 100,000. (Default is 100,000.)]
* -e (Only search for / open files with this extension.) (Coming soon: user may specify more than one file extension type.)
* -r (Make the exploration recursive to all subdirectories.)
* -p (Only print discovered files to the console. Do not open any of them.)

The first argument is expected to be the root directory; all other options may be entered in any order.

WARNING: It's a good idea to print all files in a directory before opening one at random.

Hint: If the console output overwhelms your terminal window for huge directories, use "> output.txt" at the end of your arguments to send everything to a file.

Sample command line usage:

        javac src/com/jason/*.java

        Mac:
        java -cp ./src com.jason.Main "/Users/user/path/to/Test Directory/" -r -p

        Windows:
        java -cp ./src com.jason.Main "C:\path\to\Test Directory\" -r -p

Sample console output (will only print runtime details if the user does not engage the -p option):

        Runtime details:

        User's OS: Mac
        Root directory: /Users/jasonherrboldt/Documents/Test Directory
        Max length: 100000
        Extension limiter(s):
        Recursive: true
        Print only: true


        The following file type extensions were discovered:

        .docx
        .mp3
        .txt
        .xlsx


        Printing discovered files:

        1: /Users/jasonherrboldt/Documents/Test Directory/Test Inner Directory 2/test.txt
        2: /Users/jasonherrboldt/Documents/Test Directory/test.mp3
        3: /Users/jasonherrboldt/Documents/Test Directory/test.xlsx
        4: /Users/jasonherrboldt/Documents/Test Directory/Test_Inner_Directory_3[]_'()-^,/Test_Inner_Directory_4/_,test's[]^()-.docx

        Process finished with exit code 0