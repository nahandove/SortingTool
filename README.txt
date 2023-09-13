Project assignment from JetBrains Academy (www.hyperskill.org), Java Developer track.
Sorting tool is a program that can sort integers, texts, or lines of text that are either
entered via the console or supplied by an input file. The results of the sort can be displayed on 
the console or stored in a supplied file, depending on command line arguments.

Two sorting methods and three data types are provided. 

Sorting methods:

1. Natural sorting method: the inputs are sorted in natural order(For numbers, it means 
they get sorted in increasing order. For text, they are sorted alphabetically according to ASCII +
patterns: digits before letters, capital letters before lowercase letters, letters of the same case 
in alphabetical order, and finally text of shorter length before text of longer length, if the texts 
contains identical input until the shorter text ends). The numbers or texts in question are then 
displayed in order seperated by a single space (for number and word sorting) or a new line (for line
sorting). This is the default sorting method but the user can also specify it as "-sortingType natural"
via the command line. The output is displayed as below:

For number or word:

Total {data type}: {input size}
Sorted data: {item 1}, {item 2} ... {item n}

for lines:
Total lines: {input size}
Sorted data:
{line 1}
{line 2}
...
{line n}

2. byCount sorting method: For the second sorting method, provided via the command line argument 
"-sortingType byCount", the items are sorted by how frequently the item occurs (same number or same 
text) first, and then by their natural order. The sorter displays the statistical tally of each 
element from those with the least frequency to the most and, if the element occurs at the same
frequency, the sorter display the elements according to natural order (refer to "1. natural sorting
method" for definition of natural order). The output is displayed as below:

Total {data type}: {input size}.
{item 1}: {frequency} time(s), {percent occurance}%
{item 2}: {frequency} time(s), {percent occurance}%
...
{item n}: {frequency} time(s), {percent occurance}%

and so forth, where data types can be numbers, word, or line, input size is the number of inputs 
supplied by the console or by the input file, item the actual text and number, frequency the number
of occurances of a specific text or number, and finally percent occurance as the frequency of a 
particular item compared to the input size.

Data types:

Data types can be supplied via the command line in the form of "-dataType {dataType}". Three data 
types: long, word, and line, are available, with word as the default data type if no data type was
specified. All data is supplied either by the console or via an input file as items separated by an
arbitrary number of spaces and newline characters. Below are the description of the three data types:

1. long: sorts integers that falls within the range of the long data type. This sorter is able to 
recognize non-integer inputs where it will print out an error message, but continue sorting the
remaining valid entries.

2. word: sort single words. When a space in the input is encountered, the sorter considers the next
entry as a new word. 

3. line: sort a body of text separated by the newline character. The line can contain any number of
spaces. 

Input and output arguments:

The user can specify the input to be supplied by a file by the argument "-inputFile {file name}", 
and/or the output to be written to a file by the argument "-outputFile {file name}". Absent of these
arguments, the default is to input data via the console and output data to the console. The user only
supply the argument if they specfically want file input or output. If the user supplies an output file,
error messages will be printed to the console but the final result stored in the specified output file.

Error messages:

The program recognizes invalid user arguments. If the user includes "-sortingType" or "-dataType" without
the corresponding type specification, the program informs the user and ends.

For other non-specific arguments, the program prints out an error message for each invalid argument but is 
able to continue as long as valid arguments exist.

13. September, 2023--description by E. Hsu (nahandove@gmail.com)