Stock Market Analyser

Write an application to read a CSV file, process the data on each row, and write the results to a new CSV file.

Input CSV file:
The input file name is specified as the first command line parameter (@see main()).
RFC4180 format (@see http://www.ietf.org/rfc/rfc4180.txt).
Can assume data does not contain control characters (e.g., \r or \n).
There is no header.
Blank (empty) rows should be ignored.
The first column contains the ‘name’ of the data row
Other columns contain the numeric values.
Example CSV:
"EXAMPLE1",1,2,3,4,5,6
"EXAMPLE2",4,2,4,7,1,5
"EXAMPLE3",6,5,4,3,2,1

Data processing:
Data consists of the ‘prices’ for the item at specific points in time.
Need to calculate the optimal ‘buy’ and ‘sell’ values.
Output the column number for ‘buy’ at and ‘sell’ at, plus the ‘profit’.
The calculation of these values should be as efficient as possible, ideally in linear time.
There are no dependencies between rows in the data.
Example processing:
values: 1, 2, 3, 4, 5, 6; calculates buy at #1, sell at #6, profit 5
values: 4, 2, 4, 7, 1, 5; calculates buy at #2, sell at #4, profit 5
values: 6, 5, 4, 3, 2, 1; calculates buy & sell at #0 (no buy), profit 0

Output CSV file:
The output file name is specified as the second command line parameter (@see main())
Output is bar-delimited CSV, no data is quoted.
Order of the rows in the file need not match the order in the source CSV.
First column is the ‘name’ of the data row (copied from the input).
Second column is the ‘buy’ index value.
Third column is the ‘sell’ index value.
Fourth column is the ‘profit’ realised.
Example output CSV (from input example above):
EXAMPLE1|1|6|5
EXAMPLE2|2|4|5
EXAMPLE3|0|0|0

Implementation:
Code should compile to a standalone Java jar.
We require all source code and build instructions (make/maven/or other)
Guidance:
You may use any open source libraries

-- HOW to use the application --

The application requires the following installations:

Java JDK 8 - compile and run the project
Maven - build the project

1) Open a terminal (macOS, Linux) or cmd/powershell (Windows)

2) Move into the project root directory (<local-path>/<project-name>)
    cd <local-path>/<project-name>

3) Build the project using Maven
    mvn clean package

4) Create the input file and place it into the "user-home"/downloads folder
    linux - ~/Downloads OR /home/<user>/Downloads
    MacOS - /Users/<user>/downloads
    Windows - C:\Users\<user>\Downloads

5) Execute the project (java -jar <JAR-location> <inputFileName> <outputFileName>)
    java -jar target/stockMarket-0.1.jar input.csv output.csv