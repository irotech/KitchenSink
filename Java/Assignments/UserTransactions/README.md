# User Transaction README

You have been given transaction data for customers in XML format and have been asked to analyse this data. 
For each input file, create an output xml which shows each customers average transaction and the overall average transaction.
For each value give two decimal points of precision.

All example XML files are in src/test/resources/

Example input files.

SampleInput1.xml 
SampleInput2.xml

Example output files. 

SampleOutput1.xml
SampleOutput2.xml

To help with the assignment you have been provided with ClasspathFileImporter which allows you to import
XML files form the classpath as a String. To see an example there is a test case in ClasspathFileImporterTest.

The initial test cases using SampleInput1 and SampleInput2 are available in TransactionAnalyserTest.

You are expected to implement the public method "public String analyse(String xml)" returning an XML String.

The project uses Maven for dependency management and you may use any dependency that you like. 