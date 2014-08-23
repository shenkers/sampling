sampling
========

command line utility for uniformly sampilng from a stream with known number of elements

usage
=====

the execuable StreamSampler.jar can be run as follows, assuming you have a JRE installed

[input stream] | java -jar StreamSampler.jar [n items] [total N] > [sampled file]

for a concrete example on a unix system, the command 

seq 10 | java -jar StreamSampler.jar 3 10

will uniformly sample 3 numbers from the range (1-10)
